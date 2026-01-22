import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { client } from "../api/client.api";

export function useTransactions(walletId) {
  return useQuery({
    queryKey: ["transactions", walletId],
    queryFn: () => client.get(`/transactions/wallet/${walletId}`),
    enabled: !!walletId,
  });
}

export function useTransactionsByTimeframe(startDate, endDate) {
  return useQuery({
    queryKey: ["transactions", { startDate, endDate }],
    queryFn: () =>
      client.get(`/transactions?startDate=${startDate}&endDate=${endDate}`),
    enabled: !!startDate && !!endDate,
  });
}

export function useCreateTransaction() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (payload) => client.post("/transactions", payload),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["transactions"] });
      queryClient.invalidateQueries({ queryKey: ["wallets"] });
    },
  });
}

export function useUpdateTransaction() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (payload) => client.put(`/transactions/${payload.id}`, payload),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["transactions"] });
      queryClient.invalidateQueries({ queryKey: ["wallets"] });
    },
  });
}

export function useDeleteTransaction() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id) => client.delete(`/transactions/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["transactions"] });
      queryClient.invalidateQueries({ queryKey: ["wallets"] });
    },
  });
}
