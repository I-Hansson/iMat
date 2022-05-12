package ProductCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ProductCard extends AnchorPane {

        IMatDataHandler handler = IMatDataHandler.getInstance();
        private ShoppingItem shoppingItem;
        private Product product;
        private int amount = 0;

        private static final Image addImage = new Image("resources/add.png");
        private static final Image minusImageRes = new Image("resources/remove.png");
        private static final Image favoriteFullImage = new Image("resources/favorite.png");
        private static final Image favoriteEmptyImage = new Image("resources/favorite_empty.png");

        @FXML Label productNameLabel;
        @FXML Label priceLable;
        @FXML Label unitLable;
        @FXML ImageView itemImage;
        @FXML ImageView plusImage;
        @FXML ImageView minusImage;
        @FXML TextField amountField;
        @FXML ImageView favoriteImage;
        @FXML AnchorPane mainPane;

        public ProductCard(ShoppingItem shop){
                product = shop.getProduct();
                shoppingItem = shop;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
                fxmlLoader.setRoot(this);
                fxmlLoader.setController(this);

                DropShadow dropShadow = new DropShadow();

                dropShadow.setColor(Color.BLACK);
                dropShadow.setOffsetX(3);
                dropShadow.setOffsetY(3);
                mainPane.setEffect(dropShadow);

                productNameLabel.setText(product.getName());
                priceLable.setText(String.format("%.2f",product.getPrice()));
                unitLable.setText(product.getUnit());

                itemImage.setImage(handler.getFXImage(product));

                if(handler.isFavorite(product)) {
                        favoriteImage.setImage(favoriteFullImage);
                } else {
                        favoriteImage.setImage(favoriteEmptyImage);
                }


                }
                public void Hello() {
                        System.out.println(product.getName());
        }
        }




