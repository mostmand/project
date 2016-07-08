package Logic;

/**
 * Created by akhavan on 2016-07-08.
 */
public class GameTime{

    long elapsedTime = 0;
    long startedTime = 0;

    boolean running = false;

    public void startTime(){
        if(running)
            return;
        running = true;
        startedTime = System.currentTimeMillis();
    }

    public void pauseTime(){
        if (!running)
            return;
        running = false;
        elapsedTime += System.currentTimeMillis() - startedTime;
    }

    public void toggle(){
        if (running)
            pauseTime();
        else
            startTime();
    }

    public long getTime(){
        if (running)
            return elapsedTime + System.currentTimeMillis() - startedTime;
        return elapsedTime;
    }

    public void resetTime(){
        elapsedTime = 0;
        running = false;
    }

}
