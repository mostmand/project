import Logic.Game;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by akhavan on 2016-06-26.
 */
public class Controller{

    Game game;

    @FXML
    GridPane map;

    @FXML
    public void initialize() {
        game = new Game();

        map.getColumnConstraints().remove(0);
        for (int i = 0; i < game.gameMap.width; i++) {
            ColumnConstraints c = new ColumnConstraints(40,40,40);
            map.getColumnConstraints().add(c);
        }
        map.getRowConstraints().remove(0);
        for (int i = 0; i < game.gameMap.height; i++) {
            RowConstraints c = new RowConstraints(40,40,40);
            map.getRowConstraints().add(c);
        }
        renderMap();
    }

    public void renderMap(){
        for (int i = 0; i < map.getRowConstraints().size(); i++) {
            for (int j = 0; j < map.getColumnConstraints().size(); j++) {
                Image image = new Image("/View/Images/grass.png");
                if (game.gameMap.getSector(i+1, j+1).pathIn != null){
                    image = new Image("/View/Images/path.png");
                }
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                map.add(imageView, j, i);
            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    ImageView grass;

    @FXML
    public void addTower(){
        addingTower = true;
    }

    private boolean addingTower = false;

    @FXML
    public void setTower(){
        if (!addingTower)
            return;
        ObservableList o = map.getChildren();
        ((ImageView)o.get(0)).setImage(grass.getImage());
        addingTower = false;
    }


}
