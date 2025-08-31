package com.TradeShift.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.User;
import com.TradeShift.Entity.Warehouse;
import com.TradeShift.Repository.UserRepository;
import com.TradeShift.Repository.WarehouseRepository;

import java.util.List;

@Service
public class WarehouseService {

	@Autowired
    private WarehouseRepository warehouseRepo;
	
	@Autowired
    private UserRepository userRepo;

    public WarehouseService(WarehouseRepository warehouseRepo, UserRepository userRepo) {
        this.warehouseRepo = warehouseRepo;
        this.userRepo = userRepo;
    }

    public Warehouse addWarehouse(Warehouse warehouse, Long managerId) {
        User manager = userRepo.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        warehouse.setManager(manager);
        return warehouseRepo.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepo.findAll();
    }

    public Warehouse getWarehouse(Long id) {
        return warehouseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
    }

    public Warehouse updateWarehouse(Long id, Warehouse updated) {
        Warehouse warehouse = getWarehouse(id);
        warehouse.setWname(updated.getWname());
        warehouse.setLocation(updated.getLocation());
        warehouse.setCapacity(updated.getCapacity());
        if (updated.getManager() != null) {
            warehouse.setManager(updated.getManager());
        }
        return warehouseRepo.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepo.deleteById(id);
    }
}
