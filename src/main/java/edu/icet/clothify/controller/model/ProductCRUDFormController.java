package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.ProductTableCard;
import edu.icet.clothify.dto.Product;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.SupperService;
import edu.icet.clothify.service.custom.ProductService;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductCRUDFormController implements Initializable {


    public VBox productContainer;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
    }

    public void getData() throws SQLException {
        ProductService service = (ProductService) ServiceFactory.getInstance().getService(ServiceType.PRODUCT);
        List<Product> allProducts = service.getAllProducts();
        populateProductContainer(allProducts);
    }

    public void populateProductContainer(List<Product> products) {
        for (Product product : products) {
            productContainer.getChildren().add(ProductTableCard.getInstance().createProductPane(product));
        }
    }


    public void btnAddProductOnAction(ActionEvent actionEvent) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/add.forms/addProductForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
