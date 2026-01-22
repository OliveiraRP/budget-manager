import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { client } from "../api/client.api";

const KEYS = {
  all: ["categories"],
  groups: ["category-groups"],
};

export function useCategories() {
  return useQuery({
    queryKey: KEYS.all,
    queryFn: () => client.get("/categories"),
    staleTime: 1000 * 60 * 10,
  });
}

export function useCategoryGroups() {
  return useQuery({
    queryKey: KEYS.groups,
    queryFn: () => client.get("/categories/groups"),
  });
}

export function useCreateCategory() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (newCategory) => client.post("/categories", newCategory),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: KEYS.all }),
  });
}

export function useCreateCategoryGroup() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (newGroup) => client.post("/categories/groups", newGroup),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: KEYS.groups }),
  });
}
