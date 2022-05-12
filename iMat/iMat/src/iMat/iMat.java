


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.ResourceBundle;

public class iMat extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/RecipeSearch");

        Parent root = FXMLLoader.load(getClass().getResource("iMat/Main/First_main_page.fxml"), bundle);

        Scene scene = new Scene(root, 1222,880);

        primaryStage.setTitle(bundle.getString("application.name"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop(){
        IMatDataHandler.getInstance().shutDown();
    }




}


