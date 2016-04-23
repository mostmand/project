import java.util.Timer;

/**
 * Created by akhavan on 2016-04-17.
 */
public abstract class Enemy extends Military{
    Timer reloadtime = new Timer();

    private Integer cost;
    private Integer speed;
    private Integer health;

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
