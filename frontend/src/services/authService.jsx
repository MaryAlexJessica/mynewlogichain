// src/services/authService.jsx

// Mock login function
export function mockLogin(username, password) {
  // Simple hardcoded check
  if (username === "admin" && password === "1234") {
    // You can store user info in localStorage if needed
    localStorage.setItem("user", JSON.stringify({ username }));
    return true;
  }
  return false;
}

// Mock logout function
export function mockLogout() {
  localStorage.removeItem("user");
}

// Mock check if user is logged in
export function isLoggedIn() {
  return localStorage.getItem("user") !== null;
}
