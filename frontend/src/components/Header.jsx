// src/components/Header.jsx
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "../pages/analytics/Header.css";

const Header = ({ user, onLogout }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("user");
    if (onLogout) onLogout();
    navigate("/login");
  };

  if (!user) return null;

  return (
    <header className="header">
      <div className="header-left">
        Welcome,{" "}
        <Link to={`/userprofile/${user.email}`}>
          {user.username || user.email}
        </Link>
      </div>
      <div className="header-right">
        <button onClick={handleLogout} className="logout-btn">
          Logout
        </button>
      </div>
    </header>
  );
};

export default Header;
