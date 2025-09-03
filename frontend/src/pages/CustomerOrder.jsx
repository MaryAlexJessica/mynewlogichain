// src/pages/CustomerOrder.jsx
import React, { useEffect, useState } from "react";
import { getOrders } from "../services/ordersService";
import "../pages/analytics/Orders.css"; // reuse minimal CSS or create separate

const CustomerOrder = () => {
    const [orders, setOrders] = useState([]);
    const user = JSON.parse(localStorage.getItem("user"));

    useEffect(() => {
        getOrders().then((allOrders) => {
            console.log("allOrders", allOrders);
            // Filter orders for logged-in customer
            const customerOrders = allOrders.filter(
                (order) => order.customer_email === user.email
            );
            setOrders(customerOrders);
        });
    }, [user]);

    if (orders.length === 0) {
        return <div className="page-content">You have no orders yet.</div>;
    }

    return (
        <div className="page-content">
            <h2>My Orders</h2>
            <div className="customer-orders">
                {orders.map((order) => (
                    <div key={order.order_id} className="order-card">
                        <p><strong>Order #:</strong> {order.order_id}</p>
                        <p><strong>Date:</strong> {new Date(order.order_date).toLocaleDateString()}</p>
                        <p><strong>Status:</strong> {order.status}</p>
                        <p><strong>Total:</strong> ₹{order.total_amount.toFixed(2)}</p>
                        <button className="btn small-btn" onClick={() => alert("View Details coming soon!")}>
                            View Details
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default CustomerOrder;
