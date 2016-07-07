package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Path;

/**
 * Created by akhavan on 2016-06-20.
 */
public class TreeEnemy extends Enemy {

    /**
     *  The initial values of the parameters of a Enemy of this type.
     */

    public static final MilitaryType TYPE = MilitaryType.TREE;
    public static final Integer INITIAL_PRIZE = 1;
    public static final Integer INITIAL_SPEED = 600;
    public static final Integer INITIAL_HEALTH = 1600;

    {
        this.setType(TYPE);
        this.setCost(INITIAL_PRIZE);
        this.setSpeed(INITIAL_SPEED);
        this.setHealth(INITIAL_HEALTH);
    }

    public TreeEnemy(Map gameMap, Path path, int xCoordinate, int yCoordinate){
        super( gameMap, path, xCoordinate, yCoordinate );
    }

    public TreeEnemy(Map gameMap, Path path){
        super( gameMap, path );
    }

    @Override
    public void getDamage(Tower tower, int damage) {
        if (Math.random() < 0.2)
            damage = 0;
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public void activateAfterAttackEffects(Tower attacker) {

    }
}
