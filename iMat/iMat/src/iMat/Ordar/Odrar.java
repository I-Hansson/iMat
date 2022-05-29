package Ordar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Odrar  extends AnchorPane {
    @FXML
    FlowPane orderPane;
    IMatDataHandler handler = IMatDataHandler.getInstance();
    public List<orderItem> orderItems = new ArrayList<>();


    public Odrar() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderMall.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


    }

    public void updateItems() {
        if (!handler.getOrders().isEmpty()) {
            if(handler.getOrders().get(0).getOrderNumber() < handler.getOrders().get(handler.getOrders().size() - 1).getOrderNumber()){
                Collections.reverse(handler.getOrders());
            }


            orderPane.getChildren().clear();

            for (Order o : handler.getOrders()) {


                if( 4999< o.getOrderNumber()){

                    double amount = 49;
                   // System.out.println(o.getOrderNumber());
                    orderItem item = new orderItem();


                    Date date = o.getDate();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = dateFormat.format(date);

                  //  "uuuuu-mm/dd"

                    for (ShoppingItem shop : o.getItems()) {

                        //System.out.println(shop.getProduct().getName());
                        amount += shop.getProduct().getPrice() * shop.getAmount();
                        //System.out.println("----");
                        //System.out.println( shop.getAmount());
                        inDetail produkt = new inDetail(shop);
                        item.flowPaneInACC.getChildren().add(produkt);
                    }

                    item.titlePane.setText("Order NR:  " + String.valueOf(o.getOrderNumber()) + "    Datum:  "  + strDate + "    Kostnad: " + amount + " Kr " );





                    orderPane.getChildren().add(item);



                }

            }

        }
        System.out.println("----");
    }
    public List getList(){

        return orderItems;
    }
}
