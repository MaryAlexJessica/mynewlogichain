// src/jsx/Header.jsx
import React from 'react';

export default function Header() {
  return (
    <header className="bg-white shadow flex justify-between items-center px-6 py-4">
      {/* Logo / App Name */}
      <div className="text-2xl font-bold text-gray-800">LogiChain</div>

      {/* Page Title */}
      <div className="text-xl text-gray-600">Dashboard</div>

      {/* User Info / Notifications */}
      <div className="flex items-center space-x-4">
        {/* Notifications placeholder */}
        <button className="relative">
          🔔
          <span className="absolute top-0 right-0 inline-block w-2 h-2 bg-red-500 rounded-full"></span>
        </button>
        {/* User info placeholder */}
        <div className="flex items-center space-x-2">
          <div className="w-8 h-8 bg-gray-300 rounded-full"></div>
          <span>Admin</span>
        </div>
      </div>
    </header>
  );
}