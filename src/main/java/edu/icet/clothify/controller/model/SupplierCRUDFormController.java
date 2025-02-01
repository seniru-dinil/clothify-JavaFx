package edu.icet.clothify.controller.model;


import edu.icet.clothify.util.SupplierUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierCRUDFormController implements Initializable {
    public VBox supplierContainer;



    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SupplierUtil instance = SupplierUtil.getInstance();
        instance.initializeContainer(supplierContainer);
        instance.loadContainer();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addSupplierForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
