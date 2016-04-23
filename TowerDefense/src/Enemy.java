import java.util.Timer;

/**
 * Created by akhavan on 2016-04-17.
 */
public abstract class Enemy extends Military{
    Timer reloadtime = new Timer();

    private Integer price;
    private Integer speed;
    private Integer health;

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
        this.setPrice(cost);
        this.setSpeed(speed);
        this.setHealth(initialHealth);
    }
}
