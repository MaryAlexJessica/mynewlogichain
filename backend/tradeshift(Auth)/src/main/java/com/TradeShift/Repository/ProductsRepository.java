package com.TradeShift.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
