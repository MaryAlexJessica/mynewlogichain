package com.TradeShift.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Inventory {

	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_inventory_products"))
	private Products products;
	
	@ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false, foreignKey = @ForeignKey(name = "fk_inventory_warehouse"))
    private Warehouse warehouse;

	@Column(name = "quantity")
	private long qty;
	
    // ✅ No-args constructor (required by JPA and for `new Inventory()`)
    public Inventory() {}
	
	public Inventory(long id, Products products, Warehouse warehouse, long qty) {
		super();
		this.id = id;
		this.products = products;
		this.warehouse = warehouse;
		this.qty = qty;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", products=" + products + ", warehouse=" + warehouse + ", qty=" + qty + "]";
	}
}





//CREATE TABLE inventory (
//	    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
//	    product_id INT NOT NULL,
//	    warehouse_id INT NOT NULL,
//	    quantity INT NOT NULL DEFAULT 0,
//	    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//	    FOREIGN KEY (product_id) REFERENCES products(product_id),
//	    FOREIGN KEY (warehouse_id) REFERENCES warehouse(warehouse_id)
//	);
