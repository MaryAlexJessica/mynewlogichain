import { roles } from "../mock/Roles";

// ✅ Mimic an API call with Promise
export const getRoles = () =>
  new Promise((resolve) => {
    setTimeout(() => {
      resolve(roles);
    }, 300); // small delay to simulate fetch
  });