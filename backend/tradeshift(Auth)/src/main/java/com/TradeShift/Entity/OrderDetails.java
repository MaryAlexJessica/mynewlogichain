package com.TradeShift.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderDetails {

	@Id
	@Column(name = "order_detail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "fk_order_details_orders"))
    private Orders orders;
	
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_order_details_products"))
    private Products products;
	
	@Column(nullable = false)
	private long qty;
	
	@Column(name = "unit_price", nullable = false)
	private BigDecimal price;
	
	// subtotal will be calculated in DB, so no setter
    @Column(name = "subtotal", updatable = false)
    private BigDecimal subtotal;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Transient
	public BigDecimal getSubtotal() {
	    if (price == null) return BigDecimal.ZERO;
	    return price.multiply(BigDecimal.valueOf(qty));
	}


	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orders=" + orders + ", products=" + products + ", qty=" + qty + ", price="
				+ price + ", subtotal=" + subtotal + "]";
	}

}




//CREATE TABLE order_details (
//	    order_detail_id INT PRIMARY KEY AUTO_INCREMENT,
//	    order_id INT NOT NULL,
//	    product_id INT NOT NULL,
//	    quantity INT NOT NULL,
//	    unit_price DECIMAL(10,2) NOT NULL,
//	    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (quantity * unit_price) STORED,
//	    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
//	    FOREIGN KEY (product_id) REFERENCES products(product_id)
//	);
