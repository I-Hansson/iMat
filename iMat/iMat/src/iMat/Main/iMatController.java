package Main;


import CheckoutWizard.ICheckout;
import ProductCard.ICard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ResourceBundle;


public class iMatController implements Initializable, ICard, ICheckout {

    IMatDataHandler handler = IMatDataHandler.getInstance();

    //HEADER
    @FXML TextField searchBar;
    @FXML Button searchButton;
    @FXML TextField cartPriceIndicator;
    @FXML Button toCartButton;
    // Under Header

    // Kategorier


    // flexScreen
    @FXML Label titleLabel;
    @FXML ScrollPane mainScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
            for(Product item: handler.getProducts()){
                    ProductCard productCard = new ProductCard(new ShoppingItem(item));
                    productCard.Hello();
            }
        System.out.println("hej");
    }
}