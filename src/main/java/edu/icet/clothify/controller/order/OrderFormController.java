package edu.icet.clothify.controller.order;

import edu.icet.clothify.component.tableCard.OrderDetailTableCard;
import edu.icet.clothify.dto.OrderTable;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.OrderService;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    @FXML
    private VBox orderContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    public void setData() {
        orderContainer.getChildren().clear();
        OrderService orderService = ServiceFactory.getInstance().getService(ServiceType.ORDER);
        List<OrderTable> orderTableData = orderService.getOrderTableData();
        for (OrderTable i : orderTableData) {
            AnchorPane orderPane = OrderDetailTableCard.getInstance().createOrderPane(i);
            orderContainer.getChildren().add(orderPane);
        }
    }
}
