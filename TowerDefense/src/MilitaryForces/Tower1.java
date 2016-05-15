package MilitaryForces;

import java.util.Date;

/**
 * Created by akhavan on 2016-05-09.
 */
public class Tower1 extends Tower{

    static public final Integer INITIAL_PRICE = 11;
    static public final Integer INITIAL_VIEW_RANGE = 7;
    static public final Integer INITIAL_POWER = 200;
    static public final Integer INITIAL_ATTACK_SPEED = 300;

    /**
     * temporary default constructor
     */

    public Tower1(){

    }


    /**
     *
     * @param abilities
     */

    public Tower1(Abilities abilities){
        super(abilities);
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setPower(INITIAL_POWER);
        this.setAttackSpeed(INITIAL_ATTACK_SPEED);
    }

    @Override
    public void hit(Object enemy) throws Exception{
        if (!(enemy instanceof Enemy))
            throw new Exception("Prey not an MilitaryForces.Enemy");
        if (this.canShoot()){
            ((Enemy)enemy).setHealth(((Enemy)enemy).getHealth() - this.getPower());
            this.timeOfLastHit = (new Date()).getTime();
        }
    }

}