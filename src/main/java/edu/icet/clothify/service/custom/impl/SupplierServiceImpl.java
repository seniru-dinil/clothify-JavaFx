package edu.icet.clothify.service.custom.impl;

import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.SupplierDao;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.enums.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> list = new ArrayList<>();
         SupplierDao supplierDao =DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        List<SupplierEntity> all = supplierDao.getAll();
        all.forEach(sup->list.add(new ModelMapper().map(sup,Supplier.class)));
        return list;
    }

    @Override
    public boolean saveSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return supplierDao.save(new ModelMapper().map(supplier,SupplierEntity.class));
    }

    @Override
    public boolean deleteSupplier(Integer id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return supplierDao.delete(id);
    }

    @Override
    public Supplier getSupplier(String id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return new ModelMapper().map(supplierDao.get(id),Supplier.class);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        return supplierDao.update(new ModelMapper().map(supplier,SupplierEntity.class));
    }

    @Override
    public List<Supplier> getSuppliersByName(String name) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);
        List<SupplierEntity> suppliersByName = supplierDao.getSuppliersByName(name);
        List<Supplier> list = new ArrayList<>();
        suppliersByName.forEach(s->list.add(new ModelMapper().map(s,Supplier.class)));
        return list;
    }
}
