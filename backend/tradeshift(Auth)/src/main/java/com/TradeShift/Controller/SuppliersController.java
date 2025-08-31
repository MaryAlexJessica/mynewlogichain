package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.TradeShift.Entity.Suppliers;
import com.TradeShift.Service.SupplierService;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/auth/suppliers")
public class SuppliersController {

		@Autowired
	    private SupplierService supplierService;

	    public SuppliersController(SupplierService supplierService) {
	        this.supplierService = supplierService;
	    }

	    // ✅ Add new supplier (Admin only)
	    @PreAuthorize("hasAuthority('ADMIN')")
	    @PostMapping("/add")
	    public ResponseEntity<?> addSupplier(@RequestBody Suppliers supplier) {
	        supplierService.addSupplier(supplier);
//	    	return ResponseEntity.ok(supplierService.addSupplier(supplier));
	    	return ResponseEntity.ok("Supplier added success");
	    }

	    // ✅ List all suppliers (Admin + Manager)
	    @GetMapping
	    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
	    public ResponseEntity<List<Suppliers>> getAllSuppliers() {
	        return ResponseEntity.ok(supplierService.getAllSuppliers());
	    }

	    // ✅ Update supplier (Admin only)
	    @PutMapping("/{id}")
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<Suppliers> updateSupplier(@PathVariable Long id, @RequestBody Suppliers supplierDetails) {
	        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDetails));
	    }

	    // ✅ Delete supplier (Admin only)
	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasAuthority('ADMIN')")
	    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
	        supplierService.deleteSupplier(id);
	        return ResponseEntity.ok("ID : " + id + "\n Supplier deleted successfully");
	    }
	}
