package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.SupperService;

import java.util.List;

public interface SupplierService extends SupperService {
    List<Supplier> getAllSuppliers();
    boolean saveSupplier(Supplier supplier);
    boolean deleteSupplier(Integer id);
    Supplier getSupplier(String id);
    boolean updateSupplier(Supplier supplier);
    List<Supplier> getSuppliersByName(String name);
    Integer getTotalSupplierCount();
}
