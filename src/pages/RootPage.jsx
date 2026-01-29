import { useQuery } from "@tanstack/react-query";
import { ENV } from "../config/env.js";
import { fetchCurrentUser } from "../api/auth.api";
import MainLayout from "./MainLayout.jsx";

export default function RootPage() {
  const {
    data: user,
    isLoading,
    isError,
  } = useQuery({
    queryKey: ["currentUser"],
    queryFn: fetchCurrentUser,
    retry: false,
    staleTime: 1000 * 60 * 5,
  });

  if (isLoading) {
    return null;
  }

  if (isError || !user) {
    window.location.href = ENV.HUB_URL;
    return null;
  }

  return <MainLayout />;
}
