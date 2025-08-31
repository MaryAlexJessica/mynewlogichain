// src/services/userProfileService.js

// Mock data file (you can also keep an in-memory array)
import { User } from "../mock/User";

// Get all users
export const getUser = async () => {
  // Simulate API call delay
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(User);
    }, 200);
  });
};

// Save a new user
export const saveUser = async (newUser) => {
  return new Promise((resolve, reject) => {
    try {
      // Check if email already exists
      const exists = User.some(u => u.email === newUser.email);
      if (exists) {
        reject(new Error("Email already registered"));
      } else {
        User.push(newUser); // add new user to mock array
        resolve(newUser);
      }
    } catch (err) {
      reject(err);
    }
  });
};
