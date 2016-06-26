import Logic.Game;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by akhavan on 2016-04-18.
 */
public class GameEngine extends Application{

    public static void main(String[] args) {
        launch(args);
    }


    public static final int WINDOW_HEIGHT = 300;
    public static final int WINDOW_WIDTH = 300;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Button startButton = new Button("Start Game");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                primaryStage.close();
                startGame(primaryStage);
            }
        });
        group.getChildren().addAll(startButton);
        Scene scene = new Scene(group, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    Game game;

    public void startGame(Stage stage){
        game = new Game();

        Node map = generateMap();
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(map);

        Scene scene = new Scene(stackPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();

    }

    private RotateTransition createRotator(Node card) {
        RotateTransition rotator = new RotateTransition(Duration.millis(10000), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setCycleCount(10);
        rotator.setInterpolator(Interpolator.LINEAR);
        return rotator;
    }

    public Node generateMap(){
        VBox map = new VBox();
        for (int i = 0; i < game.gameMap.height; i++) {
            HBox row = new HBox();
            for (int j = 0; j < game.gameMap.width; j++) {
                row.getChildren().addAll(new Circle(10));
            }
            map.getChildren().addAll(row);
        }
        return map;
    }
}