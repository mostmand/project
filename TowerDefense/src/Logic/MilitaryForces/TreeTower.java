package Logic.MilitaryForces;

import Logic.Map.Map;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    {
        this.setPrice(INITIAL_PRICE);
        this.setViewRange(INITIAL_VIEW_RANGE);
        this.setAttackSpeed(INITIAL_ATTACK_SPEED);
        this.setPower(INITIAL_POWER);
        this.setType(TYPE);
    }

    public TreeTower(ArrayList<Enemy> enemies, Map gameMap, int xCoordinate, int yCoordinate){
        super(enemies, gameMap, xCoordinate, yCoordinate);
    }

    @Override
    public void hit(Enemy enemy) {
        int damage = this.getPower();
        if (enemy.getType() == MilitaryType.DARK)
            damage *= 2;
        else if (enemy.getType() == MilitaryType.FIRE)
            damage /= 2;
        enemy.getDamage(this, damage);
        Integer enemySpeed = enemy.getSpeed();
        enemy.setSpeed(0);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enemy.setSpeed(enemySpeed);
            }
        }, 100);
    }

    @Override
    public void activateAfterAttackEffects(Enemy enemy) {

    }
}