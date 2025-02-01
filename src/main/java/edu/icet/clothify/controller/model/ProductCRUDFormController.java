package edu.icet.clothify.controller.model;


import edu.icet.clothify.util.ProductUtil;
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
import java.util.ResourceBundle;

public class ProductCRUDFormController implements Initializable {


    public VBox productContainer;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductUtil instance = ProductUtil.getInstance();
        instance.initializeProductContainer(productContainer);
        instance.getData();
    }


    public void btnAddProductOnAction(ActionEvent actionEvent) throws IOException {
        Node load = (Node) FXMLLoader.load(getClass().getResource("/view/add.forms/addProductForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
