// src/config/sidebarLinks.js
export const sidebarLinks = {
    1: [ // admin
        { path: "/dashboard", label: "Dashboard", icon: "📊" },
        { path: "/inventory", label: "Inventory", icon: "📦" },
        { path: "/orders", label: "Orders", icon: "🛒" },
        { path: "/suppliers", label: "Suppliers", icon: "🏭" },
    ],
    2: [ // manager
        { path: "/orders", label: "Orders", icon: "🛒" },
        { path: "/shipments", label: "Shipments", icon: "🚚" },
    ],
    8: [ // Customer
        { path: "/shop", label: "Shop", icon: "🛍️" },
        { path: "/customer-orders", label: "Orders", icon: "🛒" },           // Essential
        { path: "/cart", label: "Cart", icon: "🛍️" },              // Essential
        { path: "/wishlist", label: "Wishlist", icon: "💖" },       // Optional
        { path: "/notifications", label: "Notifications", icon: "🔔" }, // Optional
    ],
};
