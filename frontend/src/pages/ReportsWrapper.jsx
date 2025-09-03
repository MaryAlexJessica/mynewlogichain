// src/pages/ReportsWrapper.jsx
import React, { useRef } from "react";
import Reports from "./Reports";
import { useReactToPrint } from "react-to-print";

const ReportsWrapper = () => {
  const reportsRef = useRef();

  const handlePrint = useReactToPrint({
    content: () => reportsRef.current,
    documentTitle: "SCM Reports",
    pageStyle: `
      @page {
        size: A4 landscape;
        margin: 20px;
      }
      body {
        -webkit-print-color-adjust: exact !important;
        color-adjust: exact !important;
        font-family: Arial, sans-serif;
      }
    `,
  });

  return (
    <div>
      {/* ✅ Export button outside the reports */}
      <div style={{ textAlign: "right", marginBottom: "10px" }}>
        <button
          onClick={handlePrint}
          style={{
            background: "#4CAF50",
            color: "white",
            border: "none",
            padding: "8px 16px",
            borderRadius: "6px",
            cursor: "pointer",
          }}
        >
          📄 Export All Reports to PDF
        </button>
      </div>

      {/* ✅ Reports content for export */}
      <div ref={reportsRef}>
        <Reports />
      </div>
    </div>
  );
};

export default ReportsWrapper;
