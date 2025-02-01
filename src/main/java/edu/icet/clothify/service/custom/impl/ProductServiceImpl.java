package edu.icet.clothify.service.custom.impl;


import edu.icet.clothify.dto.Product;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.ProductDao;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getAllProducts() {
        ProductDao dao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        List<ProductEntity> all = dao.getAll();
        List<Product> products = new ArrayList<>();
        all.forEach(product->products.add((new ModelMapper().map(product,Product.class))));
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return productDao.save(new ModelMapper().map(product,ProductEntity.class));
    }

    @Override
    public boolean deleteProduct(Integer id) {
        ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return productDao.delete(id);
    }

    @Override
    public Product getProduct(String name) {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
        return productDao.update(new ModelMapper().map(product,ProductEntity.class));
    }

}
