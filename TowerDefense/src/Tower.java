import java.util.Timer;

/**
 * Created by akhavan on 2016-04-16.
 */
public abstract class Tower extends Military{
    Timer reloadtime = new Timer();

    private Integer viewRange;
    private Integer power;
    private Integer attackspeed;

    public Integer getViewRange() {
        return viewRange;
    }

    public void setViewRange(Integer viewRange) {
        this.viewRange = viewRange;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAttackspeed() {
        return attackspeed;
    }

    public void setAttackspeed(Integer attackspeed) {
        this.attackspeed = attackspeed;
    }

//        public abstract void monitorSurroundings();
//    public abstract void attack();
//    Sector[] surroundings;
//    public void monitorSurroundings(){
//        for (Sector sector: surroundings) {
//            if (sector.isOccupied() && sector.isOccupiedByEnemy()) {
//                this.attack(sector);
//            }
//        }
//    }
//    public void attack(Sector sector){
//        Enemy prey;
//        for (int i = 0; i < sector.occupant.size(); i++) {
//            prey = (Enemy)sector.occupant.get(i);
//        }
//    }
}

class Tower1 extends Tower{
    static Integer price = 11;
    static Integer initialhealth = 1000;

    static Integer viewRange = 7;
    static Integer power = 200;
    static Integer attackSpeed = 300;

    Tower1(){
        this.setPrice(price);
        this.setHealth(initialhealth);
        this.setViewRange(viewRange);
        this.setPower(power);
        this.setAttackspeed(attackSpeed);
    }
}