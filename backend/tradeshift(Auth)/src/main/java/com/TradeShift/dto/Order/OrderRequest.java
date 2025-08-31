package com.TradeShift.dto.Order;

import java.math.BigDecimal;

public class OrderRequest {         // id of the user who placed the order
    private String customerName;  // cname
    private BigDecimal totalAmount; // tamount
    private String status;
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderRequest [customerName=" + customerName + ", totalAmount="
				+ totalAmount + ", status=" + status + "]";
	}  
}
