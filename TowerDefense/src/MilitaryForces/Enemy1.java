package MilitaryForces;

/**
 * Created by akhavan on 2016-05-09.
 */

public class Enemy1 extends Enemy{

    /**
     *  The initial values of the parameters of a Tower of this type.
     */

    public static final Integer INITIAL_PRIZE = 1;
    public static final Integer INITIAL_SPEED = 300;
    public static final Integer INITIAL_HEALTH = 200;



    public Enemy1(){
        this.setCost(INITIAL_PRIZE);
        this.setSpeed(INITIAL_SPEED);
        this.setHealth(INITIAL_HEALTH);
    }
}