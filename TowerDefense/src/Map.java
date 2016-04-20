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

    private Sector[][] sectors;
    private Integer length;

    class Sector {

        public Sector(Integer xCoordination, Integer yCoortdination) {
            this.xCoordination = xCoordination;
            this.yCoortdination = yCoortdination;
        }

        private Integer xCoordination;
        private Integer yCoortdination;
        ArrayList<Military> occupant;

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
