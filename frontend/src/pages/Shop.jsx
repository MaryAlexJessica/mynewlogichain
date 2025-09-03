// src/pages/Shop.jsx
import React, { useState } from "react";
import { products } from "../mock/Products"; // your mock product data
import { categories } from "../mock/Categories"; // your mock category data
import "../pages/analytics/Shop.css"; // import CSS

const Shop = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);

  // Filter products by category
  const filteredProducts = selectedCategory
    ? products.filter((p) => p.category_id === selectedCategory)
    : products;

  return (
    <div className="shop-container">
      {/* Page Title */}
      <h1 className="shop-title">🛒 Shop Products</h1>

      {/* Category Filter Buttons */}
      <div className="category-filters">
        <button
          onClick={() => setSelectedCategory(null)}
          className={`category-btn ${
            selectedCategory === null ? "active" : ""
          }`}
        >
          All
        </button>
        {categories.map((cat) => (
          <button
            key={cat.category_id}
            onClick={() => setSelectedCategory(cat.category_id)}
            className={`category-btn ${
              selectedCategory === cat.category_id ? "active" : ""
            }`}
          >
            {cat.category_name}
          </button>
        ))}
      </div>

      {/* Product Grid */}
      <div className="product-grid">
        {filteredProducts.map((product) => (
          <div key={product.product_id} className="product-card">
            {/* Mock product image */}
            <div className="product-image">
              <span>📦 {product.product_name}</span>
            </div>

            <h3 className="product-name">{product.product_name}</h3>
            <p className="product-desc">{product.description}</p>

            <div className="product-price">₹{product.unit_price}</div>

            <button className="add-cart-btn">Add to Cart</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Shop;
