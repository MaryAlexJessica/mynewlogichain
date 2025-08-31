package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.TradeShift.Entity.Inventory;
import com.TradeShift.Service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/auth/inventory")
public class InventoryController {

	@Autowired
    private InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // GET /api/inventory (Admin/Manager)
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(service.getAllInventory());
    }

    // GET /api/inventory/{warehouseId}
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping("/{warehouseId}")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(service.getInventoryByWarehouse(warehouseId));
    }

    // POST /api/inventory (Admin only) → add/update stock
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestParam Long warehouseId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int qty) {
        return ResponseEntity.ok(service.addOrUpdateInventory(warehouseId, productId, qty));
    }

    // PUT /api/inventory/{id} (Admin only) → update quantity
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id,
                                                     @RequestParam int qty) {
        return ResponseEntity.ok(service.updateInventory(id, qty));
    }

    // DELETE /api/inventory/{id} (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        service.deleteInventory(id);
        return ResponseEntity.ok("ID : " + id + "\nInventory record deleted successfully");
    }
}
