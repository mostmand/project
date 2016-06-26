package Logic;

import Logic.Map.Map;
import Logic.Map.Path;
import Logic.MilitaryForces.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-06-09.
 */
public class Game {


    public Game(){
        this.gameMap = new Map();
        this.player = new User();
        this.towers = new ArrayList<>(0);
        this.enemies = new ArrayList<>(0);
    }

    private User player;
    public Map gameMap;


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
                showTheMap();
            }
        },0,13);
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
     * @return 0 if the tower was set properly
     *         1 if the user money was insufficient
     *         2 if the coordinates are invalid
     */
    public int setTower(int x, int y, MilitaryType type){
        int price = 0;
        if (type == MilitaryType.BASIC)
            price = BasicTower.INITIAL_PRICE;
        if (player.balance < price){
            return 1;
        }
        if (x < 1 || x > gameMap.width || y < 1 || y > gameMap.height || gameMap.getSector(x,y).pathIn != null){
            return 2;
        }
        player.balance -= price;
        this.makeBasicTower(x, y);
        return 0;
    }

    /**
     * Gets coordinates from the UserInterface.
     * Makes a tower at given coordinates.
     * @param xCoordinate the X coordinate at which the Tower will be placed
     * @param yCoordinate the Y coordinate at which the Tower will be placed
     */
    private void makeBasicTower(int xCoordinate, int yCoordinate){
        Tower newTower = new BasicTower(this.enemies, this.gameMap, xCoordinate, yCoordinate);
        towers.add(newTower);
        this.gameMap.getSector(xCoordinate,yCoordinate).occupant.add(newTower);
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
            enemies.get(i).move();
        }
    }

    public void removeMilitary(Military military){
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

    private void showTheMap() {
        for (int i = 1; i <= gameMap.height; i++) {
            for (int j = 1; j <= gameMap.width; j++) {
                if (!gameMap.getSector(i,j).occupant.isEmpty()){
                    if (gameMap.getSector(i,j).occupant.get(0) instanceof Tower)
                        System.out.print("T");
                    else
                        System.out.print("E" + ((Enemy)gameMap.getSector(i,j).occupant.get(0)).getHealth().toString());
                }
                else if (gameMap.getSector(i,j).pathIn != null){
                    System.out.print(".");
                }
                else{
                    System.out.print("-");
                }
                System.out.print("     ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println("__________________________________________________________________");
        System.out.println();
        System.out.println();

    }

    public static void main(String[] args) {
        Game g = new Game();
        g.setTower(1,1, MilitaryType.BASIC);

        g.showTheMap();
        System.out.println();

        g.startGame();
    }



}
