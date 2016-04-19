import java.util.Timer;

/**
 * Created by akhavan on 2016-04-16.
 */
public abstract class Tower extends Military{
    Timer reloadtime = new Timer();

    private Integer power;
    private Integer viewRange;
    private Integer attackspeed;


//    public abstract void monitorSurroundings();
//    public abstract void attack();
    Sector[] surroundings;
    public void monitorSurroundings(){
        for (Sector sector: surroundings) {
            if (sector.isOccupied() && sector.isOccupiedByEnemy()) {
                this.attack(sector);
            }
        }
    }
    public void attack(Sector sector){
        Enemy prey;
        for (int i = 0; i < sector.occupant.size(); i++) {
            prey = (Enemy)sector.occupant.get(i);
        }
    }
}

class Tower1 extends Tower{
    static Integer price = 10;
    static Integer viewRange;
    static Integer attackSpeed;
    Tower1(){
        this.setPrice(price);
    }
}

class TowerThread{

}