import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-18.
 */
public class Game{
    private User player;
    private Map gamemap;
    Timer globalTime;
    static Scanner input = new Scanner(System.in);


    public Game() {
        this.gamemap = new Map(10);
        this.player = new User();
        this.greetingMessage();
        this.setCastles();
        this.startGame();
    }

    public void greetingMessage(){
        System.out.println("Welcome");
    }
    public void setCastles(){
        String str;
        while(true){
            str = input.next();
            if (str.equals("Ready"))
                break;
            if (str.equals("Tower1")){
                int x = input.nextInt();
                int y = input.nextInt();

                if (player.balance < Tower1.price){
                    System.out.println("Not Enough money to buy this tower");
                    break;
                }

                if (x < 1 || x > gamemap.length || y < 1 || y > gamemap.length || gamemap.sectors[x-1][y-1].inPath){
                    System.out.println("Invalid tower coordinates");
                    break;
                }
                player.balance -= Tower1.price;
//                System.out.println(gamemap.sectors[x-1][y-1].occupant);
                gamemap.sectors[x-1][y-1].occupant.add(new Tower1());
            }
        }
    }

    public void startGame(){
        globalTime = new Timer();
        globalTime.schedule(new TimerTask() {
            @Override
            public void run() {
//                game: while(true){
                    System.out.println("inGame");
                    for (int i = 0; i < gamemap.length; i++) {
                        for (Map.Sector s : gamemap.sectors[i]) {
                            if (s.isOccupiedByTower()){
                                monitorSurroundingsOf(s);
                            }
                            else if (s.isOccupiedByEnemy()){
                                gamemap.moveWhateverIsIn(s);
                                if (gamemap.castle.isOccupiedByEnemy()){
                                    player.castleHealth -= gamemap.castle.occupant.size();
                                    while(!gamemap.castle.occupant.isEmpty())
                                        gamemap.castle.occupant.remove(0);
//                                    if (player.castleHealth <= 0)
//                                        break game;
                                }
                            }
                        }
                    }
                }
//            }
        }, 0, 1000);


    }

    public void monitorSurroundingsOf(Map.Sector s){
        Tower tower = (Tower) s.occupant.get(0);
        int x = s.xCoordinate;
        int y = s.yCoordinate;
        Map.Sector ns;
        for (int radius = 1; radius <= tower.getViewRange(); radius++){
            for (int xdif = -radius; xdif <= radius; xdif++){
                int ydif = radius - Math.abs(xdif);
                if (0<=x+xdif && x+xdif<gamemap.length && 0<=y+ydif && y+ydif<gamemap.length){
                    ns = gamemap.sectors[x+xdif][y+ydif];
                    if (ns.isOccupiedByEnemy()){
                        try{
                            tower.hit(ns.occupant.get(0));
                            if (((Enemy)ns.occupant.get(0)).getHealth() <= 0){
                                player.balance += ((Enemy)ns.occupant.get(0)).getCost();
                                ns.occupant.remove(0);
                            }
                        }catch(Exception e){

                        }
                    }
                }
                ydif = -ydif;
                if (0<=x+xdif && x+xdif<gamemap.length && 0<=y+ydif && y+ydif<gamemap.length){
                    ns = gamemap.sectors[x+xdif][y+ydif];
                    if (ns.isOccupiedByEnemy()){
                        try{
                            tower.hit(ns.occupant.get(0));
                            if (((Enemy)ns.occupant.get(0)).getHealth() <= 0)
                                ns.occupant.remove(0);
                        }catch(Exception e){
                            System.out.println();
                        }
                    }
                }

            }
        }

    }



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
    }


    public void refreshScreen(){

    }

}