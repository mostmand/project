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

/**
 * the GUI of the game runs in this class
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }

    @Override
    public void start(Stage primaryStage){
        try {
//            GameController gameController = new GameController();

            /**
             * here we load the mainMenu
             */
            FXMLLoader mainMenuLoader = new FXMLLoader();
            mainMenuLoader.setLocation(Main.class.getResource("/Graphics/mainMenu.fxml"));
            AnchorPane mainMenu = mainMenuLoader.load();
            MainMenuController mainMenuController = (MainMenuController)mainMenuLoader.getController();
            mainMenuController.setStage(primaryStage);
            /*
            here we set the css file
             */
            Scene mainMenuScene = new Scene(mainMenu);
            mainMenuScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(mainMenuScene);


            primaryStage.setResizable(false);
            primaryStage.setTitle("Tower Defense");
            primaryStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
