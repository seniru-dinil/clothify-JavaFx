package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.SupplierTableCard;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.service.custom.impl.SupplierServiceImpl;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierCRUDFormController implements Initializable {
    public VBox supplierContainer;

    private void loadSuppliers(List<Supplier> suppliers) {


        supplierContainer.getChildren().clear();

        for (Supplier supplier : suppliers) {
            AnchorPane card = SupplierTableCard.getInstance().createSupplierCard(
                    supplier
            );
            supplierContainer.getChildren().add(card);
        }
    }

    private void getSuppliers() throws SQLException {
        SupplierService service = (SupplierServiceImpl) ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        List<Supplier> allSuppliers = service.getAllSuppliers();
        loadSuppliers(allSuppliers);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSuppliers();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addSupplierForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
