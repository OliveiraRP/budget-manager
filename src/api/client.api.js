import { ENV } from "../config/env";

async function request(endpoint, options = {}) {
  const token = localStorage.getItem("authToken");

  const headers = {
    "Content-Type": "application/json",
    ...options.headers,
  };

  if (token && token !== "null") {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const response = await fetch(`${ENV.BACKEND_URL}/api/v1${endpoint}`, {
    ...options,
    headers,
  });

  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}));
    throw new Error(errorData.error || `Error: ${response.status}`);
  }

  if (response.status === 204) return null;
  return response.json();
}

export const client = {
  get: (url, options) => request(url, { ...options, method: "GET" }),
  post: (url, body, options) =>
    request(url, { ...options, method: "POST", body: JSON.stringify(body) }),
  put: (url, body, options) =>
    request(url, { ...options, method: "PUT", body: JSON.stringify(body) }),
  patch: (url, body, options) =>
    request(url, { ...options, method: "PATCH", body: JSON.stringify(body) }),
  delete: (url, options) => request(url, { ...options, method: "DELETE" }),
};
