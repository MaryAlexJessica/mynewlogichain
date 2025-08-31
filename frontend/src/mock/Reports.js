// src/mock/reports.js

export const inventoryTrends = [
  { month: "Mar", stock: 400 },
  { month: "Apr", stock: 350 },
  { month: "May", stock: 300 },
  { month: "Jun", stock: 450 },
  { month: "Jul", stock: 500 },
  { month: "Aug", stock: 420 },
];

export const ordersData = [
  { month: "Mar", completed: 40, pending: 10, cancelled: 5 },
  { month: "Apr", completed: 50, pending: 8, cancelled: 2 },
  { month: "May", completed: 30, pending: 15, cancelled: 7 },
  { month: "Jun", completed: 60, pending: 5, cancelled: 3 },
  { month: "Jul", completed: 55, pending: 12, cancelled: 4 },
  { month: "Aug", completed: 70, pending: 9, cancelled: 1 },
];

export const supplierPerformance = [
  { name: "Supplier A", value: 45 },
  { name: "Supplier B", value: 25 },
  { name: "Supplier C", value: 20 },
  { name: "Supplier D", value: 10 },
];

export const shipmentStatus = [
  { name: "Delivered", value: 60 },
  { name: "In-Transit", value: 25 },
  { name: "Delayed", value: 15 },
];
