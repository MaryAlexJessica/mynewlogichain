// mock/User.js
import bcrypt from "bcryptjs";

export const User = [
  {
    email: "admin@test.com",
    password: bcrypt.hashSync("123456", 10), // hash for admin
    role_id: 1,
    username: "Admin"
  },
  {
    email: "manager@test.com",
    password: bcrypt.hashSync("123456", 10), // hash for manager
    role_id: 2,
    username: "Manager"
  },
  {
    email: "selvu14@yahoo.in",
    password: bcrypt.hashSync("123456", 10), // hash for customer
    role_id: 8,
    username: "Selvu"
  }
];
