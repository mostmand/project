import Logic.Game;
import Logic.Map.Sector;
import Logic.MilitaryForces.Enemy;
import Logic.MilitaryForces.Military;
import Logic.MilitaryForces.Tower;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    Image BASIC_ENEMY_IMAGE = new Image("/View/Images/");
    Image FIRE_ENEMY_IMAGE = new Image("/View/Images/fireEnemy.png");
    Image TREE_ENEMY_IMAGE = new Image("/View/Images/treeEnemy.png");
    Image DARK_ENEMY_IMAGE = new Image("/View/Images/darkEnemy.png");
    Image LIGHT_ENEMY_IMAGE = new Image("/View/Images/lightEnemy.png");
    Image grassImage = new Image("/View/Images/grass.png");
    Image pathImage = new Image("/View/Images/path.png");
    Image basicTowerImage = new Image("/View/Images/basicTower.jpg");

    @FXML
    GridPane map;

    @FXML
    public void initialize() {
        game = new Game();
//        game.setTower(2,3, MilitaryType.BASIC);

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
                Image image = grassImage;
                Sector sector = game.gameMap.getSector(i+1, j+1);
                if (sector.pathIn != null){
                    image = pathImage;
                }
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                map.add(imageView, j, i);
//                if (!sector.occupant.isEmpty()){
//                    imageView = new ImageView(getImageOf(sector.occupant.get(0)));
//                    imageView.setFitHeight(40);
//                    imageView.setFitWidth(20);
//                    map.add(imageView, j, i);
//                }
            }
        }
//        ImageView imageView = new ImageView("/View/Images/basicTower.jpg");
//        imageView.setFitHeight(40);
//        imageView.setFitWidth(20);
//        map.add(imageView, 0, 0);
    }

    private Image getImageOf(Military military){
        if (military instanceof Enemy){
            Enemy enemy = (Enemy)military;
            switch (enemy.getType()){
                case BASIC:
                    return BASIC_ENEMY_IMAGE;
                case TREE:
                    return TREE_ENEMY_IMAGE;
                case DARK:
                    return DARK_ENEMY_IMAGE;
                case LIGHT:
                    return LIGHT_ENEMY_IMAGE;
                case FIRE:
                    return FIRE_ENEMY_IMAGE;
            }
        }
        else if (military instanceof Tower){
            return basicTowerImage;
        }
        return null;
    }

    @FXML
    Button renderButton;

    public void renderMilitary(){
        for (int i = 0; i < map.getRowConstraints().size(); i++) {
            for (int j = 0; j < map.getColumnConstraints().size(); j++) {
                Sector sector = game.gameMap.getSector(i+1, j+1);
                ImageView imageView;
                if (!sector.occupant.isEmpty()){
                    imageView = new ImageView(getImageOf(sector.occupant.get(0)));
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(20);
                    map.add(imageView, j, i);
                }
            }
        }
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
