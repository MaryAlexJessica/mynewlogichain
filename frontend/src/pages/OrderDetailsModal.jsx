import React from "react";
import "../pages/analytics/OrderDetailsModal.css"; // add basic modal styles

const OrderDetailsModal = ({ order, onClose }) => {
  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h3>Order Details: {order.orderNumber}</h3>
        <table className="details-table">
          <thead>
            <tr>
              <th>Product</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Total</th>
            </tr>
          </thead>
          <tbody>
            {order.details.map((item, index) => (
              <tr key={index}>
                <td>{item.product}</td>
                <td>{item.quantity}</td>
                <td>{item.price}</td>
                <td>{item.total}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <button className="btn" onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

export default OrderDetailsModal;
