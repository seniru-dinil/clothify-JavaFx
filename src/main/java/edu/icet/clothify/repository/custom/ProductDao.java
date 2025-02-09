package edu.icet.clothify.repository.custom;

import edu.icet.clothify.entity.MostPurchasedProductEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.repository.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<ProductEntity> getProductsByCategory(Integer productType);

    List<ProductEntity> getProductsByStatus(Integer stock);

    List<ProductEntity> getProductsByStatus();

    List<ProductEntity> getProductsByName(String status);

    List<MostPurchasedProductEntity> getMostPurchasedProducts();

    boolean updateProductByQuantity(Integer qty,Integer pid);

    boolean updateProductByPrice(Double price, Integer pid);

    Integer getTotalProductCount();
}
