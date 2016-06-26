package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Sector;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-21.
 */
public class LightTower extends Tower {

    static public final MilitaryType TYPE = MilitaryType.LIGHT;
    static public final Integer INITIAL_PRICE = 16;
    static public final Integer INITIAL_VIEW_RANGE = 5;
    static public final Integer INITIAL_POWER = 300;
    static public final Integer INITIAL_ATTACK_SPEED = 450;
    static public final MilitaryType HIGH_PERFORMANCE = MilitaryType.LIGHT;
    static public final MilitaryType LOW_PERFORMANCE = MilitaryType.TREE;

    {
        this.setPrice(INITIAL_PRICE);
        this.setType(TYPE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setPower(INITIAL_POWER);
        this.setAttackSpeed(INITIAL_ATTACK_SPEED);
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
    public LightTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate) {
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }

    @Override
    public void hit(Enemy enemy) {
        int strikePower = this.getPower();
        modifyStrikePower(enemy, strikePower);
        enemy.getDamage(this, strikePower);
        for (int i = -1; i <= 1 ; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Sector s = gameMap.getSector(enemy.xCoordinate+i, enemy.yCoordinate+j);
                if (s == null)
                    continue;
                for (Military m:s.occupant) {
                    if (m instanceof Enemy)
                        ((Enemy)m).setHealth((int) (((Enemy)m).getHealth() - 0.4*this.getPower()));

                }
            }
        }
    }

    @Override
    public void activateAfterAttackEffects(Enemy enemy) {

    }
}
