package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.TradeShift.Entity.Warehouse;
import com.TradeShift.Service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/auth/warehouses")
public class WarehouseController {

	@Autowired
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    // POST /api/warehouses (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse,
                                                  @RequestParam Long managerId) {
        return ResponseEntity.ok(service.addWarehouse(warehouse, managerId));
    }

    // GET /api/warehouses (Admin/Manager)
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(service.getAllWarehouses());
    }

    // GET /api/warehouses/{id} (Admin/Manager)
    @PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWarehouse(id));
    }

    // PUT /api/warehouses/{id} (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id,
                                                     @RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(service.updateWarehouse(id, warehouse));
    }

    // DELETE /api/warehouses/{id} (Admin only)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable Long id) {
        service.deleteWarehouse(id);
        return ResponseEntity.ok("ID : " + id + "\n" + "Warehouse deleted successfully");
    }
}
