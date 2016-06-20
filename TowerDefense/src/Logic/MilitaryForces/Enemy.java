package Logic.MilitaryForces;

import Logic.Map.Map;
import Logic.Map.Path;
import Logic.Map.Sector;

/**
 * Created by akhavan on 2016-04-17.
 */

public abstract class Enemy extends Military {

    /**
     * Characteristics of an enemy
     */
    private MilitaryType type;
    private Integer cost;
    private Integer speed;
    private Integer health;

    public MilitaryType getType() {
        return type;
    }

    public void setType(MilitaryType type) {
        this.type = type;
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

    /**
     * Constructor of Enemy
     * Makes an enemy puts it at the beginning of the path
     * @param gameMap the map that it is in
     * @param path that this enemy is going to be put in
     *             used only to get the corresponding coordinates
     */
    Enemy(Map gameMap, Path path){
        super(gameMap, path.getBeginning());
        path.addToPath(this);
    }

    /**
     * Constructor of Enemy
     * Makes an enemy puts it in the path but not at the beginning
     * the given coordinates must be part of the path
     * @param gameMap the map that it is in
     * @param path that this enemy is going to be put in
     * @param xCoordinate of the enemy
     * @param yCoordinate of the map
     */
    Enemy(Map gameMap, Path path, int xCoordinate, int yCoordinate){
        super(gameMap, xCoordinate, yCoordinate);
        path.addToPath(this);
    }

    private long timeOfLastMovement = 0;

    private boolean canMove(){
        return System.currentTimeMillis() >= timeOfLastMovement + this.getSpeed();
    }

    /**
     *   Moves the Enemy one Sector forward in the Path that it is in.
     *   Checks if the enemy is able to move first.
     *   Resets the value of timeOfLastMovement to current Time.
     */

    public void move(){
        if (!canMove())
            return;
        Sector sector;
        if (this.xCoordinate == null || this.yCoordinate == null)
            return;
        sector = this.gameMap.getSector(this.xCoordinate, this.yCoordinate);
        sector.occupant.remove(this);
        if (sector.getNextSector() == null){
            //damage user...
        }
        if (sector.getNextSector() != null)
            sector.getNextSector().occupant.add(this);
        this.setSector(sector.getNextSector());
        timeOfLastMovement = System.currentTimeMillis();
    }

    /**
     * Calculates the distance that the enemy has
     * in its corresponding path to the castle
     * @return the distance to the castle
     */
    public int distanceToCastle(){
        Sector sector = this.gameMap.getSector(this.xCoordinate, this.yCoordinate);
        return sector.distanceToCastle();
    }

    /**
     * Effects that are activated after an attack has took place.
     * @param attacker the tower that was responsible for the attack
     */
    public abstract void activateAfterAttackEffects(Tower attacker);
}
