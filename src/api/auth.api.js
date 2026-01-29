import { ENV } from "../config/env";

async function request(endpoint, method = "GET", body = null, options = {}) {
  const url = `${ENV.BACKEND_URL}/api/v1${endpoint}`;

  const fetchOptions = {
    method,
    headers: {
      "Content-Type": "application/json",
      ...options.headers,
    },
    credentials: "include",
    ...options,
  };

  if (body) {
    fetchOptions.body = JSON.stringify(body);
  }

  const response = await fetch(url, fetchOptions);

  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}));
    throw new Error(errorData.error || `Error: ${response.status}`);
  }

  if (response.status === 204) return null;
  return response.json();
}

export const api = {
  get: (url, options) => request(url, "GET", null, options),
  post: (url, body, options) => request(url, "POST", body, options),
  put: (url, body, options) => request(url, "PUT", body, options),
  patch: (url, body, options) => request(url, "PATCH", body, options),
  delete: (url, options) => request(url, "DELETE", null, options),
};

export async function fetchCurrentUser() {
  try {
    return await api.get("/auth/me");
  } catch (error) {
    return null;
  }
}
