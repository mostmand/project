/**
 * Created by akhavan on 2016-04-16.
 */
public abstract class Castle extends BattleForce{
    private Integer price;

    public void setPrice(int price) {
        this.price = price;
    }

    private Integer power;
    private Integer viewRange;
    private Integer attackspeed;
//    public abstract void monitorSurroundings();
//    public abstract void attack();
    Square[] surroundings;
}

class Castle1 extends Castle{
    static Integer price = 10;
    static Integer viewRange;
    static Integer attackSpeed;
    Castle1(){
        this.setPrice(price);
    }
}

class CastleThread{

}