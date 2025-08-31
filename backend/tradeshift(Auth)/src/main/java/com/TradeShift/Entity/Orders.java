package com.TradeShift.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Orders {

	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "placed_by", nullable = false, foreignKey = @ForeignKey(name = "fk_orders_users"))
    private User placedBy;
	
	@Column(name = "customer_name", nullable = false, length = 100)
	private String cname;
	
//	@Column(name = "order_date", insertable = true, updatable = true)
	@CreationTimestamp
	@Column(name = "order_date", updatable = true)
    private Timestamp orderDate;
	
	@Column(name = "total_amount", nullable = false)
	private BigDecimal tamount;
	
	public enum OrderStatus {
		Pending, Processing, Shipped, Delivered, Cancelled
	}

	@Enumerated(EnumType.STRING)
    private OrderStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getPlacedBy() {
		return placedBy;
	}

	public void setPlacedBy(User placedBy) {
		this.placedBy = placedBy;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTamount() {
		return tamount;
	}

	public void setTamount(BigDecimal tamount) {
		this.tamount = tamount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", placedBy=" + placedBy + ", cname=" + cname + ", orderDate=" + orderDate
				+ ", tamount=" + tamount + ", status=" + status + "]";
	}
	
}




//CREATE TABLE orders (
//	    order_id INT PRIMARY KEY AUTO_INCREMENT,
//	    placed_by INT NOT NULL, -- user_id from users table
//	    customer_name VARCHAR(100) NOT NULL,
//	    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
//	    total_amount DECIMAL(10,2) NOT NULL,
//	    status ENUM('Pending', 'Processing', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
//	    FOREIGN KEY (placed_by) REFERENCES users(user_id)
//	);

