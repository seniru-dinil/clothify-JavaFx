package edu.icet.clothify.component.tableCard;

import com.jfoenix.controls.JFXButton;
import edu.icet.clothify.dto.Employee;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

public class EmployeeTableCard {

    Employee employee;

    private static EmployeeTableCard instance;
    public static EmployeeTableCard getInstance() {
        return instance==null?instance=new EmployeeTableCard():instance;
    }

    public AnchorPane createAnchorPane(Employee employee) {
        this.employee =employee;
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(874, 42);
        anchorPane.setStyle("-fx-background-color: #1F2937;");

        // ID Label
        Label txtID = createLabel(12.0, 12.0, 78.0, 18.0, String.valueOf(employee.getEmployeeId()));

        // Name Label (combine first and last name)
        String fullName = employee.getEmployeeFirstName() + " " + employee.getEmployeeLastName();
        Label txtName = createLabel(137.0, 12.0, 98.0, 18.0, fullName);

        // Email Label
        Label txtEmail = createLabel(347.0, 12.0, 170.0, 18.0, employee.getEmail());

        // Mobile Label
        Label txtDate = createLabel(567.0, 12.0, 98.0, 18.0, employee.getJoinedDate());

        // Action buttons container
        HBox buttonBox = createButtonHBox();

        anchorPane.getChildren().addAll(txtID, txtName, txtEmail, txtDate, buttonBox);
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

    private HBox createButtonHBox() {
        HBox hbox = new HBox();
        hbox.setLayoutX(796);
        hbox.setLayoutY(8);
        hbox.setPrefSize(33, 26);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);

        // Edit Button
        StackPane editButton = createIconButton(
                "/img/edite.png",
                16, 16,
                24, 2,
                ()->handleEdit()
        );

        // Delete Button
        StackPane deleteButton = createIconButton(
                "/img/delete.png",
                18, 20,
                22, 0,
                ()->handleDelete()
        );

        hbox.getChildren().addAll(editButton, deleteButton);
        return hbox;
    }

    private StackPane createIconButton(String imagePath, double imgWidth, double imgHeight, double btnWidth, double btnHeight,Runnable action) {
        StackPane pane = new StackPane();

        JFXButton button = new JFXButton();
        button.setPrefSize(btnWidth, btnHeight);
        button.setStyle("-fx-background-color: transparent;");
        button.setOnAction(e->action.run());
        ImageView icon = new ImageView();
        icon.setFitWidth(imgWidth);
        icon.setFitHeight(imgHeight);
        icon.setPreserveRatio(true);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            icon.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        pane.getChildren().addAll(icon, button);
        return pane;
    }

    public void handleDelete(){
        System.out.println(employee.getEmployeeFirstName()+" deleted");
    }

    public void handleEdit(){
        System.out.println(employee.getEmployeeFirstName()+" edited");
    }
}