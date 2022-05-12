package Feature;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class Feature extends AnchorPane {

    @FXML private ImageView featureImageView;
    @FXML private Button toErbButton;
    private static final javafx.scene.image.Image feature = new javafx.scene.image.Image("resources/feature.png");
    IMatDataHandler handler = IMatDataHandler.getInstance();
    public Feature(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FeatureItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        featureImageView.setImage(feature);
    }



}
