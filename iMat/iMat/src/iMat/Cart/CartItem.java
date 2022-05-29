package Cart;

import ProductCard.ProductCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartItem extends AnchorPane {

    @FXML
    AnchorPane cartItemPanee;
    @FXML
    Label productNameText;
    @FXML
    Label amountText;
    @FXML
    Label costText;
    @FXML AnchorPane deleteInCart;
    public ProductCard pCard;
    public Product product;
    public static final List<ICartItem> listeners = new ArrayList<>();
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
        amountText.setText(String.valueOf((int)pCard.shoppingItem.getAmount()));

        costText.setText((Math.round(pCard.shoppingItem.getAmount()*product.getPrice() * 100.0)/100.0) + " Kr");
    }
    public double getTotalPrice(){
        return pCard.shoppingItem.getAmount()*product.getPrice();

    }
    @FXML
    public void deleteOnClick(){
        for(ICartItem e: listeners){
            e.updateCartItem(this);
            break;
        }
    }
    public void addObserver(ICartItem e){
        listeners.add(e);
    }
    @FXML
    public void detail(){
        for(ICartItem e: listeners){
            e.populateDetailView(this.pCard);
            break;
        }
    }
    @FXML
    public void cartADD(){
        for(ICartItem e: listeners){
            e.addCart(this.pCard);
            break;
        }

    }
    @FXML
    public void cartDEC(){
        for(ICartItem e: listeners){
            e.decCart(this.pCard);
            break;
        }

    }

}
