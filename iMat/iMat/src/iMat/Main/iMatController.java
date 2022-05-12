package Main;

import CheckoutWizard.ICheckout;
import ProductCard.ICard;
import ProductCard.ListItemPool;
import ProductCard.ProductCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class iMatController implements Initializable, ICard, ICheckout {

    IMatDataHandler handler = IMatDataHandler.getInstance();

    //HEADER
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    TextField cartPriceIndicator;
    @FXML
    Button toCartButton;
    // Under Header

    // Kategorier


    // flexScreen
    @FXML
    Label titleLabel;
    @FXML
    ScrollPane mainScrollPane;
    @FXML
    FlowPane browsePane;

    private ListItemPool itemPool;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        System.out.println("hej");
        browsePane.setVgap(12);
        browsePane.setHgap(12);
              for(Product item: handler.getProducts()){
                   ProductCard productCard = new ProductCard(new ShoppingItem(item));
                   browsePane.getChildren().add(productCard);
           }



    }
    public void setUpHandla(){
        browsePane.getChildren().clear();
        for (Product p : handler.getProducts()) {
            browsePane.getChildren().add(itemPool.getProductCard(p));
        }
    }
}