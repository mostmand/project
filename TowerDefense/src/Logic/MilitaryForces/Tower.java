package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Sector;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-04-16.
 */


public abstract class Tower extends Military {

    /**
     * temporary default constructor
     */

    public Tower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate){
        super(gameMap, xCoordinate, yCoordinate);
        this.enemies = enemies;
    }

    public Tower(ArrayList<Enemy> enemies, Map gameMap, Sector sector){
        super(gameMap, sector);
        this.enemies = enemies;
    }



    private ArrayList<Enemy> enemies;

    /**
     * Characteristics of the tower
     */
    public boolean alive = true;
    private MilitaryType type;
    private Integer price;
    private Integer viewRange;
    private Integer power;
    private Integer reloadTime;

    public MilitaryType getType() {
        return type;
    }
    public void setType(MilitaryType type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

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

    public Integer getReloadTime() {
        return reloadTime;
    }
    public void setReloadTime(Integer reloadTime) {
        this.reloadTime = reloadTime;
    }


    /**
     *  The time of the last hit that this Tower made.
     *  Is reset to the current time each time the hit function is called successfully.
     */
    protected long timeOfLastAttack = 0;

    /**
     * @return true if the tower can shoot
     */
    public boolean canShoot() {
        return System.currentTimeMillis() >= timeOfLastAttack + this.getReloadTime() && this.alive;
    }


    public boolean inRange(Enemy enemy){
        if (enemy.getSector() == null)
            return false;
        return this.getSector().distanceFromSector(enemy.getSector()) <= this.viewRange;
    }

    /**
     *  Gets the ArrayList of Military and
     *  finds a target to hit considering the precedence.
     *  calls the hit method with the chosen
     *  MilitaryForces.Military as the parameter
     */
    protected Enemy findTarget(){
        Enemy finalTarget = null;
        for (Enemy enemy:this.enemies) {
            if (!inRange(enemy))
                continue;
            if (        finalTarget == null
                    ||  enemy.getHealth() < finalTarget.getHealth()
                    ||  (enemy.getHealth().equals(finalTarget.getHealth()) && enemy.distanceToCastle() < finalTarget.distanceToCastle()))
            {
                finalTarget = enemy;
            }
        }
        return finalTarget;
    }

    public void attack(){
        if (!this.canShoot())
            return;
        Enemy enemy = findTarget();
        if (enemy == null)
            return;
        new Attack(this, enemy);
        this.timeOfLastAttack = System.currentTimeMillis();
    }

    public MilitaryType highPerformance = null;
    public MilitaryType lowPerformance = null;


    /**
     * Upgrades this tower
     */
    public void upgrade(){
        this.price += this.type.initialPrice;
        this.power += 100;
        this.reloadTime += 50;
    }

    public boolean canCombine(Tower tower){
        return (this.highPerformance != tower.lowPerformance) && (this.lowPerformance != tower.highPerformance);
    }

    MilitaryType combinedWith = null;

    public void combine(Tower tower){
        if ((this.highPerformance != tower.lowPerformance) && (this.lowPerformance != tower.highPerformance))
            return;
        if (this.highPerformance == tower.lowPerformance){
            this.highPerformance = null;
        }
        else{
            this.lowPerformance = null;
        }
        tower.price = this.price + tower.price;
        this.combinedWith = tower.type;
        tower.alive = false;
    }
}

