package Logic.Map;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by qasem on 4/18/16.
 */

public class Map {

    public Map(){
        this(40, 40);
    }

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.sectors = new Sector[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.sectors[i][j] = new Sector(i+1, j+1);
            }
        }
        this.configureMap();
    }


    public Integer height;
    public Integer width;

    private Sector[][] sectors;

    public Sector getSector(Integer x, Integer y){
        if (x == null || y == null)
            return null;
        if (x < 1 || x > this.height || y < 1 || y > this.width)
            return null;
        return this.sectors[x-1][y-1];
    }

    public ArrayList<Path> paths = new ArrayList<>(0);

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
        this.addDefaultPath();
//        this.addPath();
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


    private void addDefaultPath(){
        Path path = new Path(this.sectors[0][this.width/2]);
        for (int i = 1; i < this.height; i++) {
            path.addSectorToPath(this.sectors[i][this.width/2]);
        }
        this.paths.add(path);
        path = new Path(this.sectors[0][this.width/2+1]);
        for (int i = 1; i < this.height; i++) {
            path.addSectorToPath(this.sectors[i][this.width/2+1]);
        }
        this.paths.add(path);
        path = new Path(this.sectors[0][this.width/2-1]);
        for (int i = 1; i < this.height; i++) {
            path.addSectorToPath(this.sectors[i][this.width/2-1]);
        }
        this.paths.add(path);
    }

    public Sector getSectorAt(Sector sector, int difX, int difY){
        return getSector(sector.xCoordinate + difX, sector.yCoordinate + difY);
    }


}
