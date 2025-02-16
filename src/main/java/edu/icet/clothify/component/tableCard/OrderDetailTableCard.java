package edu.icet.clothify.component.tableCard;


import edu.icet.clothify.dto.OrderTable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
public class OrderDetailTableCard {
    private static  OrderDetailTableCard instance;
    public static OrderDetailTableCard getInstance(){
        if (instance==null)instance= new OrderDetailTableCard();
        return instance;
    }
    private OrderDetailTableCard (){}

    public  AnchorPane createOrderPane(OrderTable orderTable) {
        String id = String.valueOf(orderTable.getOrderId());
        String total = String.valueOf(orderTable.getOrderTotal());
        String date = String.valueOf(orderTable.getOrderDate());
        String name = String.valueOf(orderTable.getCustomerName());
        String mobile = String.valueOf(orderTable.getMobileNumber());

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(909, 40);
        anchorPane.setStyle("-fx-background-color: #1F2937;");


        Label lblID = createLabel(id, 20, 12, 97, 18);
        Label lblName = createLabel(name, 123, 11, 124, 18);
        Label lblMobile = createLabel(mobile, 330, 11, 124, 18);
        Label lblTotal = createLabel(total, 545, 12, 124, 18);
        Label lblDate = createLabel(date, 721, 12, 124, 18);


        anchorPane.getChildren().addAll(lblID, lblName, lblMobile, lblTotal, lblDate);

        return anchorPane;
    }

    private  Label createLabel(String text, double x, double y, double width, double height) {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setPrefSize(width, height);
        label.setTextFill(Color.web("#d1d5db")); // Light gray text
        return label;
    }
}
