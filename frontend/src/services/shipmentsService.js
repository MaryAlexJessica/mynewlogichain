import { shipmentsData } from "../mock/shipments";
export const getShipmentStatus = () => new Promise(resolve => setTimeout(() => resolve(shipmentsData), 500));