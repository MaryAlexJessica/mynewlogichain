import React, { useEffect, useState } from "react";
import { getOrders } from "../services/ordersService";
import { exportToExcel } from "../Util/exportToExcel";
import OrderDetailsModal from "./OrderDetailsModal";
import "../pages/analytics/Orders.css";

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [selectedOrder, setSelectedOrder] = useState(null); // order to show in modal
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    getOrders().then(setOrders);
  }, []);

  const handleExport = () => {
    if (orders.length === 0) {
      alert("No orders to export!");
      return;
    }
    exportToExcel(orders, "Orders_Report");
  };

  const handleViewDetails = (order) => {
    setSelectedOrder(order);
    setModalOpen(true);
  };

  const closeModal = () => {
    setSelectedOrder(null);
    setModalOpen(false);
  };

  return (
    <div className="page-content">
      <div className="orders-header">
        <h2 className="orders-title">Orders</h2>
        <button className="export-btn" onClick={handleExport}>📥 Export to Excel</button>
      </div>

      <table className="orders-table">
  <thead>
    <tr>
      <th>Order ID</th>
      <th>Customer Name</th>
      <th>Order Date</th>
      <th>Total Amount</th>
      <th>Status</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
    {orders.map((order) => (
      <tr key={order.order_id}>
        <td>{order.order_id}</td>
        <td>{order.customer_name}</td>
        <td>{new Date(order.order_date).toLocaleDateString()}</td>
        <td>{order.total_amount.toFixed(2)}</td>
        <td>{order.status}</td>
        <td>
          <button className="btn small-btn" onClick={() => handleViewDetails(order)}>
            View Details
          </button>
        </td>
      </tr>
    ))}
  </tbody>
</table>

      {modalOpen && selectedOrder && (
        <OrderDetailsModal order={selectedOrder} onClose={closeModal} />
      )}
    </div>
  );
};

export default Orders;
