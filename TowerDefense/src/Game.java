import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-18.
 */
public class Game{
    private User player;
    private Map gameMap;
    Timer globalTime;
    int counter = 0;
    public void setCastles(){
        //Get info from user
        //Make castles
        //Set them on the map
    }

    public void startGame(){
        globalTime = new Timer();
        globalTime.schedule(new TimerTask() {
            @Override
            public void run() {
                while(true){
                    for (int i = 0; i < gameMap.length; i++) {
                        for (Map.Sector s : gameMap.sectors[i]) {
                            if (s.isOccupiedByTower()){
                                monitorSurroundingsOf(s);
                            }
                            else if (s.isOccupiedByEnemy()){
                                gameMap.moveWhateverIsIn(s);
                                //the condition below is checked for reducing the health of the user's castle when an enemy reaches the castle door
                                if(s.nextSector.isCastleDoor && s.nextSector.isOccupiedByEnemy()){
                                    for(Military enemy: s.nextSector.occupant){
                                        player.castleHealth --;
                                        s.nextSector.occupant.remove(enemy);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 100);


    }

    public void monitorSurroundingsOf(Map.Sector s){
        Tower tower = (Tower) s.occupant.get(0);
        int x = s.xCoordinate;
        int y = s.yCoordinate;
        Map.Sector ns;
        for (int radius = 1; radius <= tower.getViewRange(); radius++){
            for (int xdif = -radius; xdif <= radius; xdif++){
                int ydif = radius - Math.abs(xdif);
                ns = gameMap.sectors[x+xdif][y+ydif];
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
                ydif = -ydif;
                ns = gameMap.sectors[x+xdif][y+ydif];
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
        g.startGame();
    }


    public void refreshScreen(){

    }

}