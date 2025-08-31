import React from "react";
import "../../css/Dashboard.css";
import Sidebar from "./Sidebar";
import Header from "./Header";

const Dashboard = ({ userEmail, onLogout }) => {
  return (
    <div className="dashboard-container">
      <Sidebar />
      <main className="dashboard-main">
        <Header userEmail={userEmail} onLogout={onLogout} />
        <section className="dashboard-content">
          <h1>Dashboard</h1>
          <p>This is where key performance indicators and widgets will appear.</p>
          <div className="dashboard-widgets">
            <div className="dashboard-widget">Inventory Summary</div>
            <div className="dashboard-widget">Order Status</div>
            <div className="dashboard-widget">Shipment Tracking</div>
            <div className="dashboard-widget">Supplier Performance</div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Dashboard;