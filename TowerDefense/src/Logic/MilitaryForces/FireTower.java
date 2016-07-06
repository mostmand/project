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
    static public final MilitaryType HIGH_PERFORMANCE = MilitaryType.TREE;
    static public final MilitaryType LOW_PERFORMANCE = MilitaryType.LIGHT;

    {
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setPower(INITIAL_POWER);
        this.setReloadTime(INITIAL_ATTACK_SPEED);
        this.setType(TYPE);
        this.highPerformance = HIGH_PERFORMANCE;
        this.lowPerformance = LOW_PERFORMANCE;
    }

    public FireTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate){
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }


}