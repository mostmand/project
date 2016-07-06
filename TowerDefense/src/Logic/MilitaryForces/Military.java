package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Sector;

/**
 * Created by akhavan on 2016-04-18.
 */
public abstract class Military{

    /**
     * The map that this piece is in
     */
    public Map gameMap;

    /**
     * The coordinates of this piece inside the map that it is in
     * The coordinates are on based meaning they are in the following intervals:
     * x -> [1,map.height] y -> [1,map.width]
     * They may also be null meaning that they are out of the map.
     */
    protected Integer xCoordinate;
    protected Integer yCoordinate;

    Military(){

    }

    /**
     * Constructor of Military
     *
     * @param gameMap the map that it is in
     * @param xCoordinate of the military
     * @param yCoordinate of the military
     */
    public Military(Map gameMap, int xCoordinate, int yCoordinate){
        this.gameMap = gameMap;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Constructor of Military
     * @param gameMap the map that it is in
     * @param sector of the military inside the map.
     *               Used to get the coordinates
     */
    public Military(Map gameMap, Sector sector){
        this.gameMap = gameMap;
        this.setSector(sector);
    }

    /**
     * Extracts the sector the military is in using
     * the coordinates and the map
     * @return the sector that the military is in
     */
    public Sector getSector(){
        return gameMap.getSector(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Set the coordinates the military using
     * the sector taken in the input
     * @param sector that represents the military's location
     */
    public void setSector(Sector sector){
        if (sector == null){
            this.xCoordinate = null;
            this.yCoordinate = null;
            return;
        }
        this.xCoordinate = sector.xCoordinate;
        this.yCoordinate = sector.yCoordinate;
    }

}