package edu.icet.clothify.controller.admin;


import edu.icet.clothify.component.ProductCardFactory;
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

public class AdminManageProductFormController implements Initializable {


    public VBox productContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void populateProductContainer(List<Product> products){
        for (Product product : products) {
            productContainer.getChildren().add(ProductCardFactory.getInstance().createProductCard(product));
        }
    }


    public void btnAddProductOnAction(ActionEvent actionEvent) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/add.forms/addProductForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
