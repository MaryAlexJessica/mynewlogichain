// src/components/Sidebar.jsx
import React from "react";
import { NavLink } from "react-router-dom";
import { sidebarLinks } from "../config/sidebarLinks";
import "../pages/analytics/Sidebar.css";

const Sidebar = ({ roleId }) => {
  const links = sidebarLinks[roleId] || [];

  return (
    <div className="sidebar">
      <div className="sidebar-title">Menu</div>
      <ul className="sidebar-link">
        {links.map((link) => (
          <li key={link.path}>
            <NavLink
              to={link.path}
              className={({ isActive }) => (isActive ? "active-link" : "")}
            >
              <span style={{ marginRight: "8px" }}>{link.icon}</span>
              {link.label}
            </NavLink>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Sidebar;
