package Main;

import Cart.CartItem;
import Cart.ICartItem;
import Feature.Feature;
import Feature.IFeature;
import MittKonto.Account;
import Ordar.IOrder;
import Ordar.Odrar;
import Ordar.inDetail;
import Ordar.orderItem;
import ProductCard.ICard;
import ProductCard.ProductCard;
import Start.startPage;
import cartItemWizard.ICartItemWizard;
import cartItemWizard.cartItemWizard;
import help.Help;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import Start.startPage;


public class iMatController implements Initializable, ICard, IFeature, ICartItemWizard, ICartItem, IOrder {
    Order order = new Order();
    Odrar orderVy = new Odrar();
    int ordernumber = 500;
    IMatDataHandler handler = IMatDataHandler.getInstance();
    Customer user = handler.getCustomer();
    ProductCategory category;
    @FXML
    AnchorPane startPane;
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
    @FXML
    Button handlaMenuButton;
    @FXML
    Button accountMenuButton;
    @FXML
    Button helpMenuButton;
    @FXML
    Button odrarMenuButton;
    boolean handlaHeader = false;
    boolean accountHeader = false;
    boolean helpHeader = false;
    boolean orderHeader = false;

    // Kategorier'
    //Mina Favoriter
    @FXML
    Button myFavoriteButton;
    @FXML
    AnchorPane pressedMyFav;
    boolean myFav = false;
    //erbjudanden
    @FXML
    Button erbjudandenButton;
    @FXML
    AnchorPane pressedErbjudanden;
    boolean erbjudanden = false;
    @FXML
    AnchorPane toErbPane;
    //kött
    @FXML
    Button meatFishButton;
    @FXML
    AnchorPane pressedkott;
    boolean kott = false;
    //fruktogrönt
    @FXML Button grontButton;
    boolean gront = false;
    @FXML
    Button fruktGrontButton;
    @FXML
    AnchorPane pressedFrukt;
    Boolean frukt = false;
    //Mejeri
    @FXML
    Button mejeriButton;
    @FXML
    AnchorPane pressedMejeri;
    Boolean mejeri = false;
    //Skafferi
    @FXML
    Button skafferiButton;
    @FXML
    AnchorPane pressedSkafferi;
    Boolean skafferi = false;
    //Kryddor
    @FXML
    Button kryddorButton;
    @FXML
    AnchorPane pressedKryddor;
    Boolean dricka = false;
    //Bröd
    @FXML
    Button brodButton;
    @FXML
    AnchorPane pressedBrod;
    Boolean brod = false;


    @FXML
    Button toErbButton;
    // flexScreen
    @FXML
    FlowPane UnderCategoiesFlowPane;
    @FXML
    FlowPane UnderFlowPane;
    @FXML AnchorPane UnderAnchorPane;
    @FXML
    AnchorPane underCatBrowse;
    @FXML
    Label titleLabel;
    @FXML
    AnchorPane scrollPaneAnchorPane;
    @FXML
    ScrollPane mainScrollPane;

    // underCat

    @FXML
    AnchorPane underCatKott;


    @FXML
    FlowPane browsePane;

    // CART
    @FXML
    AnchorPane cartPane;
    @FXML
    FlowPane browseCartPane;

    //DetaljVy
    @FXML
    AnchorPane detailViewPane;
    @FXML
    ImageView detailImageView;
    @FXML
    Label detaljProduktNamn;
    @FXML
    Label detaljKategori;
    @FXML
    Label detaljInfo;
    @FXML
    TextArea detaljInfoArea;
    @FXML
    Label detaljPris;
    @FXML
    Label detaljUnit;
    @FXML
    ImageView closeDetail;
    @FXML
    AnchorPane ecoAnchorPane;
    @FXML
    ImageView isFavorite;
    @FXML
    AnchorPane notBuyDetail;
    // ADDSUBB
    @FXML
    ImageView decButton;
    @FXML
    ImageView addButton;
    @FXML
    Text productAmount;
    /// Köp knapp
    @FXML
    AnchorPane notBuy;

    // lagt till i varukorgen
    @FXML
    AnchorPane grattisPane;


    // wizard
    @FXML
    Label errorNoProdukt;
    @FXML
    AnchorPane wizardPane;
    public double priceInCart = 0;
    @FXML
    FlowPane productInWizardPane;
    @FXML
    Label totalPriceWiz;
    @FXML
    AnchorPane uppgifterPane;
    @FXML
    AnchorPane leveransPane;

    //användare
    @FXML
    TextField fornamn;
    @FXML
    Label errorFornamn;

    @FXML
    TextField efternamn;
    @FXML
    Label errorEfternamn;

    @FXML
    TextField mobilNummer;
    @FXML
    Label errorMobilNummer;

    @FXML
    TextField mail;
    @FXML
    Label errorMail;

    @FXML
    TextField gatuAddress;
    @FXML
    Label errorGatuAddress;

    @FXML
    TextField postOrt;
    @FXML
    Label errorPostOrt;

    @FXML
    TextField postNummer;
    @FXML
    Label errorPostNummer;

    @FXML
    CheckBox checkBoxSparaUppgifter;

    //leverans
    @FXML Label leveransError;
    ToggleGroup leveransGroup = new ToggleGroup();
    @FXML
    RadioButton frefor;
    @FXML
    RadioButton freEfter;
    @FXML
    RadioButton lorFor;
    @FXML
    RadioButton sonFor;
    @FXML
    RadioButton monFor;
    @FXML
    RadioButton tisFor;
    @FXML
    RadioButton lorEfter;
    @FXML
    RadioButton sonEfter;
    @FXML
    RadioButton monEfter;
    @FXML
    RadioButton tisEfter;






    // kortUppgifter
    @FXML
    AnchorPane betalaPane;
    @FXML
    CheckBox saveCreditcard;
    @FXML
    Label errorMessage1;
    @FXML
    TextField kort1;
    @FXML
    TextField kort2;
    @FXML
    TextField kort3;
    @FXML
    TextField kort4;

    @FXML
    TextField datum1;
    @FXML
    TextField datum2;
    @FXML
    TextField cvc;
    @FXML
    Label errorCVC;

    @FXML
    Label errorBetalaDatum1;

    @FXML
    Label kortProdukterCost;
    @FXML
    Label totalKostnadFrakt;


    //tackförbeställning
    @FXML
    AnchorPane tackForBastallning;
    @FXML
    Label orderNO;
    @FXML
    Label orderDattum;
    @FXML
    Label LeveransTid;

    // order detalj
    @FXML
    FlowPane orderDetaljvyPane;
    @FXML
    AnchorPane orderDetailMAIN;

    Odrar pOrder = new Odrar();
    CreditCard card = handler.getCreditCard();
    String dag;
    String tid;
    ArrayList<ProductCard> items = new ArrayList<ProductCard>();
    Hashtable<String, CartItem> cartItems = new Hashtable<>();
    ArrayList<CartItem> inCart = new ArrayList<CartItem>();
    Account account = new Account();
    boolean open = false;
    ArrayList<cartItemWizard> ItemWizard = new ArrayList<cartItemWizard>();
    Feature feature = new Feature();
    HashMap<String, List<ProductCategory>> mainHash = setUpMainCategoryMap();
    ToggleGroup subCatGroup = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        for (Product item : handler.getProducts()) {
            ProductCard productCard = new ProductCard(new ShoppingItem(item));
            items.add(productCard);
        }
        for (ProductCard i : items) {
            CartItem cartItem = new CartItem(i);
            cartItems.put(cartItem.product.getName(), cartItem);
        }
        for (ProductCard i : items) {
            cartItemWizard itemWiz = new cartItemWizard(i);
            ItemWizard.add(itemWiz);
        }

        //handler.reset();


        feature.addObserver(this);

        setUppradiobuttons();

        subCatGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (subCatGroup.getSelectedToggle() != null) {
                    browsePane.getChildren().clear();
                    RadioButton selected = (RadioButton) subCatGroup.getSelectedToggle();
                    //selected.getText();

                    for (ProductCard item : items) {
                        if (item.getProduct().getCategory().toString().toLowerCase().equals( StringToCat(selected.getText()))){
                            browsePane.getChildren().add(item);
                        }
                    }
                }
            }
        });

        browsePane.setVgap(25);
        browsePane.setHgap(25);
        productInWizardPane.setHgap(5);
        productInWizardPane.setVgap(5);

        logoHeader.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpStartPage());
        //header
        handlaMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpErbjudanden(feature));
        accountMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpAccount());
        helpMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpHelp());
        odrarMenuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpOrdrar());
        //categories


        erbjudandenButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpErbjudanden(feature));
        myFavoriteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpMyFavorites());
        meatFishButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFishMeat());
        fruktGrontButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpFruktGront());
        grontButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> setUpGront());
        mejeriButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpMejeri());
        skafferiButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpSkafferi());
        kryddorButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpKryddor());
        brodButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> setUpBrod());

        //detail


        for (ProductCard l : items) {
            l.addobservers(this);
        }


        setUpStartPage();
    }


    public void setUpOrdrar() {
        scrollPaneAnchorPane.setTranslateY(15);
        UnderFlowPane.setVisible(false);
        UnderAnchorPane.setVisible(false);
        resetScroll();
        orderVy.updateItems();
        handlaHeader = false;
        accountHeader = false;
        helpHeader = false;
        orderHeader = true;
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        updateHeader();
        titleLabel.setText("Gamla ordrar");
        browsePane.getChildren().clear();
        browsePane.getChildren().add(orderVy);
        for (orderItem i : orderVy.orderItems) {

            i.addObserver(this);
        }


    }


    public void notifyBuyChange(ProductCard e) {
        for (ProductCard i : items) {
            if (i.shoppingItem.getAmount() > 0) {
                System.out.println(i.shoppingItem.getAmount() + "haha");
                inCart.add(cartItems.get(i.getProduct().getName()));
            }
        }
        HashSet<CartItem> hashSet = new HashSet<>(inCart);
        inCart.clear();
        for (CartItem h : hashSet) {
            if (h.pCard.shoppingItem.getAmount() > 0) {
                inCart.add(h);

            }
        }
        showIncart();
    }

    public void setUppradiobuttons() {
        frefor.getStyleClass().remove("radio-button");
        frefor.getStyleClass().add("toggle-button");
        frefor.getStyleClass().add("leveransButton");
        frefor.setToggleGroup(leveransGroup);

        lorFor.getStyleClass().remove("radio-button");
        lorFor.getStyleClass().add("toggle-button");
        lorFor.getStyleClass().add("leveransButton");
        lorFor.setToggleGroup(leveransGroup);

        sonFor.getStyleClass().remove("radio-button");
        sonFor.getStyleClass().add("toggle-button");
        sonFor.getStyleClass().add("leveransButton");
        sonFor.setToggleGroup(leveransGroup);

        monFor.getStyleClass().remove("radio-button");
        monFor.getStyleClass().add("toggle-button");
        monFor.getStyleClass().add("leveransButton");
        monFor.setToggleGroup(leveransGroup);

        tisFor.getStyleClass().remove("radio-button");
        tisFor.getStyleClass().add("toggle-button");
        tisFor.getStyleClass().add("leveransButton");
        tisFor.setToggleGroup(leveransGroup);

        lorEfter.getStyleClass().remove("radio-button");
        lorEfter.getStyleClass().add("toggle-button");
        lorEfter.getStyleClass().add("leveransButton");
        lorEfter.setToggleGroup(leveransGroup);

        sonEfter.getStyleClass().remove("radio-button");
        sonEfter.getStyleClass().add("toggle-button");
        sonEfter.getStyleClass().add("leveransButton");
        sonEfter.setToggleGroup(leveransGroup);

        monEfter.getStyleClass().remove("radio-button");
        monEfter.getStyleClass().add("toggle-button");
        monEfter.getStyleClass().add("leveransButton");
        monEfter.setToggleGroup(leveransGroup);

        tisEfter.getStyleClass().remove("radio-button");
        tisEfter.getStyleClass().add("toggle-button");
        tisEfter.getStyleClass().add("leveransButton");
        tisEfter.setToggleGroup(leveransGroup);

        freEfter.getStyleClass().remove("radio-button");
        freEfter.getStyleClass().add("toggle-button");
        freEfter.getStyleClass().add("leveransButton");
        freEfter.setToggleGroup(leveransGroup);

    }


    public void logoClick() {
        wizardPane.toBack();

        setUpStartPage();
    }

    public void showIncart() {
        browseCartPane.getChildren().clear();
        priceInCart = 0;
        ArrayList<Double> inCartPrices = new ArrayList<>();
        for (CartItem cItem : inCart) {
            System.out.println(cItem.pCard.getProduct().getName() + cItem.pCard.shoppingItem.getAmount());
            cItem.updateAmount();
            browseCartPane.getChildren().add(cItem);
            cItem.addObserver(this);
            if (cItem.pCard.shoppingItem.getAmount() < 1) {
                removeItemInCart(cItem);
            }
            inCartPrices.add(cItem.getTotalPrice());

        }
        for (Double x : inCartPrices) {
            priceInCart += x;
        }
        updatePriceInd();


        System.out.println("------");
    }

    public void removeItemInCart(CartItem cartItem) {


        browseCartPane.getChildren().remove(cartItem);
    }

    public void updatePriceInd() {
        cartPriceIndicator.setText(String.valueOf((Math.round(priceInCart * 100.0)/100.0) + " Kr"));
    }


    @FXML
    public void openCloseCart() {
        if (!open) {
            cartPane.toFront();
            open = true;
        } else {
            cartPane.toBack();
            open = false;
        }


    }

    public void setUpAccount() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(15);
        UnderAnchorPane.setVisible(false);
        UnderFlowPane.setVisible(false);
        orderHeader = false;
        handlaHeader = false;
        accountHeader = true;
        helpHeader = false;
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        updateHeader();
        titleLabel.setText("Mitt konto");
        browsePane.getChildren().clear();
        account.ifAlreadyUser();
        browsePane.getChildren().add(account);

    }


    public void setUpStartPage() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(15);
        UnderAnchorPane.setVisible(false);
        UnderFlowPane.setVisible(false);
        orderHeader = false;
        accountHeader = false;
        handlaHeader = false;
        helpHeader = false;
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        titleLabel.setText("Start");
        updatePliancykategori();
        updateHeader();
        feature.toErbButton.setVisible(false);
        resetCatPressed();
        browsePane.getChildren().clear();
        browsePane.getChildren().add(new Feature());
        browsePane.getChildren().add(new startPage());
    }

    public void updateHeader() {
        resetScroll();
        if (handlaHeader) {
            handlaMenuButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            handlaMenuButton.setStyle("-fx-background-color: rgb(255,255,255)");
        }
        if (accountHeader) {
            accountMenuButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            accountMenuButton.setStyle("-fx-background-color: rgb(255,255,255)");
        }
        if (helpHeader) {
            helpMenuButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            helpMenuButton.setStyle("-fx-background-color: rgb(255,255,255)");
        }
        if (orderHeader) {
            odrarMenuButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            odrarMenuButton.setStyle("-fx-background-color: rgb(255,255,255)");
        }


    }


    public void setUpMyFavorites() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(15);
        UnderAnchorPane.setVisible(false);
        UnderFlowPane.setVisible(false);
        orderHeader = false;
        accountHeader = false;
        handlaHeader = true;
        helpHeader = false;
        updateHeader();

        myFav = true;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        titleLabel.setText("Mina Favoriter");

        myFav = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (handler.isFavorite(item.getProduct()))
                browsePane.getChildren().add(item);

        }
    }

    public void setUpHelp() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(15);
        UnderAnchorPane.setVisible(false);
        UnderFlowPane.setVisible(false);
        orderHeader = false;
        Help help = new Help();
        accountHeader = false;
        handlaHeader = false;
        helpHeader = true;
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        updateHeader();
        updatePliancykategori();
        titleLabel.setText("Hjälp");
        browsePane.getChildren().clear();
        browsePane.getChildren().add(help);


    }

    public void setUpErbjudanden(Feature feature) {
        resetScroll();
        UnderFlowPane.setVisible(false);
        scrollPaneAnchorPane.setTranslateY(15);
        UnderAnchorPane.setVisible(false);
        orderHeader = false;
        accountHeader = false;
        handlaHeader = true;
        helpHeader = false;
        feature.toErbButton.setVisible(false);
        updateHeader();
        feature.resize();
        myFav = false;
        erbjudanden = true;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        titleLabel.setText("Erbjudanden");

        //toErbPane.toBack();
        //pressedErbjudanden.toFront();
        erbjudanden = true;
        browsePane.getChildren().clear();

        browsePane.getChildren().add(feature);
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.SWEET) {
                browsePane.getChildren().add(item);
            }
        }
    }

    public void updatePliancykategori() {

        if (kott) {
            meatFishButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            meatFishButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (frukt) {
            fruktGrontButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            fruktGrontButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (myFav) {
            myFavoriteButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            myFavoriteButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (erbjudanden) {
            erbjudandenButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            erbjudandenButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (mejeri) {
            mejeriButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            mejeriButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (skafferi) {
            skafferiButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            skafferiButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (dricka) {
            kryddorButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            kryddorButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (brod) {
            brodButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            brodButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }
        if (gront){
            grontButton.setStyle("-fx-background-color: rgb(51, 153, 255)");
        } else {
            grontButton.setStyle("-fx-background-color: rgb(255, 255, 255)");
        }

    }

    public void setUpFishMeat() {
        resetScroll();
        orderHeader = false;
        accountHeader = false;
        handlaHeader = true;
        helpHeader = false;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = true;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();

        //UnderCategoiesFlowPane.toFront();
        titleLabel.setText("Kött och Fisk");
        //underCatKott.toFront();

       UnderFlowPane.setVisible(true);
        scrollPaneAnchorPane.setTranslateY(50);
        UnderAnchorPane.setVisible(true);
        browsePane.getChildren().clear();
        UnderFlowPane.getChildren().clear();
        setUpsubcategoriPANE("Kött & Fisk");
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.FISH)
                browsePane.getChildren().add(item);
            if (item.getProduct().getCategory() == category.MEAT)
                browsePane.getChildren().add(item);
        }
    }

    public void setUpsubcategoriPANE(String mainCat) {
        resetScroll();
        UnderFlowPane.getChildren().clear();
        for (ProductCategory pC : mainHash.get(mainCat)) {
            RadioButton tempButton = new RadioButton(categoryToString(pC));
            tempButton.setToggleGroup(subCatGroup);
            tempButton.getStyleClass().remove("radio-button");
            tempButton.getStyleClass().add("toggle-button");
            tempButton.getStyleClass().add("under");
            //tempButton.getStyleClass().add();
            tempButton.setTranslateY(7);
            tempButton.setTranslateX(8);
            UnderFlowPane.getChildren().add(tempButton);
        }

    }

    public void setUpFruktGront() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);
        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        helpHeader = false;
        handlaHeader = true;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = true;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();

        titleLabel.setText("Frukt");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Frukt");
        frukt = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.BERRY)
                browsePane.getChildren().add(item);
            if (item.getProduct().getCategory() == category.CITRUS_FRUIT)
                browsePane.getChildren().add(item);
            if (item.getProduct().getCategory() == category.FRUIT)
                browsePane.getChildren().add(item);
            if (item.getProduct().getCategory() == category.MELONS)
                browsePane.getChildren().add(item);
            if (item.getProduct().getCategory() == category.EXOTIC_FRUIT)
                browsePane.getChildren().add(item);
        }
    }
    public void setUpGront() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);

        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        helpHeader = false;
        handlaHeader = true;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = true;
        updatePliancykategori();

        titleLabel.setText("Grönsaker");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Grönsaker");

        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.POD){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.ROOT_VEGETABLE){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.VEGETABLE_FRUIT){
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.CABBAGE){
                browsePane.getChildren().add(item);
            }

        }

    }

    public void setUpMejeri() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);
        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        handlaHeader = true;
        helpHeader = false;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = true;
        skafferi = false;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        titleLabel.setText("Mejeri");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Mejeri");

        mejeri = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.DAIRIES)
                browsePane.getChildren().add(item);
        }
    }

    public void setUpSkafferi() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);
        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        helpHeader = false;
        handlaHeader = true;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = true;
        dricka = false;
        brod = false;
        gront = false;
        updatePliancykategori();
        titleLabel.setText("Skafferi");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Skafferi");
        skafferi = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.FLOUR_SUGAR_SALT) {
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.NUTS_AND_SEEDS) {
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.PASTA) {
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.POTATO_RICE) {
                browsePane.getChildren().add(item);
            }
            if (item.getProduct().getCategory() == category.SWEET) {
                browsePane.getChildren().add(item);
            }



        }
    }

    public void setUpKryddor() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);
        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        helpHeader = false;
        handlaHeader = true;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = true;
        brod = false;

        gront = false;
        updatePliancykategori();
        titleLabel.setText("Drycker");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Drycker");


        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.HOT_DRINKS || item.getProduct().getCategory() == category.COLD_DRINKS) {
                browsePane.getChildren().add(item);
            }
        }
    }

    public void setUpBrod() {
        resetScroll();
        scrollPaneAnchorPane.setTranslateY(50);
        UnderFlowPane.setVisible(true);
        UnderAnchorPane.setVisible(true);
        orderHeader = false;
        accountHeader = false;
        handlaHeader = true;
        helpHeader = false;
        updateHeader();
        myFav = false;
        erbjudanden = false;
        kott = false;
        frukt = false;
        mejeri = false;
        skafferi = false;
        dricka = false;
        brod = true;
        gront = false;
        updatePliancykategori();
        ;
        titleLabel.setText("Bröd");
        subCatGroup.selectToggle(null);
        setUpsubcategoriPANE("Bröd");
        brod = true;
        browsePane.getChildren().clear();
        for (ProductCard item : items) {
            if (item.getProduct().getCategory() == category.BREAD) {
                browsePane.getChildren().add(item);
            }
        }
    }

    @FXML
    public void search() {
        scrollPaneAnchorPane.setTranslateY(15);
        UnderFlowPane.setVisible(false);
        UnderAnchorPane.setVisible(false);
        orderHeader = false;
        handlaHeader = true;
        accountHeader = false;
        helpHeader = false;
        resetCatPressed();
        browsePane.getChildren().clear();
        titleLabel.setText(searchBar.getText());
        for (Product p : handler.findProducts(searchBar.getText()))
            for (ProductCard item : items) {
                if (p == item.getProduct()) {
                    browsePane.getChildren().add(item);
                }
            }

    }

    public void populateDetailView(ProductCard p) {

        fixDetailView(p);
        detailViewPane.toFront();
    }

    ProductCard E;

    public void fixDetailView(ProductCard p) {
        System.out.println(p.getProduct().getName());

        E = p;
        detailImageView.setImage(handler.getFXImage(p.getProduct()));
        detaljProduktNamn.setText(p.getProduct().getName());

        detaljKategori.setText(categoryToString(p.getProduct().getCategory()));

        detaljPris.setText(p.getProduct().getPrice()+"");
        detaljUnit.setText(p.getProduct().getUnit());
        decButton.setImage(p.minusImageRes);
        addButton.setImage((p.addImage));
        if (E.getProduct().isEcological()) {
            ecoAnchorPane.toFront();
        } else {
            ecoAnchorPane.toBack();
        }
        if (handler.isFavorite(E.getProduct())) {
            isFavorite.setImage(new javafx.scene.image.Image("resources/redHeart.png"));
        } else {
            isFavorite.setImage(new javafx.scene.image.Image("resources/favorite_empty.png"));
        }
        notBuyDetail.toFront();
        notBuyDetail.setVisible(true);
        if (p.shoppingItem.getAmount() <= 0) {
            p.shoppingItem.setAmount(0);
        }else{
            notBuyDetail.toBack();
            notBuyDetail.setVisible(false);
        }
        productAmount.setText(String.valueOf((int) p.shoppingItem.getAmount()));

        closeDetail.setImage(new javafx.scene.image.Image("resources/icon_close.png"));
    }

    @FXML
    public void makeFavorite() {
        E.makeFavorite();
        fixDetailView(E);
    }
    public void addCart(ProductCard p){
        p.shoppingItem.setAmount(p.shoppingItem.getAmount() + 1);

        productAmount.setText(String.valueOf((int) p.shoppingItem.getAmount()));
        updateAfterDetail();
        notifyBuyChange(p);
    }
    public void decCart(ProductCard p){
        p.shoppingItem.setAmount(p.shoppingItem.getAmount() - 1);
        productAmount.setText(String.valueOf((int) p.shoppingItem.getAmount()));
        if (p.shoppingItem.getAmount() == 0 || p.shoppingItem.getAmount() == -1 ) {
            detailViewPane.toBack();
        }
        updateAfterDetail();
        notifyBuyChange(p);
    }

    @FXML
    public void onClickdetaildec() {
        E.shoppingItem.setAmount(E.shoppingItem.getAmount() - 1);
        productAmount.setText(String.valueOf((int) E.shoppingItem.getAmount()));
        if (E.shoppingItem.getAmount() == 0 || E.shoppingItem.getAmount() == -1 ) {
            //detailViewPane.toBack();
            //updateAfterDetail();
            notBuyDetail.toFront();
            notBuyDetail.setVisible(true);
        }
        notifyBuyChange(E);
    }

    @FXML
    public void onClickDetailAdd() {
        E.shoppingItem.setAmount(E.shoppingItem.getAmount() + 1);
        ;
        productAmount.setText(String.valueOf((int) E.shoppingItem.getAmount()));
        notifyBuyChange(E);

    }

    @FXML
    public void notBuyDetailClicked() throws InterruptedException {

        System.out.println("klick");
        updateAfterDetail();
        notBuyDetail.toBack();
        notBuyDetail.setVisible(false);
        E.shoppingItem.setAmount((int)1);
        //detailViewPane.toBack();
        productAmount.setText(String.valueOf((int) E.shoppingItem.getAmount()));

        notifyBuyChange(E);

    }

    public void updateFromDetail() {
        if (E.shoppingItem.getAmount() > 0) {
            notifyBuyChange(E);
        }
    }

    public void resetCatPressed() {
        if (myFav) {
            pressedMyFav.toBack();
            myFav = false;
        }
        if (erbjudanden) {
            pressedErbjudanden.toBack();
            erbjudanden = false;
        }
        if (kott) {
            pressedkott.toBack();
            kott = false;
        }
        if (frukt) {
            pressedFrukt.toBack();
            frukt = false;
        }
        if (mejeri) {
            pressedMejeri.toBack();
        }
        if (skafferi) {
            pressedSkafferi.toBack();
            skafferi = false;
        }
        if (dricka) {
            pressedKryddor.toBack();
            dricka = false;
        }
        if (brod) {
            pressedBrod.toBack();
            brod = false;
        }


    }

    public void updateAfterDetail() {

        for (ProductCard p : items) {
            for (CartItem cart : inCart) {
                if (p.getProduct().getName() == cart.product.getName()) {
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
    public void mouseTrap(Event e) {
        e.consume();
    }

    public void downDetail() {
        //E.shoppingItem.setAmount(0);
        notifyBuyChange(E);
        updateAfterDetail();
        detailViewPane.toBack();
    }


    @FXML
    public void toKassaButtonCliked() {

        productInWizardPane.getChildren().clear();
        wizardPane.toFront();
        for (cartItemWizard i : ItemWizard) {
            for (CartItem j : inCart) {
                if (i.pCard.getProduct() == j.pCard.getProduct()) {
                    i.addobservers(this);
                    i.updateItemWizard();
                    productInWizardPane.getChildren().add(i);
                }
            }
        }
        createOrder();
        errorNoProdukt.setVisible(false);
        totalPriceWiz.setText("Totalbelopp: " + (Math.round(priceInCart*100.0)/100.0) + " Kr");


    }
    public void resetScroll(){
        mainScrollPane.setVvalue(0);
    }
    @FXML
    public void todorar(){
        startPane.toFront();
        setUpOrdrar();

    }
    @FXML
    public void backToUppgifter(){
        uppgifterPane.toFront();
    }
    @FXML
    public void backToprod(){
        wizardPane.toFront();
    }

    public void updateWizItem(cartItemWizard e) {

        e.pCard.shoppingItem.setAmount((int) 0);
        notifyBuyChange(e.pCard);
        //updateAfterDetail();
        e.pCard.updateForDetail();
        toKassaButtonCliked();

    }
    public void addWizItem(cartItemWizard e){

        e.pCard.shoppingItem.setAmount((int)e.pCard.shoppingItem.getAmount() + 1);
        notifyBuyChange(e.pCard);
        //updateAfterDetail();
        e.pCard.updateForDetail();
        toKassaButtonCliked();
    }
    public void decWizItem(cartItemWizard e){

        e.pCard.shoppingItem.setAmount((int)e.pCard.shoppingItem.getAmount() - 1);
        notifyBuyChange(e.pCard);
        //updateAfterDetail();
        e.pCard.updateForDetail();
        toKassaButtonCliked();
    }

    public void updateCartItem(CartItem e) {
        e.pCard.shoppingItem.setAmount(0);
        notifyBuyChange(e.pCard);
        e.pCard.updateForDetail();

    }

    public void createOrder() {
        ArrayList<ShoppingItem> orderList = new ArrayList<>();
        for (CartItem i : inCart) {
            orderList.add(i.pCard.shoppingItem);
        }
        order.setItems(orderList);
    }

    @FXML
    public void backToNormal() {
        startPane.toFront();
    }

    @FXML
    public void toUppgifter() {
        if(!inCart.isEmpty()){
            uppgifterPane.toFront();
            checkIfAlreadyUser();
        }else{
            errorNoProdukt.setVisible(true);
        }

    }

    @FXML
    public void backToWizard() {
        wizardPane.toFront();
    }

    @FXML
    public void toLeverans() {
        clearErrors();
        if (!fixErrors()) {
            if (checkBoxSparaUppgifter.isSelected()) {
                saveUserInfo();
            }
            leveransPane.toFront();
        }
    }
    @FXML
    public void backToLeverans()
    {
        leveransPane.toFront();
    }

    public boolean fixErrors() {
        boolean isError = false;
        if (fornamn.getText().isEmpty()) {
            errorFornamn.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (efternamn.getText().isEmpty()) {
            errorEfternamn.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (mobilNummer.getText().length() != 10) {
            errorMobilNummer.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (!mail.getText().contains("@")) {
            errorMail.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (gatuAddress.getText().isEmpty()) {
            errorGatuAddress.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (postOrt.getText().isEmpty()) {
            errorPostOrt.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        if (postNummer.getText().length() != 5) {
            errorPostNummer.setStyle("-fx-text-fill: RED");
            isError = true;
        }
        return isError;
    }

    public void clearErrors() {
        errorFornamn.setStyle("-fx-text-fill: white");
        errorEfternamn.setStyle("-fx-text-fill: WHITE");
        errorMobilNummer.setStyle("-fx-text-fill: WHITE");
        errorMail.setStyle("-fx-text-fill: white");
        errorGatuAddress.setStyle("-fx-text-fill: white");
        errorPostOrt.setStyle("-fx-text-fill: white");
        errorPostNummer.setStyle("-fx-text-fill: WHITE");
    }

    public void saveUserInfo() {
        user.setFirstName(fornamn.getText());
        user.setLastName(efternamn.getText());
        user.setMobilePhoneNumber(mobilNummer.getText());
        user.setEmail(mail.getText());
        user.setAddress(gatuAddress.getText());
        user.setPostAddress(postOrt.getText());
        user.setPostCode(postNummer.getText());
        System.out.println("sparad!");
    }

    public void checkIfAlreadyUser() {
        if (!user.getFirstName().isEmpty()) {
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
    public void toBetalning() {

        if (leveransGroup.getSelectedToggle() == frefor) {
            dag = "Fredag 3/6";
            tid = "8:00 - 12:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();

        } else if (leveransGroup.getSelectedToggle() == freEfter) {
            dag = "Fredag 3/6";
            tid = "14:00 - 16:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == lorFor) {
            dag = "Lördag 4/6";
            tid = " 8:00 - 12:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == lorEfter) {
            dag = "Lördag 4/6";
            tid = " 14:00 - 16:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == sonFor) {
            dag = "Söndag 4/6";
            tid = " 8:00 - 12:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == sonEfter) {
            dag = "Söndag 4/6";
            tid = " 14:00 - 16:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == monFor) {
            dag = "Måndag 4/6";
            tid = " 8:00 - 12:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == monEfter) {
            dag = "Måndag 4/6";
            tid = " 14:00 - 16:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == tisFor) {
            dag = "Tisdag 4/6";
            tid = " 8:00 - 12:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else if (leveransGroup.getSelectedToggle() == tisEfter) {
            dag = "Tisdag 4/6";
            tid = " 14:00 - 16:00";
            uppdateraBelopp();
            betalaPane.toFront();
            ifSavedCard();
        }else{
            leveransError.setStyle("-fx-text-fill: red");
        }


    }

    public void saveCreditcardInfo() {
        card.setCardNumber(kort1.getText() + " " + kort2.getText() + " " + kort3.getText() + " " + kort4.getText());
        card.setValidMonth(Integer.parseInt(datum1.getText()));
        card.setValidYear(Integer.parseInt(datum2.getText()));
        card.setVerificationCode(Integer.parseInt(cvc.getText()));
    }

    public void ifSavedCard() {
        String[] split = {""};
        split = card.getCardNumber().split(" ");

        if (split.length >= 1) {
            kort1.setText(split[0]);
        }

        if (split.length >= 2) {
            kort2.setText(split[1]);
        }
        if (split.length >= 3) {
            kort3.setText(split[2]);
        }
        if (split.length >= 4) {
            kort4.setText(split[3]);
        }


        if (card.getValidMonth() == 0) {
            datum1.setText("");
        } else {
            datum1.setText(String.valueOf(card.getValidMonth()));
        }
        if (card.getValidYear() == 0) {
            datum2.setText("");
        } else {
            datum2.setText(String.valueOf(card.getValidYear()));
        }
        if (card.getVerificationCode() == 0) {
            cvc.setText("");
        } else {
            cvc.setText(String.valueOf(card.getVerificationCode()));
        }
    }


    @FXML
    public void slutforKop() {
        updateraErrorkort();

        if (!fixErrorCard()) {

            if (saveCreditcard.isSelected()) {
                saveCreditcardInfo();
            }
            System.out.println("---");
            for (CartItem item : inCart) {
                System.out.println(item.pCard.shoppingItem.getAmount());
                handler.getShoppingCart().addItem(item.pCard.shoppingItem);
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
            LocalDate localDate = LocalDate.now();

            ordernumber = (int) (Math.random() * 2001) + 3000;
            int  hej = 0;
            for(Order i: handler.getOrders()){
                if(i.getOrderNumber() > hej){
                    hej = i.getOrderNumber();
                }
            }
                handler.placeOrder();
            System.out.println(hej+1);
            handler.getOrders().get(handler.getOrders().size() - 1).setOrderNumber(hej +1);

            orderVy.updateItems();
            inCart.clear();
            showIncart();
            for (ProductCard i : items) {
                i.setNewShopingitem(i.shoppingItem);
                i.updateForDetail();
            }

            System.out.println();
            orderNO.setText(hej+1 + "");
            orderDattum.setText(dtf.format(localDate));
            LeveransTid.setText(dag + ", " + tid);
            tackForBastallning.toFront();
        }

    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    //}
    @FXML
    public void toBegining() {
        startPane.toFront();
        setUpStartPage();
    }


    public void uppdateraBelopp() {
        double cost = 0;

        for (ShoppingItem p : order.getItems()) {
            cost += p.getAmount() * p.getProduct().getPrice();
        }
        kortProdukterCost.setText((Math.round(cost*100.0)/100.0) + " Kr");
        totalKostnadFrakt.setText((Math.round((cost + 49)*100.0)/100.0) + "Kr");
    }

    public boolean fixErrorCard() {
        boolean isError = false;

        if (kort1.getText().length() != 4) {
            isError = true;
            errorMessage1.setText("Fel antal siffror i första fältet");
            errorMessage1.setStyle("-fx-text-fill: RED");
            if (kort1.getText().isEmpty()) {
                errorMessage1.setText("Första fältet är inte ifyllt");
                errorMessage1.setStyle("-fx-text-fill: RED");
            }

        } else if (kort2.getText().length() != 4) {
            isError = true;
            errorMessage1.setText("Fel antal siffror i andra fältet");
            errorMessage1.setStyle("-fx-text-fill: RED");
            if (kort2.getText().isEmpty()) {
                errorMessage1.setText("Andra fältet är inte ifyllt");
                errorMessage1.setStyle("-fx-text-fill: RED");
            }
            return isError;
        } else if (kort3.getText().length() != 4) {
            isError = true;
            errorMessage1.setText("Fel antal siffror i tredje fälte");
            errorMessage1.setStyle("-fx-text-fill: RED");
            if (kort3.getText().isEmpty()) {
                errorMessage1.setText("Tredje fältet är inte ifyllt");
                errorMessage1.setStyle("-fx-text-fill: RED");
            }
            return isError;
        } else if (kort4.getText().length() != 4) {
            isError = true;
            errorMessage1.setText("Fel antal siffror i fjärde fälte");
            errorMessage1.setStyle("-fx-text-fill: RED");
            if (kort4.getText().isEmpty()) {
                errorMessage1.setText("Fjärde fältet är inte ifyllt");
                errorMessage1.setStyle("-fx-text-fill: RED");
            }
            return isError;
        }
        if (datum1.getText().length() != 2) {
            isError = true;
            errorBetalaDatum1.setText("Fel antal siffror i månad");
           errorBetalaDatum1.setStyle("-fx-text-fill: RED");
            if (datum1.getText().isEmpty()) {
              errorBetalaDatum1.setText("Månad inte ifylld");
                errorBetalaDatum1.setStyle("-fx-text-fill: RED");
            }

        }
        else if (datum2.getText().length() != 2) {
            isError = true;
            errorBetalaDatum1.setText("Fel antal siffror i år");
            errorBetalaDatum1.setStyle("-fx-text-fill: RED");
            if (datum2.getText().isEmpty()) {
                errorBetalaDatum1.setText("År inte ifylld");
                errorBetalaDatum1.setStyle("-fx-text-fill: RED");
            }
        }
        if (cvc.getText().length() != 3) {
            isError = true;
           errorCVC.setText("Fel antal siffror i CVC");
            errorCVC.setStyle("-fx-text-fill: RED");
            if (cvc.getText().isEmpty()) {
                errorCVC.setText("CVC inte ifylld");
                errorCVC.setStyle("-fx-text-fill: RED");
            }
        }
        return isError;

    }

    public void updateraErrorkort() {
        errorMessage1.setText("");
        errorMessage1.setStyle("-fx-text-fill: white");
        errorBetalaDatum1.setStyle("-fx-text-fill: white");
        errorCVC.setStyle("-fx-text-fill: white");
    }

    public void showOrder(String orderNr) {
        orderDetaljvyPane.getChildren().clear();
        double amount = 0;
        orderVy.updateItems();
        for (Order o : handler.getOrders()) {

            if (o.getOrderNumber() == Integer.parseInt(orderNr)) {
                for (ShoppingItem item : o.getItems()) {
                    inDetail produkt = new inDetail(item);

                    orderDetaljvyPane.getChildren().add(produkt);


                }
                orderDetailMAIN.toFront();
            }
        }
    }

    @FXML
    public void orderBack() {
        orderDetailMAIN.toBack();
    }


    private String categoryToString(ProductCategory category) {
        return switch (category.toString().toLowerCase()) {
            case "pod" -> "Baljväxter";
            case "bread" -> "Bröd";
            case "berry" -> "Bär";
            case "citrus_fruit" -> "Citrusfrukter";
            case "hot_drinks" -> "Varma drycker";
            case "cold_drinks" -> "Kalla drycker";
            case "exotic_fruit" -> "Exotiska frukter";
            case "fish" -> "Fisk";
            case "vegetable_fruit" -> "Grönsaksfrukter";
            case "cabbage" -> "Kål";
            case "meat" -> "Kött";
            case "dairies" -> "Mejeri";
            case "melons" -> "Meloner";
            case "flour_sugar_salt" -> "Mjöl, socker och salt";
            case "nuts_and_seeds" -> "Nötter och frön";
            case "pasta" -> "Pasta";
            case "potato_rice" -> "Potatis och ris";
            case "root_vegetable" -> "Rotfrukter";
            case "fruit" -> "Övriga frukter";
            case "sweet" -> "Sötsaker";
            case "herb" -> "Örter";

            default -> null;
        };
    }

    private HashMap<String, List<ProductCategory>> setUpMainCategoryMap() {
        HashMap<String, List<ProductCategory>> output = new HashMap<>();
        List<ProductCategory> vegetables = new ArrayList<>();
        List<ProductCategory> fruit = new ArrayList<>();
        List<ProductCategory> meatFish = new ArrayList<>();
        List<ProductCategory> drinks = new ArrayList<>();
        List<ProductCategory> pantry = new ArrayList<>();
        List<ProductCategory> dairy = new ArrayList<>();
        List<ProductCategory> brod = new ArrayList<>();


        for (ProductCategory current : ProductCategory.values()) {
            if (isFruit(current)) {
                fruit.add(current);
            } else if (isVegetables(current)) {
                vegetables.add(current);
            } else if (isMeatFish(current)) {
                meatFish.add(current);
            } else if (isDrinks(current)) {
                drinks.add(current);
            } else if (isDairy(current)) {
                dairy.add(current);
            } else if (isSweets(current)) {
                brod.add(current);
            } else {
                pantry.add(current);
            }
        }

        output.put("Grönsaker", vegetables);
        output.put("Frukt", fruit);
        output.put("Kött & Fisk", meatFish);
        output.put("Drycker", drinks);
        output.put("Skafferi", pantry);
        output.put("Mejeri", dairy);
        output.put("Bröd", brod);

        return output;
    }

    private boolean isDairy(ProductCategory category) {
        return category == ProductCategory.DAIRIES;
    }

    private boolean isDrinks(ProductCategory category) {
        return category == ProductCategory.HOT_DRINKS || category == ProductCategory.COLD_DRINKS;
    }

    private boolean isMeatFish(ProductCategory category) {
        return category == ProductCategory.FISH || category == ProductCategory.MEAT;
    }

    private boolean isVegetables(ProductCategory category) {
        return category == ProductCategory.POD || category == ProductCategory.ROOT_VEGETABLE ||
                category == ProductCategory.VEGETABLE_FRUIT ||
                category == ProductCategory.CABBAGE;
    }

    private boolean isFruit(ProductCategory category) {
        if (category == ProductCategory.BERRY) {
            return true;
        } else if (category == ProductCategory.CITRUS_FRUIT) {
            return true;
        } else if (category == ProductCategory.FRUIT) {
            return true;
        } else if (category == ProductCategory.MELONS) {
            return true;
        } else if (category == ProductCategory.EXOTIC_FRUIT) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSweets(ProductCategory category) {
        return category == ProductCategory.BREAD;
    }

    private String StringToCat(String category) {
        return switch (category) {
            case "Baljväxter" -> "pod";
            case "Bröd" -> "bread";
            case "Bär" -> "berry";
            case "Citrusfrukter" -> "citrus_fruit";
            case "Varma drycker" -> "hot_drinks";
            case "Kalla drycker" -> "cold_drinks";
            case "Exotiska frukter" -> "exotic_fruit";
            case "Fisk" -> "fish";
            case "Grönsaksfrukter" -> "vegetable_fruit";
            case "Kål" -> "cabbage";
            case "Kött" -> "meat";
            case "Mejeri" -> "dairies";
            case "Meloner" -> "melons";
            case "Mjöl, socker och salt" -> "flour_sugar_salt";
            case "Nötter och frön" -> "nuts_and_seeds";
            case "Pasta" -> "pasta";
            case "Potatis och ris" -> "potato_rice";
            case "Rotfrukter" -> "root_vegetable";
            case "Övriga frukter" -> "fruit";
            case "Sötsaker" -> "sweet";
            case "Örter" -> "herb";

            default -> null;
        };

    }
    @FXML
    public void hoverCross(){
        closeDetail.setImage(new javafx.scene.image.Image("resources/icon_close_hover.png"));
    }
    @FXML
    public void hoverCrossnot(){
        closeDetail.setImage(new javafx.scene.image.Image("resources/icon_close.png"));
    }
}