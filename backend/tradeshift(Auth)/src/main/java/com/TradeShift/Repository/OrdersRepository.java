package com.TradeShift.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.Orders;

import jakarta.transaction.Transactional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // delete all orders for a user
    @Transactional
    void deleteByPlacedBy_Id(Long userId);
    
    List<Orders> findByPlacedByUsername(String username);
    
    List<Orders> findByPlacedById(Long userId);
}
