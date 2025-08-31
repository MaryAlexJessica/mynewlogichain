import {
  inventoryTrends,
  ordersData,
  supplierPerformance,
  shipmentStatus,
} from "../mock/Reports";

export const getReports = () =>
  new Promise((resolve) => {
    setTimeout(() => {
      resolve({ inventoryTrends, ordersData, supplierPerformance, shipmentStatus });
    }, 500); // fake delay
  });
