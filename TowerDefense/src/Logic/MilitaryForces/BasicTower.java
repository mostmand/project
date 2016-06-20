package Logic.MilitaryForces;

import Logic.Map.Map;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-05-09.
 */
public class BasicTower extends Tower{

    static public final MilitaryType TYPE = MilitaryType.BASIC;
    static public final Integer INITIAL_PRICE = 11;
    static public final Integer INITIAL_VIEW_RANGE = 7;
    static public final Integer INITIAL_POWER = 100;
    static public final Integer INITIAL_ATTACK_SPEED = 300;

    {
        this.setType(TYPE);
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setPower(INITIAL_POWER);
        this.setAttackSpeed(INITIAL_ATTACK_SPEED);
    }


    /**
     *
     * @param enemies the array list of military that is
     *             used to find the target to attack
     * @param gameMap the map that the tower is in
     * @param xCoordinate of the tower. one based
     * @param yCoordinate of the tower. one based
     */
    public BasicTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate) {
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }

    @Override
    public void hit(Enemy enemy){
        enemy.setHealth(enemy.getHealth() - this.getPower());
    }

    @Override
    public void activateAfterAttackEffects(Enemy enemy) {

    }
}