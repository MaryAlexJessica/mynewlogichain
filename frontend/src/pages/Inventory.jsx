// src/pages/Inventory.jsx
import React, { useEffect, useState } from "react";
import { getInventory } from "../services/inventoryService";
import { exportToExcel } from "../Util/exportToExcel";
import "./analytics/Inventory.css";

const Inventory = () => {
  const [inventory, setInventory] = useState([]);
  const [formData, setFormData] = useState({ id: null, name: "", quantity: "", location: "" });
  const [isEditing, setIsEditing] = useState(false);

  // ✅ Fetch inventory on mount
  useEffect(() => {
    getInventory().then(setInventory);
  }, []);

  // ✅ Export to Excel
  const handleExport = () => {
    if (inventory.length === 0) {
      alert("No inventory data to export!");
      return;
    }
    exportToExcel(inventory, "Inventory_Report");
  };

  // ✅ Handle input change
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // ✅ Add new item
  const handleAdd = () => {
    if (!formData.name || !formData.quantity || !formData.location) return alert("All fields required!");
    const newItem = { ...formData, id: Date.now() }; // temp id
    setInventory([...inventory, newItem]);
    setFormData({ id: null, name: "", quantity: "", location: "" });
  };

  // ✅ Edit item
  const handleEdit = (item) => {
    setFormData(item);
    setIsEditing(true);
  };

  // ✅ Update after editing
  const handleUpdate = () => {
    setInventory(inventory.map((i) => (i.id === formData.id ? formData : i)));
    setFormData({ id: null, name: "", quantity: "", location: "" });
    setIsEditing(false);
  };

  // ✅ Delete item
  const handleDelete = (id) => {
    setInventory(inventory.filter((i) => i.id !== id));
  };

  return (
    <div className="page-content">
      {/* Header with Export */}
      <div className="inventory-header">
       <h2 className="inventory-heading">Inventory</h2>
        <button className="btn" onClick={handleExport}>
          📥 Export to Excel
        </button>
      </div>

      {/* Add / Edit Form */}
      <div className="add-form">
        <input
          type="text"
          name="name"
          className="input-field"
          placeholder="Item Name"
          value={formData.name}
          onChange={handleChange}
        />
        <input
          type="number"
          name="quantity"
          className="input-field"
          placeholder="Quantity"
          value={formData.quantity}
          onChange={handleChange}
          min="0"
        />
        <input
          type="text"
          name="location"
          className="input-field"
          placeholder="Warehouse"
          value={formData.location}
          onChange={handleChange}
        />
        {isEditing ? (
          <button type="button" className ="addbtn align-right" onClick={handleUpdate}>✔ Update</button>
        ) : (
          <button type="button" className ="addbtn align-right" onClick={handleAdd}>➕ Add</button>
        )}
      </div>
      {/* Table */}
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>NAME</th>
              <th>QUANTITY</th>
              <th>LOCATION</th>
              <th>ACTIONS</th>
            </tr>
          </thead>
          <tbody>
            {inventory.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td
                  className={
                    item.quantity === 0
                      ? "stock-out"
                      : item.quantity <= 10
                      ? "low-stock"
                      : ""
                  }
                >
                  {item.quantity}
                </td>
                <td>{item.location}</td>
                <td>
                  <span
    className="action-link edit-link"
    onClick={() => handleEdit(item)}
  >
    Edit
  </span>
  {" | "}
  <span
    className="action-link delete-link"
    onClick={() => handleDelete(item.id)}
  >
    Delete
  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Inventory;
