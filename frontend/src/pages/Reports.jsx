// src/pages/Reports.jsx
import React, { useRef } from "react";
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  PieChart,
  Pie,
  Cell,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  CartesianGrid,
} from "recharts";
import html2canvas from "html2canvas";
import jsPDF from "jspdf";
import "../pages/analytics/Reports.css";
import {
  inventoryTrends,
  ordersData,
  supplierPerformance,
  shipmentStatus,
} from "../mock/Reports";

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042"];

const Reports = () => {
  const reportRef = useRef();

  const handleExportPDF = () => {
    const input = reportRef.current;
    html2canvas(input, { scale: 2 }).then((canvas) => {
      const imgData = canvas.toDataURL("image/png");
      const pdf = new jsPDF("p", "mm", "a4");
      const pageWidth = pdf.internal.pageSize.getWidth();
      const pageHeight = pdf.internal.pageSize.getHeight();

      const imgWidth = pageWidth;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;

      let position = 0;
      if (imgHeight < pageHeight) {
        pdf.addImage(imgData, "PNG", 0, 0, imgWidth, imgHeight);
      } else {
        // split into multiple pages if content is tall
        let heightLeft = imgHeight;
        let y = 0;

        while (heightLeft > 0) {
          pdf.addImage(imgData, "PNG", 0, y, imgWidth, imgHeight);
          heightLeft -= pageHeight;
          y -= pageHeight;
          if (heightLeft > 0) pdf.addPage();
        }
      }
      pdf.save("Reports.pdf");
    });
  };

  return (
    <div className="reports-container">
      <div className="reports-header">
        <h2 className="reports-title">📊 Reports & Analytics</h2>
        <button className="export-btn" onClick={handleExportPDF}>
          Export All Reports to PDF
        </button>
      </div>

      {/* All reports wrapper */}
      <div ref={reportRef}>
        <div className="reports-grid">
          {/* Inventory Trends */}
          <div className="report-card">
            <h3>Inventory Trends</h3>
            <LineChart width={400} height={250} data={inventoryTrends}>
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
            <BarChart width={400} height={250} data={ordersData}>
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
            <PieChart width={350} height={250}>
              <Pie
                data={supplierPerformance}
                cx="50%"
                cy="50%"
                outerRadius={80}
                dataKey="value"
                label
              >
                {supplierPerformance.map((entry, index) => (
                  <Cell
                    key={`cell-${index}`}
                    fill={COLORS[index % COLORS.length]}
                  />
                ))}
              </Pie>
              <Tooltip />
              <Legend />
            </PieChart>
          </div>

          {/* Shipment Status */}
          <div className="report-card">
            <h3>Shipment Status</h3>
            <PieChart width={350} height={250}>
              <Pie
                data={shipmentStatus}
                cx="50%"
                cy="50%"
                innerRadius={50}
                outerRadius={80}
                dataKey="value"
                label
              >
                {shipmentStatus.map((entry, index) => (
                  <Cell
                    key={`cell-${index}`}
                    fill={COLORS[index % COLORS.length]}
                  />
                ))}
              </Pie>
              <Tooltip />
              <Legend />
            </PieChart>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Reports;
