import MilitaryForces.Enemy;
import MilitaryForces.Military;
import MilitaryForces.Tower;

import java.io.File;
import java.util.ArrayList;

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
        this.configureMap();
    }

    Sector[][] sectors;
    Sector castle;
    Integer height;
    Integer width;

    class Sector {

        public Sector(Integer xCoordinate, Integer yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
//            occupant = new ArrayList<>();
        }

        /**
         * The sector is occupied by anything
         * path sectors can only be occupied by enemies
         * and non-path sectors can be occupied by military forces (Towers).
         */
        boolean isOccupied;


        Integer xCoordinate;
        Integer yCoordinate;
//        ArrayList<Military> occupant;

//        public Sector position(){
//            return this;
//        }

//        public boolean isOccupied(){
//            return !occupant.isEmpty();
//        }
//
//        public boolean isOccupiedByEnemy(){
//            for (Military force : occupant) {
//                if (force instanceof Enemy)
//                    return true;
//            }
//            return false;
//        }
//
//        public boolean isOccupiedByTower(){
//            for (Military force : occupant) {
//                if (force instanceof Tower)
//                    return true;
//            }
//            return false;
//        }

        boolean inPath;

        private Sector nextSector = null;

        public Sector getNextSector() {
            return nextSector;
        }

    }


    /**
     *  Configures the Map including:
     *  1. Adding paths from sources selected by the user or someone else
     *  2. Some other operations that configure the Map.
     *
     *  Operation1: Add path
     *      Adds a path using one of the addPath functions ,based on how the path is given.
     *
     */

    public void configureMap(){
        //get the map from the selected source
        this.getPath();
//        this.addPath();
    }

    /**
     * Default getPath method
     * Used for giving a Path to the Map manually
     * @return an two dimensional array, containing
     *         the coordinates of the breaking points of the Path.
     */

    public Integer[][] getPath(){

        Integer[][] path = new Integer[10000][2];

        int cnt = 0;
        path[cnt][0] = 0;
        path[cnt][1] = 5;
        cnt++;
        path[cnt][0] = 9;
        path[cnt][1] = 5;

        return path;
    }

    /**
     *
     * @param file that shows the next path
     * @return
     */

//    public Integer[][] getPath(File file){
//
//    }


    /**
     * We show the path in the file using zeros and ones
     * Ones indicating the path, then we get the path input from file
     * This method modifies the {inPath} and {nextSector} field of
     * @param file shows the Map with the mentioned characteristics
     * @throws Exception if the Path is invalid (intersecting other path, self-intersecting, ...)
     */

    public void addPath(File file) throws Exception {

    }


}
