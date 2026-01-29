import { useQuery } from "@tanstack/react-query";
import { api } from "../api/auth.api";

export function useUserSettings() {
  return useQuery({
    queryKey: ["userSettings"],
    queryFn: () => api.get("/settings"),
  });
}
