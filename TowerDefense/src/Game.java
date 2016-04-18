import java.util.Timer;

/**
 * Created by akhavan on 2016-04-18.
 */
public abstract class Game{
    private User player;
    private Map gamemap;
    Timer gameflow;
    public abstract void setCastles();
    public abstract void startGame();
    public abstract void refreshScreen();

}
