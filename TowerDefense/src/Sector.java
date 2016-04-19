import java.util.ArrayList;

/**
 * Created by mostm on 18/04/2016.
 */
public class Sector {
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
