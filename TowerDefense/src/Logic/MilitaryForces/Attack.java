package Logic.MilitaryForces;

import Logic.Map.Sector;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-07-06.
 */

public class Attack {

    /**
     * Constructor of Attack
     * @param attacker is the tower that is going to attack
     * @param enemy is the enemy that is going to be attacked at.
     */
    public Attack(Tower attacker, Enemy enemy){
        this.attacker = attacker;
        this.enemy = enemy;
        this.inflict();
    }

    Tower attacker;
    Enemy enemy;


    /**
     * Damage that is going to be inflicted to the enemy at the end
     */
    private int damage;

    public synchronized void inflict(){
        this.damage = attacker.getPower();
        if (enemy.getType() == attacker.highPerformance){
            this.damage *= 2;
        }
        else if (enemy.getType() == attacker.lowPerformance){
            this.damage /= 2;
        }

        switch (enemy.getType()){
            case TREE:
                isolate();
                break;
            case DARK:
                death();
                break;
            case LIGHT:
                haste();
                break;
            case FIRE:
                slowTower();
        }

        enemy.reduceHealth(damage);

        switch (attacker.getType()){
            case FIRE:
                innerFire();
                break;
            case TREE:
                stun();
                break;
            case LIGHT:
                splash();
                break;
            case DARK:
                slowEnemy();
                break;
        }
        if (attacker.combinedWith != null){
            switch (attacker.combinedWith){
                case FIRE:
                    innerFire();
                    break;
                case TREE:
                    stun();
                    break;
                case DARK:
                    slowEnemy();
                    break;
                case LIGHT:
                    splash();
                    break;
            }
        }

    }


    /**
     * Enemy after attack functions
     */
    private void isolate(){
        if (Math.random() < 0.2){
            this.damage = 0;
        }
    }
    private void death(){
        if (Math.random() < 0.05){
            attacker.alive = false;
        }
    }
    private void haste(){
        if (Math.random() < 0.4){
            enemy.setSpeed(enemy.getSpeed()*2);
        }
    }
    private void slowTower(){
        if (Math.random() < 0.4){
            attacker.setReloadTime(attacker.getReloadTime() + 50);
        }
    }


    private void innerFire(){

    }

    private void stun(){
        enemy.stunned = true;
        (new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                enemy.stunned = false;
            }
        }, 100);
    }

    private void splash(){
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Sector s = enemy.gameMap.getSectorAt(enemy.getSector(), i,j);
                if (s == null){
                    continue;
                }
                for (Military m:s.occupant) {
                    if (!(m instanceof Enemy))
                        continue;
                    ((Enemy)m).reduceHealth((int) (0.4*attacker.getPower()));
                }
            }
        }
    }

    private void slowEnemy(){
        enemy.setSpeed(enemy.getSpeed() + 50);
    }
}
