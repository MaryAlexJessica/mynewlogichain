import React, { useEffect, useState } from "react";
import { getSuppliers } from "../services/suppliersService";
import Table from "../components/common/Table";
import { exportToExcel } from "../Util/exportToExcel";

const Suppliers = () => {
  const [suppliers, setSuppliers] = useState([]);

  const handleExport = () => {
    exportToExcel(suppliers, "Suppliers_Report");
  };

  useEffect(() => {
    getSuppliers().then(setSuppliers);
  }, []);

  return (
    <div className="page-content">
      <div className="header-with-btn">
        <h2>Suppliers</h2>
        <button className="view-all-btn" onClick={handleExport}>
          📥 Export to Excel
        </button>
      </div>
      <Table data={suppliers} />
    </div>
  );
};

export default Suppliers;
