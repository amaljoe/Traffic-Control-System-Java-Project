import java.awt.*;

public class Road extends Canvas {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int x = 10;
    Clock clock;
    String status = "Red";

    Road() {
        setSize(640, 150);
        setBackground(Color.gray.brighter());
        clock = new Clock(this);
        clock.start();
    }

    public void controlCar(String status) {
        this.status = status;
    }

    public void clockTick() {
        switch(status) {
            case "Green":
                x += 3;
                break;
            case "Yellow":
                x += 1; 
                break;
            case "Red":
                break;
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, 60, 30, 30);
    }


    public void dispose() {
        clock.cancel();
    }
}