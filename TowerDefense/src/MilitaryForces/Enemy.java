package MilitaryForces;

import java.util.Date;

/**
 * Created by akhavan on 2016-04-17.
 */

public abstract class Enemy extends Military {
//    Timer reloadTime = new Timer();

    private Integer cost;
    private Integer speed;
    private Integer health;
    private Boolean canMove = true;

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHealth() {
        return health;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public Integer getSpeed() {
        return speed;
    }

    private long timeOfLastMovement = 0;

    private boolean canMove(){
        return (new Date()).getTime() >= timeOfLastMovement + this.getSpeed();
    }

    /**
     *   Moves the Enemy one Sector forward in the Path that it is in.
     *   Requires access to the Map.
     *   Resets the value of timeOfLastMovement to current Time.
     */

    public void move(){
        if (!canMove())
            return;
        //Some Code involving the Map and Sectors...
        //When the moving is done
        timeOfLastMovement = (new Date()).getTime();
    }
}
