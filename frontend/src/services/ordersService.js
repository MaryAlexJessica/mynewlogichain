import { ordersData } from "../mock/orders";
export const getOrders = () => new Promise(resolve => setTimeout(() => resolve(ordersData), 500));