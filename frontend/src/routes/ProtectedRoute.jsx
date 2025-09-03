import React from "react";
import { Navigate } from "react-router-dom";

const ProtectedRoute = ({ children, allowedRoleIds }) => {
  const userData = JSON.parse(localStorage.getItem("user"));

  // 🚫 If no user, go to login
  if (!userData) {
    return <Navigate to="/login" replace />;
  }

  // 🚫 If role_id not allowed, go unauthorized
  if (allowedRoleIds && !allowedRoleIds.includes(userData.role_id)) {
    return <Navigate to="/unauthorized" replace />;
  }

  // ✅ If role_id allowed, render page
  return children;
};

export default ProtectedRoute;
