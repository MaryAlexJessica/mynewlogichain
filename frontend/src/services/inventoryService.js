import { inventoryData } from "../mock/inventory";
export const getInventory = () => new Promise(resolve => setTimeout(() => resolve(inventoryData), 500));