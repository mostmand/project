import java.util.Timer;

/**
 * Created by akhavan on 2016-04-17.
 */
public abstract class Enemy extends Military{
    Timer reloadtime = new Timer();

    private Integer price;
    private Integer power;
    private Integer speed;
    private Integer health;

    public void setPower(Integer power) {
        this.power = power;
    }
    public Integer getPower() {
        return power;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public Integer getSpeed() {
        return speed;
    }

    @Override
    public void monitorSurroundings(){
        if(this.getGameMap().sectors[this.getSector().xCoordinate][this.getSector().yCoordinate].isOccupiedByTower()){
            attack();
        }
    }

    @Override
    public void attack() {

    }

}

class Enemy1 extends Enemy{
    //the values here must be changed at a later time
    static Integer price = 1;
    static Integer speed = 300;
    static Integer power = 200;
    static Integer initialHealth = 15;


    Enemy1(Map gameMap, Map.Sector sector) {
        this.setGameMap(gameMap);
        this.setSector(sector);
    }

    Enemy1(){
        this.setPrice(price);
        this.setPower(power);
        this.setSpeed(speed);
        this.setHealth(initialHealth);
    }
}
