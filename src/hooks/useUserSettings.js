import { useQuery } from "@tanstack/react-query";
import { client } from "../api/client.api";

export function useUserSettings() {
  return useQuery({
    queryKey: ["userSettings"],
    queryFn: () => client.get("/settings"),
  });
}
