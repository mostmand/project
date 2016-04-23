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

        boolean inPath;
        Sector nextSector = null;

//        public Sector nextSector(){
//
//        }
    }

    Integer[][] path = new Integer[10000][2];

    public void addPath(){
        int cnt = 0;
        while(true){
            path[cnt][0] = 1;
            path[cnt][1] = 1;
            cnt++;
            break;
        }
        for (int i = 0; i < cnt-1; i++){
            if (path[i][0].hashCode() == path[i+1][0].hashCode()){
                for (int j = path[i][1]; j != path[i+1][1]; j += Math.signum(path[i+1][1]-path[i][1])){
                    this.sectors[path[i][0]][j].inPath = true;
                    this.sectors[path[i][0]][j].nextSector = this.sectors[path[i+1][0]][j];
                }
            }
            else if (path[i][1].hashCode() == path[i+1][1].hashCode()){
                for (int j = path[i][0]; j != path[i+1][0]; j += Math.signum(path[i+1][0]-path[i][0])){
                    this.sectors[j][path[i][0]].inPath = true;
                    this.sectors[j][path[i][0]].nextSector = this.sectors[path[i+1][0]][j];
                }
            }
        }

    }

}
