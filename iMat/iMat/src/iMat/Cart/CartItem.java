package Cart;

import ProductCard.ProductCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import org.w3c.dom.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;


import java.io.IOException;

public class CartItem extends AnchorPane {

    @FXML
    AnchorPane cartItemPanee;
    @FXML
    Label productNameText;
    @FXML
    Label amountText;
    @FXML
    Label costText;

    public ProductCard pCard;
    public Product product;
    public CartItem(ProductCard pCard){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        product = pCard.getProduct();
        this.pCard = pCard;
        productNameText.setText(product.getName());
        amountText.setText(String.valueOf(pCard.shoppingItem.getAmount()));
        costText.setText(pCard.shoppingItem.getAmount()*product.getPrice() + " Kr");


    }
    public void updateAmount(){
        amountText.setText(String.valueOf(pCard.shoppingItem.getAmount()));
        costText.setText(pCard.shoppingItem.getAmount()*product.getPrice() + " Kr");
    }
    public double getTotalPrice(){
        return pCard.shoppingItem.getAmount()*product.getPrice();

    }

}
