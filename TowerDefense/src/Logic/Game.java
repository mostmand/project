package Logic;

import Logic.Exceptions.InsufficientBalanceException;
import Logic.Exceptions.InvalidCombinationException;
import Logic.Exceptions.InvalidCoordinatesException;
import Logic.Map.Map;
import Logic.Map.Path;
import Logic.Map.Sector;
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

    public static GameTime gameTime = new GameTime();

    /**
     * Consists of a timer that does all the stuff
     * that needs to be done every period.
     */

    Timer thisTimer;
    public void startGame(){
        gameTime.startTime();
        sendInEnemyWaveOf(MilitaryType.LIGHT);
        thisTimer = new Timer();
        thisTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                doAttacksAndMoves();
            }
        },0,1);
    }

    public void pauseGame(){
        gameTime.pauseTime();
        thisTimer.cancel();
    }

    /**
     * Contains the list of all the Tower s and Enemy s that have been built.
     */
    private ArrayList<Tower> towers;
    public ArrayList<Enemy> enemies;

    public void setTower(int xCoordinate, int yCoordinate, MilitaryType type) throws Exception {
        Sector s = gameMap.getSector(xCoordinate, yCoordinate);
        if (s.occupant.isEmpty()){
            makeTower(xCoordinate, yCoordinate, type);
        }
        if (!s.occupant.isEmpty() && s.occupant.get(0) instanceof Tower){
            Tower t = (Tower) s.occupant.get(0);
            if (t.getType() == type){
                upgradeTower(t);
            }
            else{
                if (!t.canCombine(type))
                    throw new InvalidCombinationException();
                t.combine(type);
            }
        }
    }

    /**
     * Sets the tower with type, type, at the given coordinates -->(x,y)
     * @param x of the tower
     * @param y of the tower
     * @param type of the tower
     * @throws Exception
     */
    private void makeTower(int x, int y, MilitaryType type) throws Exception{
        Integer price = type.initialPrice();
        if (player.balance < price){
            throw new InsufficientBalanceException();
        }
        if (x < 1 || x > gameMap.width || y < 1 || y > gameMap.height || gameMap.getSector(x,y).inPath()){
            throw new InvalidCoordinatesException();
        }
        player.balance -= price;

        Constructor c;
        c = type.getTowerType().getConstructor(ArrayList.class, Map.class, int.class, int.class);
        Tower newTower;
        newTower = (Tower) c.newInstance(this.enemies, gameMap, x, y);
        towers.add(newTower);
        this.gameMap.getSector(x,y).occupant.add(newTower);
    }

    /**
     * Upgrades the given tower and returns an error code in case of an error
     * @param tower that is to be upgraded
     * @throws Exception
     */
    private void upgradeTower(Tower tower) throws Exception{
        if (tower == null) {
            return;
        }
        if (player.balance < tower.getType().initialPrice()){
            throw new InsufficientBalanceException();
        }
        player.balance -= tower.getType().initialPrice()/2;
        tower.upgrade();
    }

    /**
     * Combines two towers
     * @param baseTower that something is going to be combined with this.
     * @param combinedTower that is going to be put on the baseTower.
     * @throws Exception if towers can't be combined.
     */
    private void combineTowers(Tower baseTower, Tower combinedTower) throws Exception{
        if (baseTower == null || combinedTower == null)
            return;
        if (baseTower.getType() == combinedTower.getType()){

        }
        if (!baseTower.canCombine(combinedTower))
            throw new InvalidCombinationException();
        baseTower.combine(combinedTower);
    }

    public void modifyTower(MilitaryType type, int xCoordinate, int yCoordinate) throws Exception {

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
                player.balance += enemies.get(i).getCost();
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
        if (military == null)
            return;
        if (military instanceof Tower)
            towers.remove(military);
        else if (military instanceof Enemy)
            enemies.remove(military);
        if (military.getSector() != null)
            military.getSector().occupant.remove(military);
    }

    class SendInWave extends TimerTask{

        Timer timer;
        Constructor c;


        public SendInWave(Timer timer) {
            this.timer = timer;
            try {
                c = BasicEnemy.class.getConstructor(Map.class, Path.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        public SendInWave(Timer timer, MilitaryType type) {
            this.timer = timer;
            try {
                c = type.getEnemyType().getConstructor(Map.class, Path.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        int cnt = 0;

        public boolean finished(){
            return cnt > 5;
        }

        @Override
        public void run() {
            if (finished()){
                timer.cancel();
                return;
            }
            for (Path p:gameMap.paths) {
                try {
                    enemies.add((Enemy) c.newInstance(gameMap, p));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            cnt++;
        }

    }

    private void sendInEnemyWave(){
        Timer timer = new Timer();
        SendInWave task = new SendInWave(timer, MilitaryType.FIRE);
        timer.schedule(task, 0, 3000);
    }

    private void sendInEnemyWaveOf(MilitaryType type){
        Timer timer = new Timer();
        SendInWave task = new SendInWave(timer);
        timer.schedule(task, 0, 3000);
    }

    public void showTheMap() {
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

}
