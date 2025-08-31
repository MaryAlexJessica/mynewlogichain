package com.TradeShift.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.Suppliers;
import com.TradeShift.Repository.SuppliersRepository;

import java.util.List;

@Service
public class SupplierService {

	@Autowired
    private SuppliersRepository supplierRepository;

    public SupplierService(SuppliersRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Suppliers addSupplier(Suppliers supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Suppliers> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Suppliers updateSupplier(Long id, Suppliers supplierDetails) {
        Suppliers supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setSname(supplierDetails.getSname());
        supplier.setCname(supplierDetails.getCname());
        supplier.setCmail(supplierDetails.getCmail());
        supplier.setCnumber(supplierDetails.getCnumber());
        supplier.setAdd(supplierDetails.getAdd());
        supplier.setCity(supplierDetails.getCity());
        supplier.setCountry(supplierDetails.getCountry());

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        supplierRepository.deleteById(id);
    }
}
