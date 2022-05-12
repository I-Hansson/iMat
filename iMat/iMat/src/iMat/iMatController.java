


import CheckoutWizard.ICheckout;
import ProductCard.ICard;
import ProductCard.ProductCard;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    @FXML Label cartPriceIndicator;
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
    }
}