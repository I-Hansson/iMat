package Main;

import Cart.CartItem;
import Feature.Feature;
import Feature.IFeature;
import ProductCard.ICard;
import ProductCard.ProductCard;
import cartItemWizard.cartItemWizard;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

//import Start.startPage;


public class iMatController implements Initializable, ICard, IFeature {

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
            @FXML AnchorPane toErbPane;
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
        @FXML FlowPane UnderCategoiesFlowPane;
        @FXML AnchorPane underCatBrowse;
        @FXML Label titleLabel;
        @FXML AnchorPane scrollPaneAnchorPane;
        @FXML ScrollPane mainScrollPane;

            // underCat

            @FXML AnchorPane underCatKott;


    @FXML FlowPane browsePane;

    // CART
    @FXML AnchorPane cartPane;
    @FXML FlowPane browseCartPane;

    //DetaljVy
    @FXML AnchorPane detailViewPane;
    @FXML ImageView detailImageView;
    @FXML Label detaljProduktNamn;
    @FXML Label detaljKategori;
    @FXML Label detaljInfo;
    @FXML TextArea detaljInfoArea;
    @FXML Label detaljPris;
    @FXML Label detaljUnit;
      @FXML ImageView closeDetail;
        // ADDSUBB
            @FXML ImageView decButton;
            @FXML ImageView addButton;
            @FXML Text productAmount;
            /// Köp knapp
            @FXML AnchorPane notBuy;

            // lagt till i varukorgen
        @FXML AnchorPane grattisPane;


        // wizard
        @FXML AnchorPane wizardPane;
    public double priceInCart = 0;
    @FXML FlowPane productInWizardPane;

     ArrayList<ProductCard> items = new ArrayList<ProductCard>();
    Hashtable <String,CartItem> cartItems = new Hashtable<>();
    ArrayList<CartItem> inCart = new ArrayList<CartItem>();
    boolean open = false;
   ArrayList<cartItemWizard> ItemWizard = new ArrayList<cartItemWizard>();

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
        for (ProductCard i : items){
            cartItemWizard itemWiz = new cartItemWizard(i);
           ItemWizard.add(itemWiz);
        }
        Feature feature = new Feature();
        //feature.addObserver(this);

        System.out.println("hej");
        browsePane.setVgap(15);
        browsePane.setHgap(10);

        logoHeader.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpStartPage());
        //header
        handlaMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event-> setUpErbjudanden(feature));

        //categories


        erbjudandenButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpErbjudanden(feature) );
        myFavoriteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpMyFavorites() );
        meatFishButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFishMeat() );
        fruktGrontButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFruktGront() );
        mejeriButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpMejeri() );
        skafferiButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpSkafferi() );
        kryddorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpKryddor() );
        brodButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpBrod() );

        //detail


        for(ProductCard l: items ){
            l.addobservers(this);
        }




        setUpStartPage();
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
            if (h.pCard.shoppingItem.getAmount() > 0) {
                inCart.add(h);
            }
        }
        showIncart();
    }



    public void logoClick(){
        wizardPane.toBack();
        setUpStartPage();
    }

    public void showIncart(){
        browseCartPane.getChildren().clear();
        priceInCart = 0;
        ArrayList<Double> inCartPrices = new ArrayList<>();
        for (CartItem cItem: inCart) {
            System.out.println(cItem.pCard.getProduct().getName() + cItem.pCard.shoppingItem.getAmount());
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

    public void setUpStartPage() {
        resetCatPressed();
        browsePane.getChildren().clear();
        browsePane.getChildren().add(new Feature());
        //browsePane.getChildren().add(new startPage());
    }


    public void setUpMyFavorites(){
        resetCatPressed();
        titleLabel.setText("Mina Favoriter");
        pressedMyFav.toFront();
        myFav = true;
        browsePane.getChildren().clear();
        for (ProductCard item: items){
            if(handler.isFavorite(item.getProduct()))
                browsePane.getChildren().add(item);

        }
    }
    public void setUpErbjudanden(Feature feature){
        resetCatPressed();
        titleLabel.setText("Erbjudanden");

        //toErbPane.toBack();
        pressedErbjudanden.toFront();
        erbjudanden = true;
        browsePane.getChildren().clear();

        browsePane.getChildren().add(feature);
        for(ProductCard item: items) {
            if (item.getProduct().getCategory() == category.SWEET) {
                browsePane.getChildren().add(item);
            }
        }
    }

    public void setUpFishMeat(){
        resetCatPressed();
        //UnderCategoiesFlowPane.toFront();
        titleLabel.setText("Kött och Fisk");
        underCatKott.toFront();

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
    public void setUpKryddor() {
        resetCatPressed();
        titleLabel.setText("Kryddor");
       pressedKryddor.toFront();
        kryddor = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.HERB) {
                browsePane.getChildren().add(item);
            }
            }
    }
    public void setUpBrod() {
        resetCatPressed();
        titleLabel.setText("Bröd");
        pressedBrod.toFront();
        brod = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.BREAD) {
                browsePane.getChildren().add(item);
            }
        }
    }
    @FXML
    public void search(){
        resetCatPressed();
        browsePane.getChildren().clear();
        titleLabel.setText(searchBar.getText());
        for(Product p : handler.findProducts(searchBar.getText()))
            for(ProductCard item : items){
                if (p == item.getProduct()){
                    browsePane.getChildren().add(item);
                }
            }

    }
    public void populateDetailView(ProductCard p){
        fixDetailView(p);
       detailViewPane.toFront();
    }
    ProductCard E;
    public void fixDetailView(ProductCard p){
        System.out.println(p.getProduct().getName());

        E =p;
        detailImageView.setImage(handler.getFXImage(p.getProduct()));
        detaljProduktNamn.setText(p.getProduct().getName());
        detaljKategori.setText(p.getProduct().getCategory().name());
        detaljInfo.setText("Produktinformation");
        detaljPris.setText(p.getProduct().getPrice()+ " Kr");
        detaljUnit.setText(p.getProduct().getUnit());
        decButton.setImage(p.minusImageRes);
        addButton.setImage((p.addImage));
        if(p.shoppingItem.getAmount() > 0){
        }else{
            p.shoppingItem.setAmount(1);
        }
        productAmount.setText(String.valueOf(p.shoppingItem.getAmount()));
        closeDetail.setImage(new javafx.scene.image.Image("resources/icon_close.png"));
        notifyBuyChange(E);

    }
    @FXML
    public void onClickdetaildec(){
        E.shoppingItem.setAmount(E.shoppingItem.getAmount()- 1);
        productAmount.setText(String.valueOf(E.shoppingItem.getAmount()));
        if(E.shoppingItem.getAmount() == 0){
            detailViewPane.toBack();
            updateAfterDetail();

        }
        notifyBuyChange(E);
    }

    @FXML
    public void onClickDetailAdd(){
        E.shoppingItem.setAmount(E.shoppingItem.getAmount()+ 1);;
        productAmount.setText(String.valueOf(E.shoppingItem.getAmount()));
        notifyBuyChange(E);

    }
    @FXML
    public void notBuyDetailClicked() throws InterruptedException {
       notifyBuyChange(E);
        updateAfterDetail();
        grattis();
       detailViewPane.toBack();


    }
    public void updateFromDetail() {
        if (E.shoppingItem.getAmount() > 0) {
            notifyBuyChange(E);
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

    public void updateAfterDetail(){

            for(ProductCard p: items){
                for(CartItem cart: inCart){
                    if(p.getProduct().getName() == cart.product.getName()){
                        p.updateForDetail();

                }
            }
        }

    }
    public void grattis() throws InterruptedException {
        grattisPane.toFront();
        grattisPane.toFront();
        System.out.println("now");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("klar");
        grattisPane.toBack();
    }
    @FXML
    public void toKassaButtonCliked(){
        productInWizardPane.getChildren().clear();
        wizardPane.toFront();
        for(cartItemWizard i: ItemWizard){
            for(CartItem j: inCart){
                if (i.pCard.getProduct() == j.pCard.getProduct()){
                    i.updateItemWizard();
                    productInWizardPane.getChildren().add(i);
                }
            }
        }
    }


}