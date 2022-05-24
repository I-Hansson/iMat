package Ordar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class inDetail extends AnchorPane {
    @FXML
    Label  produkt;
    @FXML Label price;
    @FXML Label unit;
    @FXML Label amount;
    @FXML Label totalPrice;
    @FXML
    ImageView bild;
    IMatDataHandler handler = IMatDataHandler.getInstance();
    public inDetail(ShoppingItem I){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("inDetail.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        produkt.setText(I.getProduct().getName());
        price.setText(String.valueOf(I.getProduct().getPrice()));
        unit.setText(I.getProduct().getUnit());
        amount.setText(I.getAmount() + " St");
        totalPrice.setText(I.getAmount() * I.getProduct().getPrice() + " Kr");
         bild.setImage(handler.getFXImage(I.getProduct()));
    }
}
