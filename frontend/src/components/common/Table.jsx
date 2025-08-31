import React from "react";
import "./Table.css"; // <-- your common CSS

const Table = ({ data }) => {
  if (!data || data.length === 0) return <p>No data available</p>;

  const headers = Object.keys(data[0]);

  return (
    <div className="table-wrapper">
      <table>
        <thead>
          <tr>
            {headers.map((h) => (
              <th key={h}>{h.toUpperCase()}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {data.map((row, idx) => (
            <tr key={idx}>
              {headers.map((h) => (
                <td
                  key={h}
                  className={
                    h === "quantity" && row[h] === 0
                      ? "stock-out"
                      : h === "quantity" && row[h] <= 10
                      ? "low-stock"
                      : ""
                  }
                >
                  {typeof row[h] === "object" ? JSON.stringify(row[h]) : row[h]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Table;
