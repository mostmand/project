import MilitaryForces.Enemy;
import MilitaryForces.Military;
import MilitaryForces.Tower;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qasem on 4/18/16.
 */

public class Map {

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.sectors = new Sector[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.sectors[i][j] = new Sector(i+1, j+1);
            }
        }
        this.castle = new Sector(null, null);
//        this.castle.nextSector = this.castle;
        this.editMap();
    }

    Sector[][] sectors;
    Sector castle;
    Integer height;
    Integer width;

    class Sector {

        public Sector(Integer xCoordinate, Integer yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
            occupant = new ArrayList<>();
        }

        Integer xCoordinate;
        Integer yCoordinate;
        ArrayList<Military> occupant;

//        public Sector position(){
//            return this;
//        }

//        public boolean isOccupied(){
//            return !occupant.isEmpty();
//        }

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


    /**
     *  Edits the Map including:
     *  1. Adding paths from sources selected by the user or someone else
     *  2. ...
     */

    public void editMap(){
        //get the map from the selected source
        this.addPath();
    }


    /**
     * We show the path in the file using zeros and ones
     * Ones indicating the path, then we get the path input from file
     * This method modifies the {inPath} and {nextSector} field of
     * @param file shows the Map with the mentioned characteristics
     * @throws Exception if the Path is invalid (intersecting other path, self-intersecting, ...)
     */

    public void addPath(File file) throws Exception {

    }


    Integer[][] path = new Integer[10000][2];

    public void addPath(){
        int cnt = 0;
        /*
         We have to add a method for setting the path, either manual or automatic.
         */
        /*
         this is a temporary path...
         */
        path[cnt][0] = 0;
        path[cnt][1] = 5;
        cnt++;
        path[cnt][0] = 9;
        path[cnt][1] = 5;
        cnt++;
//        while(true){
//            path[cnt][0] = 1;
//            path[cnt][1] = 1;
//            cnt++;
//            break;
//        }
        for (int i = 0; i < cnt-1; i++){
            if (path[i][0].hashCode() == path[i+1][0].hashCode()){
                int sgn = (int) Math.signum(path[i+1][1]-path[i][1]);
                for (int j = path[i][1]; j != path[i+1][1]; j += sgn){
                    this.sectors[path[i][0]][j].inPath = true;
                    this.sectors[path[i][0]][j].nextSector = this.sectors[path[i+1][0]][j+sgn];
                }
            }
            else if (path[i][1].hashCode() == path[i+1][1].hashCode()){
                int sgn = (int)Math.signum(path[i+1][0]-path[i][0]);
                for (int j = path[i][0]; j != path[i+1][0]; j += sgn){
                    this.sectors[j][path[i][1]].inPath = true;
                    this.sectors[j][path[i][1]].nextSector = this.sectors[j+sgn][path[i+1][1]];
                }
            }
        }
        this.sectors[path[cnt-1][0]][path[cnt-1][1]].nextSector = this.castle;
    }

//    public void moveWhateverIsIn(Sector sector){
//        Iterator<Military> iter = sector.occupant.iterator();
//        int cnt = 0;
//        while (iter.hasNext()){
////            System.out.println(cnt);
////            cnt++;
//            Military enemy = iter.next();
//            if (((Enemy)enemy).getCanMove()){
//                sector.nextSector.occupant.add(enemy);
//                iter.remove();
//                ((Enemy)enemy).startExhaustTime();
//            }
//        }
////        for(MilitaryForces.Military enemy: sector.occupant){
////            if(((MilitaryForces.Enemy)enemy).getCanMove()){
////                sector.nextSector.occupant.add(enemy);
////                sector.occupant.remove(enemy);
////                ((MilitaryForces.Enemy) enemy).startExhaustTime();
////            }
////        }
//    }

}
