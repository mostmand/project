import Logic.Game;
import Logic.MilitaryForces.Military;
import Logic.MilitaryForces.MilitaryType;
import Logic.User;
import Logic.UserInterface;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-18.
 */
public class GameEngine {

    private User player;
    private Logic.Map.Map gameMap;
    static Scanner input = new Scanner(System.in);
    UserInterface userInterface = new UserInterface();
//
//    public GameEngine() {
//
//        this.greetingMessage();
//        this.initializeGame();
//
//    }
//
//    public void initializeGame(){
//        int width = 10;
//        int height = 10;
//        // The width and height of the Map are somehow got from the User.
//        this.gameMap = new Map(width, height);
//        this.player = new User();
//        this.setCastles();
//    }


    public void greetingMessage(){
//        System.out.println("Welcome");
//        Print a message in the user interface
    }

    /**
     * Contains the list of all the Tower s and Enemy s that have been built.
     */

    ArrayList<Military> Army;
//
//
//    /**
//     * Gets coordinates from the UserInterface.
//     * Makes a tower at given coordinates.
//     * @param xCoordinate the X coordinate at which the Tower will be placed
//     * @param yCoordinate the Y coordinate at which the Tower will be placed
//     */
//
//    public void makeTower(int xCoordinate, int yCoordinate){
//        //getCoordinatesFromUser();
//        Army.add(new BasicTower(new Abilities() {
//            @Override
//            public ArrayList<Military> getMilitaries() {
//                return null;
//            }
//
//            @Override
//            public long getTime() {
//                return (new Date()).getTime();
//            }
//        }));
//    }

//
//    public void setCastles(){
//        String str;
//        while(true){
//            str = input.next();
//            if (str.equals("Ready"))
//                break;
//            if (str.equals("MilitaryForces.BasicTower")){
//                int x = input.nextInt();
//                int y = input.nextInt();
//
//                if (player.balance < BasicTower.INITIAL_PRICE){
//                    userInterface.print("Not Enough money to buy this tower");
//                    break;
//                }
//
//                if (x < 1 || x > gameMap.width || y < 1 || y > gameMap.height || gameMap.sectors[x-1][y-1].pathIn != null){
//                    userInterface.print("Invalid tower coordinates");
//                    break;
//                }
//                player.balance -= BasicTower.INITIAL_PRICE;
//                this.makeTower(x, y);
//            }
//        }
//    }

//    public void startGame(){
//        globalTime = new Timer();
//        globalTime.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                game: while(true){
//                    showTheMap(gameMap);
//                    System.out.println("inGame");
//                    for (int i = 0; i < gameMap.length; i++) {
//                        for (Map.Sector s : gameMap.sectors[i]) {
//                            if (s.isOccupiedByTower()){
//                                monitorSurroundingsOf(s);
//                            }
//                            else if (s.isOccupiedByEnemy()){
//                                gameMap.moveWhateverIsIn(s);
//                                if (gameMap.castle.isOccupiedByEnemy()){
//                                    player.castleHealth -= gameMap.castle.occupant.size();
//                                    while(!gameMap.castle.occupant.isEmpty())
//                                        gameMap.castle.occupant.remove(0);
////                                    if (player.castleHealth <= 0)
////                                        break game;
//                                }
//                            }
//                        }
//                    }
//                }
////            }
//        }, 0, 1000);
//
//
//    }

//    private void showTheMap(Map gamemap) {
//        for(int i = 0; i < gamemap.length; i ++){
//            for(int j = 0; j < gamemap.length; j ++){
//                if(gamemap.sectors[i][j].isOccupiedByEnemy()){
//                    System.out.print("E ");
//                }
//                else if(gamemap.sectors[i][j].isOccupiedByTower()){
//                    System.out.print("T ");
//                }
//                else if(gamemap.sectors[i][j].inPath){
//                    Integer direction = 0;
//                    if(gamemap.sectors[i][j].nextSector.xCoordinate == gamemap.sectors[i][j].xCoordinate){
//                        direction = gamemap.sectors[i][j].nextSector.yCoordinate - gamemap.sectors[i][j].yCoordinate;
//                        if(direction > 0){
//                            System.out.print("l ");
//                        }
//                        else{
//                            System.out.print("r ");
//                        }
//                    }
//                    else{
//                        direction = gamemap.sectors[i][j].nextSector.xCoordinate - gamemap.sectors[i][j].xCoordinate;
//                        if(direction > 0){
//                            System.out.print("d ");
//                        }
//                        else{
//                            System.out.print("u ");
//                        }
//                    }
//                }
//                else{
//                    System.out.print(". ");
//                }
//            }
//            System.out.println();
//        }
//    }




    public void check(){
        System.out.println("Here");
    }

    public TimerTask getTimerTask(){
        return new TimerTask() {
            @Override
            public void run() {
                check();
            }
        };
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.setTower(1,1, MilitaryType.BASIC);
    }


    public void refreshScreen(){

    }

}