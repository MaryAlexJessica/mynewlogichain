import React from "react";
import Sidebar from "../components/Sidebar";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Dashboard from "./Dashboard";
import "./analytics/Layout.css";

const Layout = ({ children, userEmail, onLogout }) => {
  return (
    <div className="app-container">
  <Sidebar />
  <div className="main-container">
     <Header user={userEmail} onLogout={onLogout} />  {/* ✅ Pass userEmail as "user" */}
    <div className="page-content">
     {children}
    </div>
    <Footer />
  </div>
</div>
  );
};

export default Layout;
