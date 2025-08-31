import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/auth/Login";
import Layout from "./pages/Layout";       // Layout wrapper
import Dashboard from "./pages/Dashboard";  // Dashboard content
import Inventory from "./pages/Inventory";
import Orders from "./pages/Orders";
import ShipmentStatus from "./pages/ShipmentStatus";
import ProtectedRoute from "./routes/ProtectedRoute";
import UserProfile from "./pages/auth/UserProfile";
import Reports from "./pages/Reports";
import Suppliers from "./pages/Suppliers";
import Signup from "./pages/auth/Signup";

function App() {
  const [user, setUser] = React.useState(() => localStorage.getItem("user") || null);

  const handleLogin = (email) => {
    localStorage.setItem("user", email);
    setUser(email);
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <Routes>
      <Route
        path="/login"
        element={user ? <Navigate to="/dashboard" /> : <Login onLogin={handleLogin} />}
      />

      <Route
        path="/dashboard/*"
        element={
          <ProtectedRoute>
            <Layout userEmail={user} onLogout={handleLogout}>
              <Dashboard />   {/* Pass Dashboard as children */}
            </Layout>
          </ProtectedRoute>
        }
      />

      <Route path="*" element={<Navigate to={user ? "/dashboard" : "/login"} />} />
      <Route
  path="/inventory"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <Inventory />
      </Layout>
    </ProtectedRoute>
  }
/>
 <Route
  path="/orders"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <Orders />
      </Layout>
    </ProtectedRoute>
  }
/>
<Route
  path="/suppliers"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <Suppliers />
      </Layout>
    </ProtectedRoute>
  }
/>
<Route
  path="/shipmentstatus"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <ShipmentStatus />
      </Layout>
    </ProtectedRoute>
  }
/>
  <Route
  path="/userprofile/:email"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <UserProfile />
      </Layout>
    </ProtectedRoute>
  }
/>
  <Route
  path="/reports"
  element={
    <ProtectedRoute>
      <Layout userEmail={user} onLogout={handleLogout}>
        <Reports />
      </Layout>
    </ProtectedRoute>
   }
  />
  <Route
  path="/signup"
  element={
    user ? <Navigate to="/dashboard" /> : 
    <Signup onSignup={handleLogin} />
  }
  />
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
