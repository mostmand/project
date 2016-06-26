package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Path;

/**
 * Created by mostm on 26/06/2016.
 */
public class DarkEnemy extends Enemy{
    /**
     *  The initial values of the parameters of a Enemy of this type.
     */

    public static final MilitaryType TYPE = MilitaryType.BASIC;
    public static final Integer INITIAL_PRIZE = 1;
    public static final Integer INITIAL_SPEED = 700;
    public static final Integer INITIAL_HEALTH = 1800;

    {
        this.setType(TYPE);
        this.setCost(INITIAL_PRIZE);
        this.setSpeed(INITIAL_SPEED);
        this.setHealth(INITIAL_HEALTH);
    }

    public DarkEnemy(Map gameMap, Path path, int xCoordinate, int yCoordinate){
        super( gameMap, path, xCoordinate, yCoordinate );
    }

    public DarkEnemy(Map gameMap, Path path){
        super( gameMap, path );
    }

    @Override
    public void getDamage(Tower tower, int damage) {
        if (Math.random() < 0.05)
            tower.alive = false;
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public void activateAfterAttackEffects(Tower attacker) {

    }
}
