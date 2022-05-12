package ProductCard;


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
@FXML ImageView addButton;
@FXML  ImageView decButton;
@FXML  AnchorPane buyButton;
@FXML  AnchorPane ecoAnchorPane;
@FXML AnchorPane mainPane;
@FXML ImageView infoImageView;
@FXML ImageView favoriteItem;
@FXML AnchorPane NotBuy;
@FXML AnchorPane buyed;


    IMatDataHandler handler = IMatDataHandler.getInstance();
    private ShoppingItem shoppingItem;
    Product product;
    private int amount = 0;



    private static final javafx.scene.image.Image addImage = new javafx.scene.image.Image("resources/addButton.png");
    private static final javafx.scene.image.Image minusImageRes = new javafx.scene.image.Image("resources/decButton.png");
    private static final javafx.scene.image.Image favoriteFullImage = new javafx.scene.image.Image("resources/favorite.png");
    private static final javafx.scene.image.Image favoriteEmptyImage = new Image("resources/favorite_empty.png");
    private static final javafx.scene.image.Image infoImage= new Image("resources/informationLogo.png");
    public static final List<ICard> listeners = new ArrayList<>();

    public ProductCard(ShoppingItem shop){
        product = shop.getProduct();
        shoppingItem = shop;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

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
        if(handler.isFavorite(product)) {
            favoriteItem.setImage(favoriteFullImage);
        }
        else {
            favoriteItem.setImage(favoriteEmptyImage);

        }

        update();
    }
    public void Hello() {
        System.out.println(product.getName());
    }
    public void update() {
        productAmount.setText(String.valueOf(amount));

    }
    @FXML
    public void notBuyClicked(){
        buyed.toFront();
        amount =1;
        update();
    }
    @FXML
    public void onClickDecButton(){
        amount -= 1;
        if(amount == 0){
            NotBuy.toFront();
        }
        update();
    }
    @FXML
    public void addButtonClicked(){
        amount +=1;
        update();
    }
    public Product getProduct() {
        return product;
    }




}
