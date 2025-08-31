// src/pages/Inventory.jsx
import React, { useEffect, useState } from "react";
import { getInventory } from "../services/inventoryService";
import Table from "../components/common/Table";
import { exportToExcel } from "../Util/exportToExcel";
import "./analytics/Inventory.css";

const Inventory = () => {
  const [inventory, setInventory] = useState([]);

  const handleExport = () => {
    exportToExcel(inventory, "Inventory_Report");
  };

  useEffect(() => {
    getInventory().then(setInventory);
  }, []);

  return (
      <div className="page-content">
        <div className="inventory-header">
          <h2 className="inventory-title">Inventory</h2>
          <button className="export-btn" onClick={handleExport}>
            📥 Export to Excel
          </button>
        </div>
        <Table data={inventory} />
      </div>
  );
};

export default Inventory;
