package Logic.Map;

import Logic.MilitaryForces.Enemy;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-10.
 */
public class Path{


    Path(Sector firstSector){
        this.addSectorToPath(firstSector);
    }

    private ArrayList<Sector> sectors = new ArrayList<>(0);

    public Sector getBeginning(){
        return this.sectors.get(0);
    }

    public void addSectorToPath(Sector added){
        if (!sectors.isEmpty()) {
            sectors.get(sectors.size()-1).endOfPath = false;
            sectors.get(sectors.size()-1).setNextSector(added);
        }
        added.setNextSector(null);
        added.endOfPath = true;
        sectors.add(added);
        added.pathIn = this;
    }

    public void addToPath(Enemy enemy){
        if (!sectors.isEmpty()){
            enemy.setSector(sectors.get(0));
            enemy.getSector().occupant.add(enemy);
        }
    }

    public int distanceToCastle(Sector sector){
        return sectors.size() - this.sectors.indexOf(sector);
    }

}