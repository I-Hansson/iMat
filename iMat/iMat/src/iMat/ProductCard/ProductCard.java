package ProductCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Main.iMatController;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;

public class ProductCard extends AnchorPane {

@FXML
private ImageView productImageView;
@FXML
private Label productNameLabel;
@FXML
private Label priceLabel;
@FXML
private TextField productAmount;

@FXML Label unitLabel;

@FXML
private Button incButton;
@FXML
private Button decButton;
@FXML
private AnchorPane buyButton;
@FXML
private AnchorPane ecoAnchorPane;

@FXML private AnchorPane mainPane;

@FXML ImageView favoriteItem;

private iMatController iMatController;


    IMatDataHandler handler = IMatDataHandler.getInstance();
    private ShoppingItem shoppingItem;
    private Product product;
    private int amount = 0;

    private static final javafx.scene.image.Image addImage = new javafx.scene.image.Image("resources/add.png");
    private static final javafx.scene.image.Image minusImageRes = new javafx.scene.image.Image("resources/remove.png");
    private static final javafx.scene.image.Image favoriteFullImage = new javafx.scene.image.Image("resources/favorite.png");
    private static final javafx.scene.image.Image favoriteEmptyImage = new Image("resources/favorite_empty.png");

    public ProductCard(ShoppingItem shop){
        product = shop.getProduct();
        shoppingItem = shop;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        /*DropShadow dropShadow = new DropShadow();

        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        mainPane.setEffect(dropShadow);
*/
        productNameLabel.setText(product.getName());
        priceLabel.setText(String.format("%.2f",product.getPrice()));
        unitLabel.setText(product.getUnit());

        productImageView.setImage(handler.getFXImage(product));

        if(handler.isFavorite(product)) {
            favoriteItem.setImage(favoriteFullImage);
        } else {
            favoriteItem.setImage(favoriteEmptyImage);
        }


    }
    public void Hello() {
        System.out.println(product.getName());
    }


}
