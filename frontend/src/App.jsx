import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/auth/Login";
import Layout from "./pages/Layout";
import Dashboard from "./pages/Dashboard";
import Inventory from "./pages/Inventory";
import Orders from "./pages/Orders";
import ShipmentStatus from "./pages/ShipmentStatus";
import ProtectedRoute from "./routes/ProtectedRoute";
import UserProfile from "./pages/auth/UserProfile";
import Reports from "./pages/Reports";
import Suppliers from "./pages/Suppliers";
import Signup from "./pages/auth/Signup";
import ReportsWrapper from "./pages/ReportsWrapper";
import Shop from "./pages/Shop";
import Unauthorized from "./pages/auth/UnAuthorized";
import { roleRoutes } from "./config/roleRoute";
import CustomerOrder from "./pages/CustomerOrder"; // ✅ import the new component

function App() {
  const [user, setUser] = React.useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  const handleLogin = (userData) => {
    localStorage.setItem("user", JSON.stringify(userData));
    setUser(userData);
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  const getDefaultRoute = (roleId) => roleRoutes[roleId]?.defaultRoute || "/login";

  return (
    <Routes>
      {/* Login */}
      <Route
        path="/login"
        element={user ? <Navigate to={getDefaultRoute(user.role_id)} /> : <Login onLogin={handleLogin} />}
      />

      {/* Admin Routes */}
      <Route
        path="/dashboard/*"
        element={
          <ProtectedRoute allowedRoles={["Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Dashboard />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/inventory"
        element={
          <ProtectedRoute allowedRoles={["Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Inventory />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/suppliers"
        element={
          <ProtectedRoute allowedRoles={["Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Suppliers />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/reports"
        element={
          <ProtectedRoute allowedRoles={["Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Reports />
            </Layout>
          </ProtectedRoute>
        }
      />

      {/* Manager Routes */}
      <Route
        path="/orders"
        element={
          <ProtectedRoute allowedRoles={["Manager", "Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Orders />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/shipments"
        element={
          <ProtectedRoute allowedRoles={["Manager", "Admin"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <ShipmentStatus />
            </Layout>
          </ProtectedRoute>
        }
      />
      <Route
        path="/customer-orders"
        element={
          <ProtectedRoute allowedRoles={["Customer"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <CustomerOrder />
            </Layout>
          </ProtectedRoute>
        }
      />
      {/* Customer Routes */}
      <Route
        path="/shop"
        element={
          <ProtectedRoute allowedRoles={["Customer"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <Shop />
            </Layout>
          </ProtectedRoute>
        }
      />

      {/* Common Routes */}
      <Route
        path="/userprofile/:email"
        element={
          <ProtectedRoute allowedRoles={["Admin", "Manager", "Customer"]}>
            <Layout userEmail={user?.email} userRoleId={user?.role_id} onLogout={handleLogout}>
              <UserProfile />
            </Layout>
          </ProtectedRoute>
        }
      />

      <Route
        path="/signup"
        element={user ? <Navigate to={getDefaultRoute(user.role_id)} /> : <Signup onSignup={handleLogin} />}
      />

      {/* Unauthorized + fallback */}
      <Route path="/unauthorized" element={<Unauthorized />} />
      <Route path="*" element={<Navigate to={user ? getDefaultRoute(user.role_id) : "/login"} />} />
    </Routes>
  );
}

export default function AppWrapper() {
  return (
    <Router>
      <App />
    </Router>
  );
}
