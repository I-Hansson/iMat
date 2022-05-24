package help;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Help extends AnchorPane {

    public Help(){
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("helpitem.fxml"));
     fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);
    try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException(exception);
    }

}
}
