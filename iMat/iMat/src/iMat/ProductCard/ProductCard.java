package ProductCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import Main.iMatController;
import se.chalmers.cse.dat216.project.IMatDataHandler;

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
@FXML
private Button incButton;
@FXML
private Button decButton;
@FXML
private AnchorPane buyButton;
@FXML
private AnchorPane ecoAnchorPane;

private iMatController iMatController;

private IMatDataHandler handler;


}
