/**
 * Clock to invoke callback in Road class at a regular interval
 */
public class Clock extends Thread {
    Road road;
    boolean running;

    Clock(Road road) {
        running = true;
        this.road = road;
    }

    public void cancel() {
        running = false;
    }

    public void run() {
        while(running) {
            road.clockTick();
            try {
                Thread.sleep(17);
            } catch(Exception e) {}
        }
    }
    
}
