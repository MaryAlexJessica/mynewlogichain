package com.TradeShift.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findByWarehouse_Id(Long warehouseId);
}
