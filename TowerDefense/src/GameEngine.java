import Logic.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by akhavan on 2016-04-18.
 */
public class GameEngine extends Application{

    public static void main(String[] args) {
        Application.launch(GameEngine.class, new String[0]);
    }


    public static final int WINDOW_HEIGHT = 600;
    public static final int WINDOW_WIDTH = 900;

    Image image = new Image("View/Images/grass.png");



    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Button startButton = new Button("Start Game");
        startButton.setOnAction(event -> {
//                primaryStage.close();
            startGame(primaryStage);
        });
        group.getChildren().addAll(startButton);
        Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Game game;

    public void startGame(Stage stage){
        game = new Game();

        AnchorPane page = null;
        try {
            page = FXMLLoader.load(GameEngine.class.getResource("/View/playScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (page == null)
//            page = new AnchorPane();
        Scene scene = new Scene(page, WINDOW_WIDTH, WINDOW_HEIGHT);
//        scene.setCursor(new ImageCursor(image));
        stage.setScene(scene);
        stage.show();

    }
}