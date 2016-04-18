/**
 * Created by akhavan on 2016-04-16.
 */
public class Castle extends BattleForce{
    private int price;

    public void setPrice(int price) {
        this.price = price;
    }

    private int power;
    private int viewRange;
    private int attackspeed;
//    public abstract void monitorSurroundings();
//    public abstract void attack();
}

class Castle1 extends Castle{
    static int price = 10;
    Castle1(){
        this.setPrice(price);
    }
}