package edu.icet.clothify.component.tableCard;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.CustomerUtil;
import edu.icet.clothify.util.ServiceType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerTableCard {


    private static CustomerTableCard instance;

    private CustomerTableCard() {
    }

    public static CustomerTableCard getInstance() {
        return instance == null ? instance = new CustomerTableCard() : instance;
    }

    public AnchorPane createCustomerPane(Customer customer) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(874, 42);
        anchorPane.setStyle("-fx-background-color: #1F2937;");

        // ID Label
        Label txtID = createLabel(12.0, 12.0, 78.0, 18.0, String.valueOf(customer.getCustomerID()));

        // Name Label (combine first and last name)
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        Label txtName = createLabel(137.0, 12.0, 90.0, 18.0, fullName);

        // Email Label
        Label txtEmail = createLabel(347.0, 12.0, 170.0, 18.0, customer.getEmail());

        // Mobile Label
        Label txtMobile = createLabel(567.0, 12.0, 98.0, 18.0, customer.getMobileNumber());

        // Action buttons container
        HBox buttonBox = createButtonHBox(customer);

        anchorPane.getChildren().addAll(txtID, txtName, txtEmail, txtMobile, buttonBox);
        return anchorPane;
    }

    private Label createLabel(double x, double y, double width, double height, String text) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.setTextFill(Paint.valueOf("#d1d5db"));
        return label;
    }

    private HBox createButtonHBox(Customer customer) {
        HBox hbox = new HBox();
        hbox.setLayoutX(796);
        hbox.setLayoutY(8);
        hbox.setPrefSize(33, 26);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);

        // Edit Button
        StackPane editButton = createIconButton("/img/edite.png", 16, 16, 24, 2, () -> {
            try {
                handleEdit(customer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Delete Button
        StackPane deleteButton = createIconButton("/img/delete.png", 18, 20, 22, 0, () -> {
            try {
                handleDelete(customer);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        hbox.getChildren().addAll(editButton, deleteButton);
        return hbox;
    }

    private StackPane createIconButton(String imagePath, double imgWidth, double imgHeight, double btnWidth, double btnHeight, Runnable action) {
        StackPane pane = new StackPane();

        JFXButton button = new JFXButton();
        button.setPrefSize(btnWidth, btnHeight);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(e -> action.run());
        ImageView icon = new ImageView();
        icon.setFitWidth(imgWidth);
        icon.setFitHeight(imgHeight);
        icon.setPreserveRatio(true);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            icon.setImage(image);
        } catch (Exception e) {

        }

        pane.getChildren().addAll(icon, button);
        return pane;
    }

    public void handleDelete(Customer customer) throws SQLException {
        CustomerService service = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
        service.deleteCustomer(customer.getCustomerID());
        CustomerUtil.getInstance().reloadContainer();
    }

    public void handleEdit(Customer customer) throws IOException {
        loadUpdateWindow();
        CustomerUtil.getInstance().setInitialValues(customer);
    }

    private void loadUpdateWindow() throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/update/updateCustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}