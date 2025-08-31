package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.TradeShift.Entity.Products;
import com.TradeShift.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/auth/products")
public class ProductController {

	@Autowired
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. Add Product (Admin / Manager)
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    public ResponseEntity<Products> addProduct(@RequestBody Products product, @RequestParam Long supplierId) {
        return ResponseEntity.ok(productService.addProduct(product, supplierId));
    }

    // 2. Browse Products (All users)
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 3. Get Product Details (All users)
    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // 4. Update Product (Manager only)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // 5. Delete Product (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    	productService.deleteProduct(id);
    	return ResponseEntity.ok("ID : " + id + "\n Product deleted successfully");
    }
}
