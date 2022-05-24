package Feature;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Feature extends AnchorPane {
    public static final List<IFeature> listeners = new ArrayList<>();
    @FXML private ImageView featureImageView;
    @FXML  public AnchorPane toErbButton;
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
        featureImageView.setStyle("-fx-background-radius: 15px");
    }

@FXML public void toErb(){
        toErbButton.setVisible(true);
        listeners.get(0).setUpErbjudanden(this);
}
 public void addObserver(IFeature i){
        listeners.add(i);
 }
 @FXML
 public void hover(){

        toErbButton.setStyle("-fx-background-color: rgb(102, 178, 255)");

 }

}
