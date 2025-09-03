// src/config/roleRoute.js
export const roleRoutes = {
    1: { // Admin
        default: "/dashboard",
        sidebar: [
            { path: "/dashboard", label: "Dashboard", icon: "🏠" },
            { path: "/inventory", label: "Inventory", icon: "📦" },
            { path: "/suppliers", label: "Suppliers", icon: "🚚" },
            { path: "/reports", label: "Reports", icon: "📊" }
        ]
    },
    2: { // Manager
        default: "/orders",
        sidebar: [
            { path: "/orders", label: "Orders", icon: "🛒" },
            { path: "/shipments", label: "Shipment Status", icon: "📦" }
        ]
    },
    3: { // Procurement Manager
        default: "/orders",
        sidebar: [
            { path: "/orders", label: "Orders", icon: "🛒" }
        ]
    },
    4: { // Sales
        default: "/orders",
        sidebar: [
            { path: "/orders", label: "Orders", icon: "🛒" }
        ]
    },
    8: { // Customer
        default: "/shop",
        sidebar: [
            { path: "/shop", label: "Shop", icon: "🛍️" }
        ]
    }
};
