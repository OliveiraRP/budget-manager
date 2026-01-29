import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { api } from "../api/auth.api";

const KEYS = {
  all: ["categories"],
  groups: ["category-groups"],
};

export function useCategories() {
  return useQuery({
    queryKey: KEYS.all,
    queryFn: () => api.get("/categories"),
    staleTime: 1000 * 60 * 10,
  });
}

export function useCategoryGroups() {
  return useQuery({
    queryKey: KEYS.groups,
    queryFn: () => api.get("/categories/groups"),
  });
}

export function useCreateCategory() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (newCategory) => api.post("/categories", newCategory),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: KEYS.all }),
  });
}

export function useCreateCategoryGroup() {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (newGroup) => api.post("/categories/groups", newGroup),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: KEYS.groups }),
  });
}
