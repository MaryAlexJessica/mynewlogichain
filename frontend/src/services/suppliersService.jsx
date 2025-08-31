import { suppliersData } from "../mock/suppliers";

export const getSuppliers = () => {
  return new Promise((resolve) => {
    setTimeout(() => resolve(suppliersData), 500);
  });
};
