package edu.icet.clothify.repository.custom;

import edu.icet.clothify.dto.Product;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.repository.CrudDao;
import edu.icet.clothify.util.ProductType;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<ProductEntity> getProductsByCategory(Integer productType);
     List<ProductEntity> getProductsByStatus(Integer stock);
     List<ProductEntity> getProductsByStatus();
}
