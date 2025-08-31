package com.TradeShift.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.Products;
import com.TradeShift.Entity.Suppliers;
import com.TradeShift.Repository.ProductsRepository;
import com.TradeShift.Repository.SuppliersRepository;

import java.util.List;

@Service
public class ProductService {
    
	@Autowired
    private ProductsRepository productRepo;
	
	@Autowired
    private SuppliersRepository supplierRepo;

    public ProductService(ProductsRepository productRepo, SuppliersRepository supplierRepo) {
        this.productRepo = productRepo;
        this.supplierRepo = supplierRepo;
    }

    public Products addProduct(Products product, Long supplierId) {
        Suppliers supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        product.setSuppliers(supplier);
        return productRepo.save(product);
    }

    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    public Products getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Products updateProduct(Long id, Products updatedProduct) {
        Products existing = getProductById(id);
        existing.setPname(updatedProduct.getPname());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());
        return productRepo.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
