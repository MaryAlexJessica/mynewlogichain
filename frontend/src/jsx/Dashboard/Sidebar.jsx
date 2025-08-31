import React from "react";
import "../../css/Sidebar.css";
import { Link } from "react-router-dom"; // ✅ Import Link

const Sidebar = () => {
  return (
    <aside className="sidebar">
      <h2 className="logo">LogiChain</h2>
      <nav className="nav">
        <Link to="/dashboard" className="nav-item">📊 Dashboard</Link>
        <a href="#orders" className="nav-item">📦 Categories</a>
        <a href="#suppliers" className="nav-item">🛒 Orders</a>
        <a href="#logistics" className="nav-item">⚙️ Settings</a>
        <a href="#reports" className="nav-item">🚪 Log Out</a>
      </nav>
    </aside>
  );
};

export default Sidebar;