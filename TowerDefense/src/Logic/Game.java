package Logic;

import Logic.Exceptions.InsufficientBalanceException;
import Logic.Exceptions.InvalidCombinationException;
import Logic.Exceptions.InvalidCoordinatesException;
import Logic.Map.Map;
import Logic.Map.Path;
import Logic.MilitaryForces.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public Game(){
        this.gameMap = new Map();
        this.player = new User();
        this.towers = new ArrayList<>(0);
        this.enemies = new ArrayList<>(0);
    }

    private User player;
    public Map gameMap;

    int level;

    /**
     * Consists of a timer that does all the stuff
     * that needs to be done every period.
     */
    public void startGame(){
        Timer timer = new Timer();
        sendInEnemyWave();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                doAttacksAndMoves();
            }
        },0,1);
    }

    /**
     * Contains the list of all the Tower s and Enemy s that have been built.
     */
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;

    /**
     * Sets the tower with type, type, at the given coordinates -->(x,y)
     * @param x of the tower
     * @param y of the tower
     * @param type of the tower
     * @throws Exception
     */
    public void setTower(int x, int y, MilitaryType type) throws Exception{
        Integer price = type.initialPrice();
        if (player.balance < price){
            throw new InsufficientBalanceException();
        }
        if (x < 1 || x > gameMap.width || y < 1 || y > gameMap.height || gameMap.getSector(x,y).inPath()){
            throw new InvalidCoordinatesException();
        }
        player.balance -= price;

        Constructor c = type.getTowerType().getConstructor(ArrayList.class, Map.class, int.class, int.class);
        Tower newTower;
        newTower = (Tower) c.newInstance(this.enemies, gameMap, x, y);
        towers.add(newTower);
        this.gameMap.getSector(x,y).occupant.add(newTower);
    }

//    /**
//     * Gets coordinates from the UserInterface.
//     * Makes a tower at given coordinates.
//     * @param xCoordinate the X coordinate at which the Tower will be placed
//     * @param yCoordinate the Y coordinate at which the Tower will be placed
//     */
//    private void makeBasicTower(int xCoordinate, int yCoordinate){
//        Tower newTower = new BasicTower(this.enemies, this.gameMap, xCoordinate, yCoordinate);
//        towers.add(newTower);
//        this.gameMap.getSector(xCoordinate,yCoordinate).occupant.add(newTower);
//    }

    /**
     * Upgrades the given tower and returns an error code in case of an error
     * @param tower that is to be upgraded
     * @throws Exception
     */

    public void upgradeTower(Tower tower) throws Exception{
        if (tower == null) {
            return;
        }
        if (player.balance < tower.getType().initialPrice()){
            throw new InsufficientBalanceException();
        }
        player.balance -= tower.getType().initialPrice()/2;
        tower.upgrade();
    }

    public void combineTowers(Tower baseTower, Tower combinedTower) throws Exception{
        if (baseTower == null || combinedTower == null)
            return;
        if (!baseTower.canCombine(combinedTower))
            throw new InvalidCombinationException();
        baseTower.combine(combinedTower);
    }

    private void doAttacksAndMoves(){
        for (int i = 0; i < towers.size(); i++) {
            if (!towers.get(i).alive){
                removeMilitary(towers.get(i));
                i--;
                continue;
            }
            towers.get(i).attack();
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()){
                removeMilitary(enemies.get(i));
                i--;
                continue;
            }
            if (enemies.get(i).getSector() == null){
                player.castleHealth--;
                removeMilitary(enemies.get(i));
                i--;
                continue;
            }
            enemies.get(i).move();
        }
    }

    private void removeMilitary(Military military){
        if (military instanceof Tower)
            towers.remove(military);
        else if (military instanceof Enemy)
            enemies.remove(military);
        military.getSector().occupant.remove(military);
    }

    Integer cnt = 0;

    private void sendInEnemyWave(){
        Timer timer = new Timer();
        cnt = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Path p:gameMap.paths) {
                    enemies.add(new BasicEnemy(gameMap, p));
                }
                cnt++;
                if (cnt >= 5)
                    timer.cancel();
            }
        },0,350);
    }

}
