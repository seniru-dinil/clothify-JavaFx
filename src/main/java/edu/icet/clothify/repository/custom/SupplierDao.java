package edu.icet.clothify.repository.custom;

import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.repository.CrudDao;

import java.util.List;

public interface SupplierDao extends CrudDao<SupplierEntity> {
    List<SupplierEntity> getSuppliersByName(String name);
    Integer getTotalSupplierCount();
}
