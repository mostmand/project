package Logic.MilitaryForces;

import Logic.Map.Map;

import java.util.ArrayList;

/**
 * Created by akhavan on 2016-06-20.
 */
public class TreeTower extends Tower{
    /**
     * Characteristics of the TreeTower
     */
    static public final MilitaryType TYPE = MilitaryType.TREE;
    static public final Integer INITIAL_PRICE = 14;
    static public final Integer INITIAL_VIEW_RANGE = 6;
    static public final Integer INITIAL_POWER = 350;
    static public final Integer INITIAL_ATTACK_SPEED = 400;
    static public final MilitaryType HIGH_PERFORMANCE = MilitaryType.DARK;
    static public final MilitaryType LOW_PERFORMANCE = MilitaryType.FIRE;

    {
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setReloadTime(INITIAL_ATTACK_SPEED);
        this.setPower(INITIAL_POWER);
        this.setType(TYPE);
        this.highPerformance = HIGH_PERFORMANCE;
        this.lowPerformance = LOW_PERFORMANCE;
    }

    public TreeTower(ArrayList<Enemy> enemies, Map gameMap, Integer xCoordinate, Integer yCoordinate){
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }

}
