package Orders;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import java.io.IOException;

public class OrderListItem extends AnchorPane {
    private Orders parentController;
    private Order order;

    @FXML private Label orderNumberLabel;
    @FXML private Label orderDateLabel;
    @FXML private Button viewOrderButton;

    public OrderListItem(Order order, Orders ordersController) {
        this.order = order;
        this.parentController = ordersController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        orderNumberLabel.setText(String.valueOf(order.getOrderNumber()));
        orderDateLabel.setText(String.valueOf(order.getDate()));
    }

    @FXML
    protected void onClick(Event event){
        parentController.openOrderView(order);
    }
}
