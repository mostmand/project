package Logic.Map;

import Logic.MilitaryForces.Military;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-10.
 */
public class Sector {

    public Sector(Integer xCoordinate, Integer yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public ArrayList<Military> occupant = new ArrayList<>();

    /**
     * The sector is occupied by anything
     * path sectors can only be occupied by enemies
     * and non-path sectors can be occupied by military forces (Towers).
     */
    public boolean isOccupied(){
        return !occupant.isEmpty();
    }

    public boolean endOfPath;
    public int xCoordinate;
    public int yCoordinate;

    public Path pathIn;

    public boolean inPath(){
        return pathIn != null;
    }

    private Sector nextSector = null;

    public void setNextSector(Sector nextSector) {
        this.nextSector = nextSector;
    }

    public Sector getNextSector() {
        return nextSector;
    }

    public int distanceToCastle(){
        if (pathIn == null)
            return -1;
        return pathIn.distanceToCastle(this);
    }

    public double distanceFromSector(Sector sector){
        int deltaX = this.xCoordinate - sector.xCoordinate;
        int deltaY = this.yCoordinate - sector.yCoordinate;
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }

}
