import React, { useEffect, useState } from "react";
import { getShipmentStatus } from "../services/shipmentsService";
import Table from "../components/common/Table";
import "./analytics/ShipmentStatus.css"; // Create this CSS file similar to Inventory.css

const Shipments = () => {
  const [shipmentStatus, setShipmentStatus] = useState([]);

  useEffect(() => {
    getShipmentStatus().then(setShipmentStatus);
  }, []);

  return (
    <div className="page-content">
      <h2>Shipment Status</h2>
      <Table data={shipmentStatus} />
    </div>
  );
};

export default Shipments;
