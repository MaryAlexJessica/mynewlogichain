package com.TradeShift.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	
    // get all details for a given orderId
    List<OrderDetails> findByOrders_Id(Long orderId);

    // get all details for a given productId (optional, if needed)
    List<OrderDetails> findByProducts_Id(Long productId);
}
