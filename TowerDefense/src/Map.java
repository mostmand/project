import java.util.ArrayList;

/**
 * Created by qasem on 4/18/16.
 */
public class Map {
    public Map(int length) {
        this.length = length;
        this.sectors = new Sector[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.sectors[i][j] = new Sector(i+1, j+1);
            }
        }
    }

    Sector[][] sectors;
    Integer length;



    class Sector {

        public Sector(Integer xCoordinate, Integer yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        Integer xCoordinate;
        Integer yCoordinate;
        ArrayList<Military> occupant;

        public Sector position(){
            return this;
        }

        public boolean isOccupied(){
            return !occupant.isEmpty();
        }

        public boolean isOccupiedByEnemy(){
            for (Military force : occupant) {
                if (force instanceof Enemy)
                    return true;
            }
            return false;
        }

        public boolean isOccupiedByTower(){
            for (Military force : occupant) {
                if (force instanceof Tower)
                    return true;
            }
            return false;
        }
    }
}
