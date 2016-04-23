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

    @Override
    public void monitorSurroundings(){
        for (int radius = 1; radius <= viewRange; radius++){
            for (int xdif = -radius; xdif <= radius; xdif++){
                int ydif = radius - Math.abs(xdif);
                if (this.neighboringSector(xdif, ydif) != null){
                    if (this.neighboringSector(xdif, ydif).isOccupiedByEnemy()){
                        attack(this.neighboringSector(xdif, ydif));
                    }
                }
                ydif = -ydif;
                if (this.neighboringSector(xdif, ydif) != null){
                    if (this.neighboringSector(xdif, ydif).isOccupiedByEnemy()){
                        attack(this.neighboringSector(xdif, ydif));
                    }
                }
            }
        }
    }

    @Override
    public void attack(Map.Sector wheretoattack){
        Enemy prey = null;
        for (Military candidate:wheretoattack.occupant) {
            if (prey == null || prey.getHealth() > candidate.getHealth())
                prey = (Enemy) candidate;
        }
        if (prey != null)
            prey.setHealth(prey.getHealth()-this.getPower());
    }

}

class Tower1 extends Tower{

    Tower1(Map gameMap, Map.Sector sector) {
        this.setGameMap(gameMap);
        this.setPosition(sector);
    }

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