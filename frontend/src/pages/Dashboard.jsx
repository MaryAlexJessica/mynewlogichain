import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";   // ✅ import for navigation
import { getInventory } from "../services/inventoryService";
import { getOrders } from "../services/ordersService";
import { getShipmentStatus } from "../services/shipmentsService";
import Table from "../components/common/Table";
import "./analytics/Dashboard.css"; // ✅ import CSS for styling

const Dashboard = () => {
  const [inventory, setInventory] = useState([]);
  const [orders, setOrders] = useState([]);
  const [shipments, setShipments] = useState([]);
  const navigate = useNavigate(); // ✅ hook
  const previewInventory = inventory.slice(0, 3);
  const previewOrders = orders.slice(0, 3);

  useEffect(() => {
    getInventory().then(setInventory);
    getOrders().then(setOrders);
    getShipmentStatus().then(setShipments);
  }, []);

  // KPI calculations
  const totalInventory = inventory.length;
  const lowStockItems = inventory.filter(item => item.quantity < 10).length;
  const pendingOrders = orders.filter(order => order.status === "Pending").length;
  const shipmentsInTransit = shipments.filter(s => s.status === "In Transit").length;

  return (
    <div className="dashboard-main">
      {/* KPI Cards */}
      <div className="kpi-container">
        <div className="kpi-card">📦 Total Inventory: {totalInventory}</div>
        <div className="kpi-card">⚠️ Low Stock Items: {lowStockItems}</div>
        <div className="kpi-card">📝 Pending Orders: {pendingOrders}</div>
        <div className="kpi-card">🚚 Shipments In Transit: {shipmentsInTransit}</div>
      </div>

      {/* Inventory Summary */}
      <div className="section header-with-btn">
        <h3>Inventory Summary</h3>
        <button
          className="view-all-btn"
          onClick={() => navigate("/inventory")}  // ✅ navigate to inventory screen
        >
          View All
        </button>
      </div>
      <Table data={previewInventory} />

      {/* Orders Summary */}
      <div className="section header-with-btn">
        <h3>Recent Orders</h3>
        <button
          className="view-all-btn"
          onClick={() => navigate("/orders")}  // ✅ navigate to orders screen
        >
          View All
        </button>     
      </div>
        <Table data={previewOrders} />
      {/* Shipments Status */}
      <div className="section header-with-btn">
        <h3>Shipments Status</h3>
       <button
          className="view-all-btn"
          onClick={() => navigate("/shipmentstatus")}  // ✅ navigate to shipmentstatus screen
        >
          View All
        </button>     
      </div>
       <Table data={shipments} />
    </div>
  );
};

export default Dashboard;
