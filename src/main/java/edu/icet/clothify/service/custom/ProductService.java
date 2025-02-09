package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Product;
import edu.icet.clothify.entity.MostPurchasedProductEntity;
import edu.icet.clothify.service.SupperService;

import java.util.List;

public interface ProductService extends SupperService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean deleteProduct(Integer id);
    Product getProduct(String name);
    boolean updateProduct(Product product);
    List<Product> getProductsByCategory(Integer productType);
     List<Product> getProductsByStatus(String status);
     List<Product> getProductsByName(String status);
    List<MostPurchasedProductEntity> getMostPurchasedProducts();
    boolean updateProductByQuantity(Integer qty,Integer pid);
    boolean updateProductByPrice(Double price,Integer pid);
    Integer getTotalProductCount();
}
