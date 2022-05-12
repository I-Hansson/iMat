package Orders;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Orders implements Initializable {

    @FXML private GridPane orderGridPane;
    @FXML private AnchorPane orderViewAnchorPane;
    @FXML private ImageView closeImage;

    private Map<Date, OrderListItem> orderListItemMap = new HashMap<Date, OrderListItem>();
    private IMatDataHandler handler = IMatDataHandler.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Order order : handler.getOrders()) {
            OrderListItem orderListItem = new OrderListItem(order, this);
            orderListItemMap.put(order.getDate(), orderListItem);
        }
        populateOrderGrid();
    }

    private void populateOrderGrid() {
        orderGridPane.getChildren().clear();
        for(Order order : handler.getOrders()){
            orderGridPane.getChildren().add(orderListItemMap.get(order.getDate()));
        }
    }

    public void openOrderView(Order order){
        // populate orderView
        orderViewAnchorPane.toFront();
    }

    public void closeOrderView(){
        orderViewAnchorPane.toBack();
    }

    @FXML
    public void closeButtonMouseEntered(){
        closeImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "src/iMat/resources/icon_close_hover.png")));
    }

    @FXML
    public void closeButtonMousePressed(){
        closeImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "src/iMat/resources/icon_close_pressed.png")));
    }

    @FXML
    public void closeButtonMouseExited(){
        closeImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "src/iMat/resources/icon_close.png")));
    }
}
