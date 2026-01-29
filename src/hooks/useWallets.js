import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "../api/auth.api";

export function useWallets() {
  return useQuery({
    queryKey: ["wallets"],
    queryFn: () => api.get("/wallets"),
  });
}

export function useCreateWallet() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (payload) => api.post("/wallets", payload),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["wallets"] }),
  });
}

export function useUpdateWallet() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: ({ id, payload }) => api.patch(`/wallets/${id}`, payload),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["wallets"] }),
  });
}

export function useArchiveWallet() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (id) => api.patch(`/wallets/${id}/archive`),
    onMutate: async (id) => {
      await queryClient.cancelQueries({ queryKey: ["wallets"] });
      const previous = queryClient.getQueryData(["wallets"]);
      queryClient.setQueryData(["wallets"], (old) =>
        old?.filter((w) => w.id !== id),
      );
      return { previous };
    },
    onError: (err, id, context) => {
      queryClient.setQueryData(["wallets"], context.previous);
    },
    onSettled: () => queryClient.invalidateQueries({ queryKey: ["wallets"] }),
  });
}
