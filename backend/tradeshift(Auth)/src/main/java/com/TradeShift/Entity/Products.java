package com.TradeShift.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Products {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "product_name", nullable = false, length = 100)
	private String pname;
	
	@Column(name = "description")
	private String description;
	
	// suppliers
	@ManyToOne
	@JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_products_suppliers"))
	private Suppliers suppliers;

	@Column(name = "unit_price")
	private BigDecimal price;
	
	@Column(name = "quantity_in_stock")
	private long stock;
	
	@Column(name = "expiry_date", nullable = false)
	private Date date;
	
	@Column(name = "created_at", updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}


	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", pname=" + pname + ", description=" + description + ", suppliers=" + suppliers
				+ ", price=" + price + ", stock=" + stock + ", date=" + date + ", createdAt=" + createdAt + "]";
	}
}




//CREATE TABLE products (
//	    product_id INT PRIMARY KEY AUTO_INCREMENT,
//	    product_name VARCHAR(100) NOT NULL,
//	    description TEXT,
//	    supplier_id INT,
//	    unit_price DECIMAL(10,2),
//	    quantity_in_stock INT,
//	    expiry_date DATE NOT NULL,
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
//	);

