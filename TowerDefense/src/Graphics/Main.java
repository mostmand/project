package Graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by mostm on 30/06/2016.
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage){
        try {
//            GameController controller = new GameController();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Graphics/game.fxml"));
//            loader.setController(Graphics.GameController.class);
            AnchorPane anchorPane = loader.load();
            anchorPane.setId("anchorPane");
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Tower Defense");
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
