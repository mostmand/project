import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akhavan on 2016-04-16.
 */
public abstract class Tower extends Military{
    Timer reloadTime = new Timer();

    private Integer price;
    private Integer viewRange;
    private Integer power;
    private Integer attackSpeed;
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

    public Integer getAttackSpeed() {
        return attackSpeed;
    }
    public void setAttackSpeed(Integer attackSpeed) {
        this.attackSpeed = attackSpeed;
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

            reloadTime.schedule(new TimerTask() {
                @Override
                public void run() {
                    setCanShoot(true);
//                    reloadTime.cancel();
                }
            }, 0, getAttackSpeed());
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
        this.setAttackSpeed(attackSpeed);
        this.setCanShoot(true);
    }
}