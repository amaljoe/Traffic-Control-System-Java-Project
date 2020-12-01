class Control extends Thread {
    boolean move_forward;
    Road road;

    Control(Road road) {
        move_forward = true;
        this.road = road;
    }

    public void brake() {
        move_forward = false;
    }

    public void accelerate() {
        move_forward = true;
        move();
    }

    public void move() {
        while(move_forward) {
            System.out.println("Moving forward");
            road.repaint();
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
            road.x += 10;
        }
    }

    public void run() {
        move();
    }
}