package MilitaryForces;

import java.util.Date;

/**
 * Created by akhavan on 2016-04-16.
 */


public abstract class Tower extends Military {

    /**
     * temporary default constructor
     */

    Tower(){

    }

    /**
     *
     * The Constructor of MilitaryForces.Tower
     *
     * @param getMilitaries is an interface that grants the ability to get the list of all the Militaries in the game.
     */

    public Tower(Abilities getMilitaries) {
        this.getMilitaries = getMilitaries;
    }

    Abilities getMilitaries;

//    Timer reloadTime = new Timer();

    private Integer price;
    private Integer viewRange;
    private Integer power;
    private Integer attackSpeed;

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

    public Integer getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(Integer attackSpeed) {
        this.attackSpeed = attackSpeed;
    }


    /**
     *  The time of the last hit that this Tower made.
     *  Is reset to the current time each time the hit function is called successfully.
     *
     */

    protected long timeOfLastHit = 0;

    /**
     *
     *
     * @return true if the tower can shoot
     */

    public boolean canShoot() {
        return (new Date()).getTime() >= timeOfLastHit + this.getAttackSpeed();
    }


    /**
     *  Gets the ArrayList of Military and
     *  finds a target to hit considering the precedence.
     *  calls the hit method with the chosen
     *  MilitaryForces.Military as the parameter
     */

    public void findTarget(){

    }



    /**
     * This method is implemented in each child of {@code MilitaryForces.Tower}
     * separately because different towers attack differently
     *
     * @param enemy that should be hit
     * @throws Exception if the Object given to attack is not an MilitaryForces.Enemy.
     */

    public abstract void hit(Object enemy) throws Exception;

}

