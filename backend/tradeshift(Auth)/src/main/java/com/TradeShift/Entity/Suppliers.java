package com.TradeShift.Entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Suppliers {

	@Id
	@Column(name="supplier_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="supplier_name", nullable = false, length = 100)
	private String sname;
	
	@Column(name="contact_name")
	private String cname;
	
	@Column(name="contact_email")
	private String cmail;
	
	@Column(name="contact_phone")
	private long cnumber;
	
	@Column(name="address")
	private String add;
	
	private String city;
	
	private String country;
	
	@Column(name="created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;

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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCmail() {
		return cmail;
	}

	public void setCmail(String cmail) {
		this.cmail = cmail;
	}

	public long getCnumber() {
		return cnumber;
	}

	public void setCnumber(long cnumber) {
		this.cnumber = cnumber;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Suppliers [id=" + id + ", sname=" + sname + ", cname=" + cname + ", cmail=" + cmail + ", cnumber="
				+ cnumber + ", add=" + add + ", city=" + city + ", country=" + country + ", createdAt=" + createdAt
				+ "]";
	}
}




//CREATE TABLE suppliers (
//	    supplier_id INT PRIMARY KEY AUTO_INCREMENT,
//	    supplier_name VARCHAR(100) NOT NULL,
//	    contact_name VARCHAR(100),
//	    contact_email VARCHAR(100),
//	    contact_phone VARCHAR(15),
//	    address TEXT,
//	    city VARCHAR(50),
//	    country VARCHAR(50),
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//	);

