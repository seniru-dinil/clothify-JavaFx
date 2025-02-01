package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.SupperService;

import java.sql.SQLException;
import java.util.List;

public interface ProductService extends SupperService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean deleteProduct(Integer id);
    Product getProduct(String name);
    boolean updateProduct(Product product);
}
