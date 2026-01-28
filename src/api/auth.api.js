import { client } from "./client.api";

export async function fetchCurrentUser() {
  const token = localStorage.getItem("authToken");

  if (!token || token === "null") {
    return null;
  }

  try {
    return await client.get("/auth/me");
  } catch (error) {
    localStorage.removeItem("authToken");
    return null;
  }
}
