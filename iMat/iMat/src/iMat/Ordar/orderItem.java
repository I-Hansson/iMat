package Ordar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class orderItem extends AnchorPane {
    @FXML
    TitledPane titlePane;
    @FXML
    FlowPane flowPaneInACC;

    public ShoppingItem item;
     double amount = 0;
    public static final List<IOrder> listeners = new ArrayList<>();
    public orderItem(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }





    }

    public void addObserver(IOrder I){
        listeners.add(I);
    }
    @FXML
    public void notifyShowOrder(){
       /* System.out.println("produkter");
        for(IOrder i: listeners){
            i.showOrder(this.orderNR.getText());
            break;
        }*/
    }


}
