public class Clock extends Thread {
    Road road;
    boolean running;

    Clock(Road road){
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
                Thread.sleep(100);
            } catch(Exception e) {}
        }
    }
    
}
