package Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class startPage extends AnchorPane {
    public startPage(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startpageText.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
