import { useEffect, useState } from "react";
import { ENV } from "../config/env.js";
import { fetchCurrentUser } from "../api/auth.api";
import MainLayout from "./MainLayout.jsx";

export default function RootPage() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function checkAuth() {
      const currentUser = await fetchCurrentUser();

      if (!currentUser) {
        console.log(
          "AUTH FAILED: Staying here so you can check the Network tab!",
        );
        // window.location.href = ENV.HUB_URL;
        setLoading(false);
        return;
      }

      setUser(currentUser);
      setLoading(false);
    }
    checkAuth();
  }, []);

  if (loading) return <p>Verifying authentication...</p>;

  return user ? <MainLayout /> : <p>Auth Failed - Check the Network Tab!</p>;
}
