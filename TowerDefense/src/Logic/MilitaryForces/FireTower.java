package Logic.MilitaryForces;

import Logic.Map.Map;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-19.
 */
public class FireTower extends Tower{

    /**
     * Characteristics of the FireTower
     */
    static public final MilitaryType TYPE = MilitaryType.FIRE;
    static public final Integer INITIAL_PRICE = 13;
    static public final Integer INITIAL_VIEW_RANGE = 5;
    static public final Integer INITIAL_POWER = 300;
    static public final Integer INITIAL_ATTACK_SPEED = 500;

    {
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setPower(INITIAL_POWER);
        this.setAttackSpeed(INITIAL_ATTACK_SPEED);
        this.setType(TYPE);
    }

    public FireTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate){
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }


    @Override
    public void hit(Enemy enemy) {
        int strikePower = this.getPower();
        if (enemy.getType() == MilitaryType.TREE)
            strikePower *= 2;
        else if (enemy.getType() == MilitaryType.LIGHT)
            strikePower /= 2;

        this.activateAfterAttackEffects(enemy);
        enemy.activateAfterAttackEffects(this);

        enemy.setHealth(enemy.getHealth() - strikePower);
    }

    @Override
    public void activateAfterAttackEffects(Enemy enemy) {
        //this tower is little tricky...
    }

    public void activateAfterAttackEffects(Enemy enemy, int strikePower) {

    }

}