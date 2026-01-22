import { client } from "../api/client.api";

export async function fetchCurrentUser() {
  try {
    return await client.get("/auth/me");
  } catch (error) {
    return null;
  }
}
