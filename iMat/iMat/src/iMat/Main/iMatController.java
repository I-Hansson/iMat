package Main;

import CheckoutWizard.ICheckout;
import Feature.Feature;
import ProductCard.ICard;
import ProductCard.ListItemPool;
import ProductCard.ProductCard;
import Start.startPage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.net.URL;
import java.util.*;


public class iMatController implements Initializable, ICard, ICheckout {

    IMatDataHandler handler = IMatDataHandler.getInstance();
    ProductCategory category;
    //HEADER
    @FXML
    ImageView logoHeader;
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    TextField cartPriceIndicator;
    @FXML
    Button toCartButton;
    // Under Header
    @FXML Button handlaMenuButton;
    // Kategorier


    // flexScreen
    @FXML
    Label titleLabel;
    @FXML
    ScrollPane mainScrollPane;
    @FXML
    FlowPane browsePane;

    private ListItemPool itemPool;
    private ArrayList<ProductCard> items = new ArrayList<ProductCard>();
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        for(Product item: handler.getProducts()) {
            ProductCard productCard = new ProductCard(new ShoppingItem(item));
            items.add(productCard);
        }


        System.out.println("hej");
        browsePane.setVgap(10);
        browsePane.setHgap(15);

        handlaMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpHandla());
        logoHeader.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpStartPage());


        setUpStartPage();
    }

    public void setUpStartPage(){
        browsePane.getChildren().clear();
        browsePane.getChildren().add(new Feature());
        browsePane.getChildren().add(new startPage());
    }




    public void setUpHandla(){
        titleLabel.setText("Handla");
        browsePane.getChildren().clear();
        Feature feature = new Feature();
        browsePane.getChildren().add(feature);
        for(ProductCard item: items) {
            if (item.getProduct().getCategory() == category.SWEET) {
                browsePane.getChildren().add(item);
            }
        }
    }
    public void notifyCheckout(){

    }
    @FXML
    public void logoClick(){

    }
}