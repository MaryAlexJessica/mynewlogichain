package com.TradeShift.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.Inventory;
import com.TradeShift.Entity.Products;
import com.TradeShift.Entity.Warehouse;
import com.TradeShift.Repository.InventoryRepository;
import com.TradeShift.Repository.ProductsRepository;
import com.TradeShift.Repository.WarehouseRepository;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepo;

    @Autowired
    private ProductsRepository productRepo;

    @Autowired
    private WarehouseRepository warehouseRepo;

    public InventoryService(InventoryRepository inventoryRepo,
                            ProductsRepository productRepo,
                            WarehouseRepository warehouseRepo) {
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
        this.warehouseRepo = warehouseRepo;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    public List<Inventory> getInventoryByWarehouse(Long warehouseId) {
        return inventoryRepo.findByWarehouse_Id(warehouseId);
    }

    public Inventory addOrUpdateInventory(Long warehouseId, Long productId, int qty) {
        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Products product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // ✅ check if this product already exists in this warehouse
        List<Inventory> existing = inventoryRepo.findByWarehouse_Id(warehouseId);
        for (Inventory inv : existing) {
            if (inv.getProducts().getId() == productId.longValue()) {  // ✅ safe compare
                inv.setQty(inv.getQty() + qty);  // update stock
                return inventoryRepo.save(inv);
            }
        }

        // ✅ create new record if not already present
        Inventory inventory = new Inventory();   // no Lombok builder
        inventory.setWarehouse(warehouse);
        inventory.setProducts(product);
        inventory.setQty(qty);

        return inventoryRepo.save(inventory);
    }


    public Inventory updateInventory(Long id, int qty) {
        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQty(qty);
        return inventoryRepo.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepo.deleteById(id);
    }
}
