import "../pages/analytics/Header.css";
import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Header = ({ onLogout }) => {
  const navigate = useNavigate();
  const userEmail = localStorage.getItem("user"); // get stored email

  const handleLogout = () => {
    localStorage.removeItem("user"); // clear storage
    if (onLogout) onLogout();
    navigate("/"); // go back to login page
  };

  return (
    <header className="header">
      <div className="header-left">
          Welcome, <Link to={`/userprofile/${userEmail}`}>{userEmail}</Link>
      </div>
      <div className="header-right">
        {userEmail ? (                      
            <button onClick={handleLogout} className="logout-btn">
              Logout
            </button>          
        ) : (
          <span>Guest</span>
        )}
      </div>
    </header>
  );
};

export default Header;
