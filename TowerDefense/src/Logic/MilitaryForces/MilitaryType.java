package Logic.MilitaryForces;

/**
 * Created by akhavan on 2016-06-09.
 */
public enum MilitaryType {
    BASIC(11),
    FIRE(13),
    TREE(14),
    LIGHT(16),
    DARK(15);

    public int initialPrice;

    MilitaryType(int price){
        this.initialPrice = price;
    }
}
