package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.SupplierTableCard;
import edu.icet.clothify.dto.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierCRUDFormController implements Initializable {
    public VBox supplierContainer;

    private void loadSuppliers() {
        List<Supplier> suppliers =new ArrayList<>();
       suppliers.add(new Supplier(1,"sandun","sandun@gmail.com","029792432","kandy",3));
        supplierContainer.getChildren().clear();

        for (Supplier supplier : suppliers) {
            AnchorPane card = SupplierTableCard.getInstance().createSupplierCard(
                    supplier
            );
            supplierContainer.getChildren().add(card);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSuppliers();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addSupplierForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
