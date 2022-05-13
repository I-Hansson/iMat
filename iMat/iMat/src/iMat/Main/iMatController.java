package Main;

import Cart.CartItem;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
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
    Text cartPriceIndicator;
    @FXML
    Button toCartButton;
    @FXML
    AnchorPane toCartPane;
    // Under Header
    @FXML Button handlaMenuButton;
    // Kategorier
    @FXML Button meatFishButton;

    // flexScreen
    @FXML
    Label titleLabel;
    @FXML
    ScrollPane mainScrollPane;
    @FXML
    FlowPane browsePane;

    // CART
    @FXML AnchorPane cartPane;
    @FXML FlowPane browseCartPane;


    private ListItemPool itemPool;
     ArrayList<ProductCard> items = new ArrayList<ProductCard>();
    Hashtable <String,CartItem> cartItems = new Hashtable<>();
    ArrayList<CartItem> inCart = new ArrayList<CartItem>();
    boolean open = false;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        for(Product item: handler.getProducts()) {
            ProductCard productCard = new ProductCard(new ShoppingItem(item));
            items.add(productCard);
        }
        for (ProductCard i : items){
            CartItem cartItem = new CartItem(i);
            cartItems.put(cartItem.product.getName(),cartItem);
        }


        System.out.println("hej");
        browsePane.setVgap(10);
        browsePane.setHgap(15);

        logoHeader.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpStartPage());
        handlaMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpHandla());

        for(ProductCard l: items ){
            l.addobservers(this);
        }

        meatFishButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFishMeat() );


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


    public void notifyBuyChange(ProductCard e){
        for (ProductCard i : items){
           if(i.shoppingItem.getAmount() > 0){

               inCart.add(cartItems.get(i.getProduct().getName()));
           }
        }
        showIncart();
    }

    public void setUpFishMeat(){
    titleLabel.setText("Kött och Fisk");
    browsePane.getChildren().clear();
    for (ProductCard item: items){
        if(item.getProduct().getCategory()== category.FISH)
            browsePane.getChildren().add(item);
        if(item.getProduct().getCategory()== category.MEAT)
            browsePane.getChildren().add(item);
        }
    }


    public void logoClick(){

    }

    public void showIncart(){
        browseCartPane.getChildren().clear();
        for (CartItem cItem: inCart){
            System.out.println(cItem.pCard.getProduct().getName());
            System.out.println("bajs");
            if(!inCart.contains(cItem)){
                browseCartPane.getChildren().add(cItem);
            }
        }
    }
    @FXML
    public void openCloseCart(){
        if (!open){
            cartPane.toFront();
            open = true;
        }else{
            cartPane.toBack();
            open = false;
        }



    }


}