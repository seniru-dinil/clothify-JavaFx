package edu.icet.clothify.util;

import edu.icet.clothify.component.tableCard.ProductTableCard;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.ProductService;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.List;

public class ProductUtil {
    private static ProductUtil instance;

    @FXML
    private VBox productContainer;

    private ProductUtil() {
    }

    public static ProductUtil getInstance() {
        return instance == null ? instance = new ProductUtil() : instance;
    }


    public void initializeProductContainer(VBox productContainer) {
        this.productContainer = productContainer;
    }

    public void getAllData() {
        ProductService service = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        List<Product> allProducts = service.getAllProducts();
        populateProductContainer(allProducts);
    }

    public void populateProductContainer(List<Product> products) {
        productContainer.getChildren().clear();
        for (Product product : products) {
            productContainer.getChildren().add(ProductTableCard.getInstance().createProductPane(product));
        }
    }

    public void getCategoryData(String categoryType) {
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        if (categoryType.equals("kids")) {
            populateProductContainer(productService.getProductsByCategory(1));
        } else if (categoryType.equals("gents")){
            populateProductContainer(productService.getProductsByCategory(2));
        } else if(categoryType.equals("ladies")){
            populateProductContainer(productService.getProductsByCategory(3));
        }else{
            populateProductContainer(productService.getAllProducts());
        }
    }

    public void getProductsByStatus(String status){
        ProductService productService = ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
         populateProductContainer(productService.getProductsByStatus(status));
    }

}
