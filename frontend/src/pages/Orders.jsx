import React, { useEffect, useState } from "react";
import { getOrders } from "../services/ordersService";
import Table from "../components/common/Table";
import { exportToExcel } from "../Util/exportToExcel";
import "./analytics/Orders.css"; // Create this CSS file similar to Inventory.css

const Orders = () => {
  const [orders, setOrders] = useState([]);

  const handleExport = () => {
    exportToExcel(orders, "Orders_Report");
  };

  useEffect(() => {
    getOrders().then(setOrders);
  }, []);

  return (
    <div className="page-content">
      <div className="orders-header">
        <h2 className="orders-title">Orders</h2>
        <button className="export-btn" onClick={handleExport}>
          📥 Export to Excel
        </button>
      </div>
      <Table data={orders} />
    </div>
  );
};

export default Orders;
