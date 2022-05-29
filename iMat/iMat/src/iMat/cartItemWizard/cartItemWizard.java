package cartItemWizard;

import ProductCard.ProductCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class cartItemWizard extends AnchorPane {
    public ProductCard pCard;
    public Product product;

    @FXML
    ImageView imageProduct;
    @FXML
    Label productNameItemWizard;
    @FXML Label amountWiz;
    @FXML Label cost;
    @FXML Label Unit;
    @FXML Label totalCost;
    IMatDataHandler handler = IMatDataHandler.getInstance();
    public static final List<ICartItemWizard> listeners = new ArrayList<>();
    public cartItemWizard(ProductCard pCard){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizardItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        product = pCard.getProduct();
        this.pCard = pCard;
        imageProduct.setImage(handler.getFXImage(product));
        productNameItemWizard.setText(product.getName());
        amountWiz.setText(String.valueOf(pCard.shoppingItem.getAmount()));
        cost.setText(String.valueOf(product.getPrice()));
        Unit.setText(product.getUnit());
        totalCost.setText(String.valueOf(pCard.shoppingItem.getAmount() * product.getPrice()));
    }
    public void updateItemWizard(){
        amountWiz.setText(String.valueOf((int) pCard.shoppingItem.getAmount()));
        totalCost.setText((Math.round(pCard.shoppingItem.getAmount()*product.getPrice() * 100.0)/100.0) + " Kr");
    }
    @FXML
    public void deleteOnClick(){
            for(ICartItemWizard e: listeners){
                e.updateWizItem(this);
                break;
            }
    }
    public void addobservers(ICartItemWizard e){
        listeners.add(e);
    }
    @FXML
    public void inWizAdd(){
        for(ICartItemWizard e: listeners){
            e.addWizItem(this);
            break;
        }
    }
    @FXML
    public void inWizdec(){
        for(ICartItemWizard e: listeners){
            e.decWizItem(this);
            break;
        }
    }

}
