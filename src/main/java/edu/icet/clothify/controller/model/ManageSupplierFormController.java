package edu.icet.clothify.controller.model;


import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.enums.ServiceType;
import edu.icet.clothify.util.dtoUtil.SupplierUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageSupplierFormController implements Initializable {
    public VBox supplierContainer;
    public TextField searchBar;


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

    public void searchBarOnActoin(KeyEvent keyEvent) {
        SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        List<Supplier> suppliersByName = supplierService.getSuppliersByName(searchBar.getText());
        SupplierUtil.getInstance().loadSuppliers(suppliersByName);
    }
}
