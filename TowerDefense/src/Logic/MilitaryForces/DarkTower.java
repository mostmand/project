package Logic.MilitaryForces;

import Logic.Map.Map;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-26.
 */
public class DarkTower extends Tower{

    static public final MilitaryType TYPE = MilitaryType.DARK;
    static public final Integer INITIAL_PRICE = 15;
    static public final Integer INITIAL_VIEW_RANGE = 6;
    static public final Integer INITIAL_POWER = 250;
    static public final Integer INITIAL_ATTACK_SPEED = 350;
    static public final MilitaryType HIGH_PERFORMANCE = MilitaryType.FIRE;
    static public final MilitaryType LOW_PERFORMANCE = MilitaryType.DARK;

    {
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setType(TYPE);
        this.setPower(INITIAL_POWER);
        this.setReloadTime(INITIAL_ATTACK_SPEED);
        this.highPerformance = HIGH_PERFORMANCE;
        this.lowPerformance = LOW_PERFORMANCE;
    }

    /**
     *
     * @param enemies the array list of military that is
     *             used to find the target to attack
     * @param gameMap the map that the tower is in
     * @param xCoordinate of the tower. one based
     * @param yCoordinate of the tower. one based
     */
    public DarkTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate) {
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }

    @Override
    public void hit(Enemy enemy) {
        int strikePower = this.getPower();
        strikePower = super.modifyStrikePower(enemy, strikePower);
        enemy.setSpeed(enemy.getSpeed() + 50);
        enemy.getDamage(this, strikePower);
    }

    @Override
    public void activateAfterAttackEffects(Enemy enemy) {

    }
}
