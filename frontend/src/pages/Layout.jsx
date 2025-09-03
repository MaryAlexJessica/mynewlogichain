// src/pages/Layout.jsx
import React from "react";
import Sidebar from "../components/Sidebar";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "./analytics/Layout.css";

const Layout = ({ children, onLogout }) => {
  // ✅ Load full user object from localStorage
  const user = React.useMemo(() => {
    const stored = localStorage.getItem("user");
    return stored ? JSON.parse(stored) : null;
  }, []);

  if (!user) return null; // don't render layout if not logged in
  console.log("Layout userRoleId:", user.role_id);
  return (
    <div className="app-container">
      <Sidebar roleId={user.role_id} />
      <div className="main-container">
        <Header user={user} onLogout={onLogout} />
        <div className="page-content">{children}</div>
        <Footer />
      </div>
    </div>
  );
};

export default Layout;
