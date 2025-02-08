package edu.icet.clothify.util.dtoUtil;

import edu.icet.clothify.component.tableCard.SupplierTableCard;
import edu.icet.clothify.dto.Supplier;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.SupplierService;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class SupplierUtil {
    private static SupplierUtil instance;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;


    private Integer supplierId;
    private Integer categoryId;

    @FXML
    public VBox supplierContainer;

    private SupplierUtil() {
    }

    public static SupplierUtil getInstance() {
        return instance == null ? instance = new SupplierUtil() : instance;
    }

    public void initializeContainer(VBox supplierContainer) {
        this.supplierContainer = supplierContainer;
    }

    public void initializeTextField(TextField... args) {
        txtName = args[0];
        txtEmail = args[1];
        txtMobile = args[2];
        txtAddress = args[3];
    }

    public void setValue(Supplier supplier) {
        txtAddress.setText(supplier.getSupplierAddress());
        txtMobile.setText(supplier.getSupplierMobile());
        txtName.setText(supplier.getSupplierName());
        txtEmail.setText(supplier.getSupplierEmail());
        this.supplierId = supplier.getSupplierId();
        this.categoryId = supplier.getCategoryId();
    }

    public boolean updateSupplier() {
        SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        return supplierService.updateSupplier(new Supplier(
                supplierId,
                txtName.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtAddress.getText(),
                categoryId
        ));
    }

    public void loadContainer() throws SQLException {
        getSuppliers();
    }


    public void loadSuppliers(List<Supplier> suppliers) {
        supplierContainer.getChildren().clear();
        for (Supplier supplier : suppliers) {
            AnchorPane card = SupplierTableCard.getInstance().createSupplierCard(
                    supplier
            );
            supplierContainer.getChildren().add(card);
        }
    }

    private void getSuppliers() throws SQLException {
        SupplierService service =ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
        List<Supplier> allSuppliers = service.getAllSuppliers();
        loadSuppliers(allSuppliers);
    }


}
