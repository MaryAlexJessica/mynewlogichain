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
public class Warehouse {

	@Id
	@Column(name = "warehouse_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "warehouse_name", nullable = false)
	private String wname;
	
	@Column(nullable = false)
	private String location;
	
	@Column(nullable = false)
	private long capacity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", wname=" + wname + ", location=" + location + ", capacity=" + capacity
				+ ", manager=" + managerId + "]";
	}
	
	@ManyToOne
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "fk_warehouse_users"))
    private User managerId;

	public User getManager() {
		return managerId;
	}

	public void setManager(User managerId) {
		this.managerId = managerId;
	}
}




//CREATE TABLE warehouse (
//	    warehouse_id INT PRIMARY KEY AUTO_INCREMENT,   -- Unique ID for each warehouse
//	    warehouse_name VARCHAR(100) NOT NULL,          -- Name of the warehouse
//	    location VARCHAR(255) NOT NULL,                -- Full address/location
//	    capacity INT NOT NULL,                         -- Maximum number of items it can store
//	    manager_id INT,                                -- The user managing this warehouse
//	    FOREIGN KEY (manager_id) REFERENCES users(user_id) -- Linking to users table
//	);
