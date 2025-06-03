// hooks/useAuthUser.js
import { useEffect, useState } from "react";
import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

export function useAuthUser() {
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get("token");

    const fetchUser = async (tokenToUse) => {
      try {
        const res = await axios.get(`${API_URL}/users/me`, {
          headers: { Authorization: `Bearer ${tokenToUse}` },
        });
        if (res.data.error) {
          setError(res.data.error);
          setUser(null);
        } else {
          setUser(res.data);
          setError(null);
        }
      } catch (err) {
        if (err.response?.data?.error) {
          setError(err.response.data.error);
        } else {
          setError("Network or server error");
        }
        setUser(null);
      }
    };

    if (token) {
      localStorage.setItem("accessToken", token);
      window.history.replaceState({}, document.title, window.location.pathname);
      fetchUser(token);
    } else {
      const storedToken = localStorage.getItem("accessToken");
      if (storedToken) {
        fetchUser(storedToken);
      } else {
        setError("No token found");
      }
    }
  }, []);

  return { user, error };
}
