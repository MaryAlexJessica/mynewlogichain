// src/components/Sidebar.jsx
import React from "react";
import { NavLink } from "react-router-dom";
import "../pages/analytics/Sidebar.css";

const Sidebar = () => {
  return (
    <div className="sidebar">
      <h2 className="sidebar-title">LogiChain</h2>
      <ul>
        <li>
          <NavLink
            to="/dashboard"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            📊 Dashboard
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/inventory"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            📦 Inventory
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/orders"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            🛒 Orders
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/suppliers"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            🏭 Suppliers
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/shipments"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            🚚 Shipments
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/reports"
            className={({ isActive }) =>
              isActive ? "active-link" : ""
            }
          >
            📈 Reports / Analytics
          </NavLink>
        </li>
      </ul>
    </div>
  );
};

export default Sidebar;
