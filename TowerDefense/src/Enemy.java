import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-17.
 */
public abstract class Enemy extends Military{
    Timer reloadtime = new Timer();

    private Integer cost;
    private Integer speed;
    private Integer health;
    private Boolean canMove = true;

    public void startExhaustTime(){
        setCanMove(false);
        reloadtime.schedule(new TimerTask() {
            @Override
            public void run() {
                setCanMove(true);
                reloadtime.cancel();
            }
        },0, getSpeed());
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getHealth() {
        return health;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public Integer getSpeed() {
        return speed;
    }

    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    public Boolean getCanMove() {
        return canMove;
    }
}

class Enemy1 extends Enemy{
    //the values here must be changed at a later time
    static Integer cost = 1;
    static Integer speed = 300;
    static Integer initialHealth = 200;

    Enemy1(){
        this.setCost(cost);
        this.setSpeed(speed);
        this.setHealth(initialHealth);
    }
}
