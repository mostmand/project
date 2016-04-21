/**
 * Created by akhavan on 2016-04-18.
 */
public abstract class Military{

    private Integer health;

    public Integer getHealth() {
        return health;
    }
    public void setHealth(Integer health) {
        this.health = health;
    }


    private Integer price;

    public Integer getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }


    private Map gameMap;

    public Map getGameMap() {
        return gameMap;
    }
    public void setGameMap(Map gameMap) {
        this.gameMap = gameMap;
    }

    private Map.Sector position;

    public Map.Sector getSector() {
        return position;
    }
    public void setSector(Map.Sector sector) {
        this.position = sector;
    }

    public abstract void monitorSurroundings();

    public abstract void attack();

}
