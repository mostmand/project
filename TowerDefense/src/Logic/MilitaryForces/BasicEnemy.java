package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Path;

/**
 * Created by akhavan on 2016-05-09.
 */

public class BasicEnemy extends Enemy{

    /**
     *  The initial values of the parameters of a Enemy of this type.
     */

    public static final MilitaryType TYPE = MilitaryType.BASIC;
    public static final Integer INITIAL_PRIZE = 1;
    public static final Integer INITIAL_SPEED = 300;
    public static final Integer INITIAL_HEALTH = 200;

    {
        this.setType(TYPE);
        this.setCost(INITIAL_PRIZE);
        this.setSpeed(INITIAL_SPEED);
        this.setHealth(INITIAL_HEALTH);
    }

    public BasicEnemy(Map gameMap, Path path, int xCoordinate, int yCoordinate){
        super( gameMap, path, xCoordinate, yCoordinate );
    }

    public BasicEnemy(Map gameMap, Path path){
        super( gameMap, path );
    }

    @Override
    public void activateAfterAttackEffects(Tower attacker) {

    }
}