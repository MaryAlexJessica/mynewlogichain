// src/pages/Reports.jsx
import React from "react";
import {
  LineChart, Line, BarChart, Bar, PieChart, Pie, Cell, XAxis, YAxis, Tooltip, Legend, CartesianGrid
} from "recharts";
import "../pages/analytics/Reports.css";
import { inventoryTrends, ordersData, supplierPerformance, shipmentStatus } from "../mock/Reports";

// Define some chart colors
const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const Reports = () => {
  return (
    <div className="reports-container">
      <h2 className="reports-title">📊 Reports & Analytics</h2>

      {/* Inventory Trends */}
      <div className="report-card">
        <h3>Inventory Trends</h3>
        <LineChart width={500} height={300} data={inventoryTrends}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="month" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="stock" stroke="#8884d8" />
        </LineChart>
      </div>

      {/* Orders Over Time */}
      <div className="report-card">
        <h3>Orders Over Time</h3>
        <BarChart width={500} height={300} data={ordersData}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="month" />
          <YAxis />
          <Tooltip />
          <Legend />
          <Bar dataKey="completed" fill="#82ca9d" />
          <Bar dataKey="pending" fill="#ffc658" />
          <Bar dataKey="cancelled" fill="#ff6961" />
        </BarChart>
      </div>

      {/* Supplier Performance */}
      <div className="report-card">
        <h3>Supplier Performance</h3>
        <PieChart width={400} height={300}>
          <Pie
            data={supplierPerformance}
            cx="50%"
            cy="50%"
            labelLine={false}
            outerRadius={100}
            fill="#8884d8"
            dataKey="value"
            label
          >
            {supplierPerformance.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </div>

      {/* Shipment Status */}
      <div className="report-card">
        <h3>Shipment Status</h3>
        <PieChart width={400} height={300}>
          <Pie
            data={shipmentStatus}
            cx="50%"
            cy="50%"
            innerRadius={60}
            outerRadius={100}
            fill="#82ca9d"
            dataKey="value"
            label
          >
            {shipmentStatus.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </div>
    </div>
  );
};

export default Reports;
