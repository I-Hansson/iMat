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

import javax.swing.*;
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
    // Kategorier'
        //Mina Favoriter
            @FXML Button myFavoriteButton;
            @FXML AnchorPane pressedMyFav;
            boolean myFav = false;
        //erbjudanden
            @FXML Button erbjudandenButton;
            @FXML AnchorPane pressedErbjudanden;
            boolean erbjudanden = false;
        //kött
             @FXML Button meatFishButton;
            @FXML AnchorPane pressedkott;
            boolean kott = false;
        //fruktogrönt
            @FXML Button fruktGrontButton;
            @FXML AnchorPane pressedFrukt;
            Boolean frukt = false;
        //Mejeri
            @FXML Button mejeriButton;
            @FXML AnchorPane pressedMejeri;
            Boolean mejeri = false;
        //Skafferi
            @FXML Button skafferiButton;
            @FXML AnchorPane pressedSkafferi;
            Boolean skafferi = false;
        //Kryddor
            @FXML Button kryddorButton;
            @FXML AnchorPane pressedKryddor;
            Boolean kryddor = false;
        //Bröd
            @FXML Button brodButton;
            @FXML AnchorPane pressedBrod;
            Boolean brod = false;


    // flexScreen

        @FXML Label titleLabel;
        @FXML ScrollPane mainScrollPane;


    @FXML FlowPane browsePane;

    // CART
    @FXML AnchorPane cartPane;
    @FXML FlowPane browseCartPane;

    public double priceInCart = 0;
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
        //header
        handlaMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpHandla());

        //categories
        meatFishButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFishMeat() );
        fruktGrontButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFruktGront() );
        mejeriButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpMejeri() );
        skafferiButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpSkafferi() );
        for(ProductCard l: items ){
            l.addobservers(this);
        }




        setUpStartPage();
    }

    public void setUpStartPage() {
        resetCatPressed();
        browsePane.getChildren().clear();
        browsePane.getChildren().add(new Feature());
        browsePane.getChildren().add(new startPage());
    }




    public void setUpHandla(){
        resetCatPressed();
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
        HashSet<CartItem> hashSet = new HashSet<>(inCart);
        inCart.clear();
        for( CartItem h : hashSet){
            inCart.add(h);
        }
        showIncart();
    }



    public void logoClick(){

    }

    public void showIncart(){
        browseCartPane.getChildren().clear();
        priceInCart = 0;
        ArrayList<Double> inCartPrices = new ArrayList<>();
        for (CartItem cItem: inCart) {
            System.out.println(cItem.pCard.getProduct().getName() + " hej" + cItem.pCard.shoppingItem.getAmount());
            cItem.updateAmount();
            browseCartPane.getChildren().add(cItem);
            if (cItem.pCard.shoppingItem.getAmount() < 1) {
                removeItemInCart(cItem);
            }
            inCartPrices.add(cItem.getTotalPrice());

        }
            for(Double x : inCartPrices){
                priceInCart += x;
            }
            updatePriceInd();



        System.out.println("------");
    }
    public void removeItemInCart(CartItem cartItem){


            browseCartPane.getChildren().remove(cartItem);
        }
public void updatePriceInd(){
        cartPriceIndicator.setText(String.valueOf(priceInCart + " Kr"));
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
    public void setUpFishMeat(){
        resetCatPressed();
        titleLabel.setText("Kött och Fisk");
        pressedkott.toFront();
        kott = true;
        browsePane.getChildren().clear();
        for (ProductCard item: items){
            if(item.getProduct().getCategory()== category.FISH)
                browsePane.getChildren().add(item);
            if(item.getProduct().getCategory()== category.MEAT)
                browsePane.getChildren().add(item);
        }
    }
    public void setUpFruktGront(){
        resetCatPressed();
        titleLabel.setText("Frukt och Grönt");
        pressedFrukt.toFront();
        frukt =true;
        browsePane.getChildren().clear();
        for (ProductCard item: items){
            if(item.getProduct().getCategory()== category.VEGETABLE_FRUIT)
                browsePane.getChildren().add(item);
            if(item.getProduct().getCategory()== category.BERRY)
                browsePane.getChildren().add(item);
        }
    }
    public void setUpMejeri(){
        resetCatPressed();
        titleLabel.setText("Mejeri");
        pressedMejeri.toFront();
        mejeri =true;
        browsePane.getChildren().clear();
        for (ProductCard item: items){
            if(item.getProduct().getCategory()== category.DAIRIES)
                browsePane.getChildren().add(item);
        }
    }
    public void setUpSkafferi() {
        resetCatPressed();
        titleLabel.setText("Skafferi");
        pressedSkafferi.toFront();
        skafferi = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.FLOUR_SUGAR_SALT) {
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.NUTS_AND_SEEDS){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.PASTA){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.POTATO_RICE){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.SWEET){
                browsePane.getChildren().add(item);
            }


        }
    }






    public void resetCatPressed(){
        if (myFav){
            pressedMyFav.toBack();
            myFav = false;
        }
        if(erbjudanden){
            pressedErbjudanden.toBack();
            erbjudanden = false;
        }
        if(kott){
            pressedkott.toBack();
            kott = false;
        }
        if(frukt){
            pressedFrukt.toBack();
            frukt = false;
        }
        if(mejeri){
            pressedMejeri.toBack();
        }
        if(skafferi){
            pressedSkafferi.toBack();
            skafferi = false;
        }
        if(kryddor){
            pressedKryddor.toBack();
            kryddor = false;
        }
        if(brod){
            pressedBrod.toBack();
            brod = false;
        }


    }



}