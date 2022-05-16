package ProductCard;


import Main.iMatController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProductCard extends AnchorPane {

@FXML private ImageView productImageView;
@FXML  Text productNameText;
@FXML private Label priceLabel;
@FXML Text productAmount;

@FXML Label unitLabel;
@FXML public  ImageView addButton;
@FXML public ImageView decButton;
@FXML   AnchorPane buyButton;
@FXML  AnchorPane ecoAnchorPane;
@FXML AnchorPane mainPane;
@FXML ImageView infoImageView;
@FXML ImageView favoriteItem;
@FXML private AnchorPane NotBuy;
@FXML AnchorPane buyed;



    IMatDataHandler handler = IMatDataHandler.getInstance();
    public ShoppingItem shoppingItem;
    Product product;





    public static final javafx.scene.image.Image addImage = new javafx.scene.image.Image("resources/addButton.png");
    public static final javafx.scene.image.Image minusImageRes = new javafx.scene.image.Image("resources/decButton.png");
    private static final javafx.scene.image.Image favoriteFullImage = new javafx.scene.image.Image("resources/favorite.png");
    private static final javafx.scene.image.Image favoriteEmptyImage = new Image("resources/favorite_empty.png");
    private static final javafx.scene.image.Image infoImage= new Image("resources/informationLogo.png");
    public static final List<ICard> listeners = new ArrayList<>();

    public ProductCard(ShoppingItem shop){
        product = shop.getProduct();
        shoppingItem = shop;
        shoppingItem.setAmount(0);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println(shoppingItem.getAmount());
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addButton.setImage(addImage);
        decButton.setImage(minusImageRes);
        productNameText.setText(product.getName());
        priceLabel.setText(String.format("%.2f",product.getPrice()));
        unitLabel.setText(product.getUnit());
        productImageView.setImage(handler.getFXImage(product));
        infoImageView.setImage(infoImage);
        setFavPic();
        if(!product.isEcological())
            ecoAnchorPane.setVisible(false);

        update();
    }
    public void update() {
        productAmount.setText(String.valueOf(shoppingItem.getAmount()));
        //System.out.println(product.getName() +" " +shoppingItem.getAmount());

        notifyListeners();

    }
    @FXML
    public void notBuyClicked(){
        buyed.toFront();
        shoppingItem.setAmount(1);

        update();
    }
    @FXML
    public void onClickDecButton(){
        shoppingItem.setAmount(shoppingItem.getAmount()- 1);
        if(shoppingItem.getAmount() == 0){
            buyed.toBack();
        }
        update();
    }
    @FXML
    public void addButtonClicked(){
        shoppingItem.setAmount(shoppingItem.getAmount()+ 1);;
        update();
    }
    public void updateAmount(){
        productAmount.setText(String.valueOf(shoppingItem.getAmount()));
        System.out.println(product.getName() +" " +shoppingItem.getAmount());
    }
    public Product getProduct() {
        return product;
    }

    public void addobservers(ICard e){
        listeners.add(e);
    }
    public void notifyListeners(){
        for( ICard e : listeners){
            e.notifyBuyChange(this);
            break;
        }
    }
    @FXML
    public void makeFavorite(){
        if(!handler.isFavorite(this.getProduct())){
            handler.addFavorite(this.getProduct());

        }else{
            handler.removeFavorite(this.getProduct());
        }
        setFavPic();

    }
    public void setFavPic(){
        if(handler.isFavorite(product)) {
            favoriteItem.setImage(favoriteFullImage);
        }
        else {
            favoriteItem.setImage(favoriteEmptyImage);
        }
    }



    @FXML
    public void openDetailView(){
        for( ICard e : listeners){
            e.populateDetailView(this);
            break;
        }
    }
    public void updateForDetail(){
        if(shoppingItem.getAmount()== 0){
            buyed.toBack();
        }
        if(shoppingItem.getAmount()> 0){
            buyed.toFront();
        }



        productAmount.setText(String.valueOf(shoppingItem.getAmount()));
    }

}
