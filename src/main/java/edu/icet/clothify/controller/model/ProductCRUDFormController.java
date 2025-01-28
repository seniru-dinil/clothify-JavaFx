package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.ProductTableCard;
import edu.icet.clothify.dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductCRUDFormController implements Initializable {


    public VBox productContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
    }

    public void getData() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "sunlight", 726.90, "this is soap", 18, "img/", 3, 1));
        list.add(new Product(2, "ldsfjk", 726.90, "this is jfljd", 0, "img/", 1, 1));
        list.add(new Product(3, "dinil", 726.90, "this is baaanan", 10, "img/", 2, 1));
        populateProductContainer(list);
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
