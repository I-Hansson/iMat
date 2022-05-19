package Main;

import Cart.CartItem;
import cartItemWizard.ICartItemWizard;
import Feature.Feature;
import Feature.IFeature;
import ProductCard.ICard;
import ProductCard.ProductCard;
import cartItemWizard.cartItemWizard;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import Start.startPage;


public class iMatController implements Initializable, ICard, IFeature, ICartItemWizard {
    Order order = new Order();

    IMatDataHandler handler = IMatDataHandler.getInstance();
    Customer user = handler.getCustomer();
    ProductCategory category;
    @FXML AnchorPane startPane;
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
    boolean handlaHeader = false;
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
      @FXML AnchorPane ecoAnchorPane;
      @FXML ImageView isFavorite;
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
    @FXML Label totalPriceWiz;
    @FXML AnchorPane uppgifterPane;
    @FXML AnchorPane leveransPane;

    //användare
    @FXML TextField fornamn;
    @FXML Label errorFornamn;

    @FXML TextField efternamn;
    @FXML Label errorEfternamn;

    @FXML TextField mobilNummer;
    @FXML Label errorMobilNummer;

    @FXML TextField mail;
    @FXML Label errorMail;

    @FXML TextField gatuAddress;
    @FXML Label errorGatuAddress;

    @FXML TextField postOrt;
    @FXML Label errorPostOrt;

    @FXML TextField postNummer;
    @FXML Label errorPostNummer;

    @FXML CheckBox checkBoxSparaUppgifter;

    //leverans
    ToggleGroup group = new ToggleGroup();
    @FXML RadioButton frefor;
    @FXML RadioButton freEfter;


    // kortUppgifter
    @FXML AnchorPane betalaPane;

    @FXML Label errorMessage1;
    @FXML TextField kort1;
    @FXML TextField kort2;
    @FXML TextField kort3;
    @FXML TextField kort4;

    @FXML TextField datum1;
    @FXML TextField datum2;
    @FXML TextField cvc;

    @FXML Label kortProdukterCost;
    @FXML Label totalKostnadFrakt;






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
        setUppradiobuttons();




        System.out.println("hej");
        browsePane.setVgap(15);
        browsePane.setHgap(10);
        productInWizardPane.setHgap(5);
        productInWizardPane.setVgap(5);

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
               System.out.println(i.shoppingItem.getAmount() + "haha");
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
public void setUppradiobuttons(){

    frefor.getStyleClass().remove("radio-button");
    frefor.getStyleClass().add("toggle-button");
    frefor.setToggleGroup(group);

    freEfter.getStyleClass().remove("radio-button");
    freEfter.getStyleClass().add("toggle-button");
    freEfter.setToggleGroup(group);

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
        handlaHeader = false;
        updateHeader();
        resetCatPressed();
        browsePane.getChildren().clear();
        browsePane.getChildren().add(new Feature());
        //browsePane.getChildren().add(new startPage());
    }
     public void updateHeader(){
        if(handlaHeader){
            handlaMenuButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        }else{
            handlaMenuButton.setStyle("-fx-background-color: rgb(255,255,255)");
        }

     }


    public void setUpMyFavorites(){
        handlaHeader =true;
        updateHeader();

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
        handlaHeader =true;
        updateHeader();
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
        handlaHeader =true;
        updateHeader();
        resetCatPressed();

        //UnderCategoiesFlowPane.toFront();
        titleLabel.setText("Kött och Fisk");
        //underCatKott.toFront();

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
        handlaHeader =true;
        updateHeader();
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
        handlaHeader =true;
        updateHeader();
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
        handlaHeader =true;
        updateHeader();
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
        handlaHeader =true;
        updateHeader();
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
        handlaHeader =true;
        updateHeader();
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
        if(E.getProduct().isEcological()){
           ecoAnchorPane.toFront();
        }else{
            ecoAnchorPane.toBack();
        }
        if(handler.isFavorite(E.getProduct())){
            isFavorite.setImage(new javafx.scene.image.Image("resources/redHeart.png"));
        }else{
            isFavorite.setImage(new javafx.scene.image.Image("resources/favorite_empty.png"));
        }
        if(p.shoppingItem.getAmount() > 0){
        }else{
            p.shoppingItem.setAmount(1);
        }
        productAmount.setText(String.valueOf(p.shoppingItem.getAmount()));
        closeDetail.setImage(new javafx.scene.image.Image("resources/icon_close.png"));
        notifyBuyChange(E);

    }
    @FXML
    public void makeFavorite(){
        E.makeFavorite();
        fixDetailView(E);
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

        System.out.println("now");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("klar");
        grattisPane.toBack();
    }
    @FXML
    public void mouseTrap(Event e){
        e.consume();
    }
    public void downDetail(){
        detailViewPane.toBack();
    }


    @FXML
    public void toKassaButtonCliked(){

        productInWizardPane.getChildren().clear();
        wizardPane.toFront();
        for(cartItemWizard i: ItemWizard){
            for(CartItem j: inCart){
                if (i.pCard.getProduct() == j.pCard.getProduct()){
                    i.addobservers(this);
                    i.updateItemWizard();
                    productInWizardPane.getChildren().add(i);
                }
            }
        }
        createOrder();
        totalPriceWiz.setText("Totalbelopp: " +priceInCart + " Kr");


    }
public void updateWizItem(cartItemWizard e){

    e.pCard.shoppingItem.setAmount(0);
    notifyBuyChange(e.pCard);
    //updateAfterDetail();
    e.pCard.updateForDetail();
    toKassaButtonCliked();
    System.out.println();
    System.out.println("<<<<<<<<<<<");
    for(ShoppingItem i: order.getItems()){
        System.out.println(i.getProduct().getName());
    }
    System.out.println(order.getOrderNumber());
    System.out.println(">>>>>>>>>>>>>");
}
public void createOrder(){
    ArrayList<ShoppingItem> orderList = new ArrayList<>();
    for(CartItem i: inCart){
        orderList.add(i.pCard.shoppingItem);
    }
    order.setItems(orderList);
}
@FXML public void backToNormal(){
        startPane.toFront();
}
@FXML
    public void toUppgifter(){
uppgifterPane.toFront();
    checkIfAlreadyUser();
}
@FXML
    public void backToWizard(){
        wizardPane.toFront();
}
@FXML
public void toLeverans() {
        clearErrors();
       if( !fixErrors() ){
            if(checkBoxSparaUppgifter.isSelected()){
                saveUserInfo();
          }
            leveransPane.toFront();
    }
}


public boolean fixErrors(){
        boolean isError = false;
    if(fornamn.getText().isEmpty()){
        errorFornamn.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(efternamn.getText().isEmpty()){
        errorEfternamn.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(mobilNummer.getText().length() != 10 ){
        errorMobilNummer.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(!mail.getText().contains("@")  ){
        errorMail.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(gatuAddress.getText().isEmpty()){
        errorGatuAddress.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(postOrt.getText().isEmpty() ){
        errorPostOrt.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    if(postNummer.getText().length() != 5 ){
        errorPostNummer.setStyle("-fx-text-fill: RED");
        isError = true;
    }
    return isError;
}
public void clearErrors(){
    errorFornamn.setStyle("-fx-text-fill: white");
    errorEfternamn.setStyle("-fx-text-fill: WHITE");
    errorMobilNummer.setStyle("-fx-text-fill: WHITE");
    errorMail.setStyle("-fx-text-fill: white");
    errorGatuAddress.setStyle("-fx-text-fill: white");
    errorPostOrt.setStyle("-fx-text-fill: white");
    errorPostNummer.setStyle("-fx-text-fill: WHITE");
}
public void saveUserInfo(){
        user.setFirstName(fornamn.getText());
        user.setLastName(efternamn.getText());
        user.setMobilePhoneNumber(mobilNummer.getText());
        user.setEmail(mail.getText());
        user.setAddress(gatuAddress.getText());
        user.setPostAddress(postOrt.getText());
        user.setPostCode(postNummer.getText());
    System.out.println("sparad!");
}
public void checkIfAlreadyUser(){
        if(!user.getFirstName().isEmpty()){
            fornamn.setText(user.getFirstName());
            efternamn.setText(user.getLastName());
            mobilNummer.setText(user.getMobilePhoneNumber());
            mail.setText(user.getEmail());
            gatuAddress.setText(user.getAddress());
            postOrt.setText(user.getPostAddress());
            postNummer.setText(user.getPostCode());
        }
}
@FXML
public void toBetalning(){
            String dag;
            String tid;
            if(group.getSelectedToggle() == frefor){
                dag = "Fredag";
                tid = "Förmiddag";
                uppdateraBelopp();
                betalaPane.toFront();

            }else if(group.getSelectedToggle() == freEfter){
                dag = "Fredag";
                tid = "EfterMiddag";
                uppdateraBelopp();
                betalaPane.toFront();

    }
}

@FXML
    public void slutforKop(){
        updateraErrorkort();

   // if(!fixErrorCard()){

    inCart.clear();
    showIncart();
    for(ProductCard i: items){
        i.shoppingItem.setAmount(0);
        i.updateForDetail();
    }
       /* for(CartItem i :inCart){
            for(ProductCard j : items){
                if(i.product.getName() == j.getProduct().getName()){
                    System.out.println("-----------he-----");
                    System.out.println(i.product.getName());

                   j.shoppingItem.setAmount(0.0);
                    notifyBuyChange(i.pCard);
                    i.pCard.updateForDetail();
                }
            }*/
    startPane.toFront();
        }
        //handler.placeOrder();

    //}


public void uppdateraBelopp(){
        double cost = 0;

        for(ShoppingItem p :order.getItems()){
            cost += p.getAmount() *p.getProduct().getPrice();
        }
        kortProdukterCost.setText(cost + " Kr");
        totalKostnadFrakt.setText(cost + 49 +"Kr");
}
public boolean fixErrorCard(){
    boolean isError = false;
    if(kort1.getText().length() != 4){
        errorMessage1.setText("Fel antal siffror i första fältet");
        errorMessage1.setStyle("-fx-text-fill: RED");
        if(kort1.getText().isEmpty()){
            errorMessage1.setText("Första fältet är inte ifyllt");
            errorMessage1.setStyle("-fx-text-fill: RED");
        }
    }else if(kort2.getText().length() != 4){
        isError = true;
        errorMessage1.setText("Fel antal siffror i andra fältet");
        errorMessage1.setStyle("-fx-text-fill: RED");
        if(kort2.getText().isEmpty()){
            errorMessage1.setText("Andra fältet är inte ifyllt");
            errorMessage1.setStyle("-fx-text-fill: RED");
        }
    }else if(kort3.getText().length() != 4){
        isError = true;
        errorMessage1.setText("Fel antal siffror i tredje fälte");
        errorMessage1.setStyle("-fx-text-fill: RED");
        if(kort3.getText().isEmpty()){
            errorMessage1.setText("Tredje fältet är inte ifyllt");
            errorMessage1.setStyle("-fx-text-fill: RED");
        }
    }else if(kort4.getText().length() != 4){
        isError = true;
        errorMessage1.setText("Fel antal siffror i fjärde fälte");
        errorMessage1.setStyle("-fx-text-fill: RED");
        if(kort4.getText().isEmpty()){
            errorMessage1.setText("Fjärde fältet är inte ifyllt");
            errorMessage1.setStyle("-fx-text-fill: RED");
        }
    }
    if(datum1.getText().isEmpty()){
        isError = true;
    }
    if(datum2.getText().isEmpty()){
        isError = true;
    }
    if(datum2.getText().isEmpty()){
        isError = true;
    }
    return  isError;

}
public void updateraErrorkort(){
        errorMessage1.setText("");
    errorMessage1.setStyle("-fx-text-fill: white");

}

}