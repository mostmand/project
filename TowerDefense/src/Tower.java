import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-16.
 */
public abstract class Tower extends Military{
    Timer reloadtime = new Timer();

    private Integer price;
    private Integer viewRange;
    private Integer power;
    private Integer attackspeed;
    private Boolean canShoot;

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getViewRange() {
        return viewRange;
    }
    public void setViewRange(Integer viewRange) {
        this.viewRange = viewRange;
    }

    public Integer getPower() {
        return power;
    }
    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAttackspeed() {
        return attackspeed;
    }
    public void setAttackspeed(Integer attackspeed) {
        this.attackspeed = attackspeed;
    }

    public void setCanShoot(Boolean canShoot) {
        this.canShoot = canShoot;
    }

    public Boolean getCanShoot() {
        return canShoot;
    }

    public void hit(Object enemy) throws Exception{
        if (!(enemy instanceof Enemy))
            throw new Exception("Prey not an Enemy");
        if (getCanShoot()){
            ((Enemy)enemy).setHealth(((Enemy)enemy).getHealth() - this.getPower());
            setCanShoot(false);

            reloadtime.schedule(new TimerTask() {
                @Override
                public void run() {
                    setCanShoot(true);
                    reloadtime.cancel();
                }
            }, 0, getAttackspeed());
        }
    }
}

class Tower1 extends Tower{

    static Integer price = 11;
    static Integer viewRange = 7;
    static Integer power = 200;
    static Integer attackSpeed = 300;

    Tower1(){
        this.setPrice(price);
        this.setViewRange(viewRange);
        this.setPower(power);
        this.setAttackspeed(attackSpeed);
        this.setCanShoot(true);
    }
}