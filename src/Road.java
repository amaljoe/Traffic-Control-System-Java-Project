import java.awt.*;

/**
 * Road displays the road and controls the car
 */
public class Road extends Canvas {
    private static final long serialVersionUID = 1L;

    Clock clock;
    String status = "Red";
    Car car1;
    Car car2;

    Road() {
        setSize(640, 150);
        setBackground(Color.gray.brighter());
        car1 = new Car(Color.red, 0, 30);
        car2 = new Car(Color.blue, 40, 90);
        clock = new Clock(this);
        clock.start();
    }

    public void controlCar(String status) {
        this.status = status;
    }

    public void clockTick() {
        switch(status) {
            case "Green":
                greenLight();
                break;
            case "Yellow":
                redLight();
                break;
            case "Red":
                redLight();
                break;
        }
        repaint();
    }

    public void greenLight() {
        car1.greenLight();
        car2.greenLight();;
    }

    public void redLight() {
        car1.redLight();
        car2.redLight();
    }

    @Override
    public void paint(Graphics g) {
        car1.paint(g);
        car2.paint(g);
    }


    public void dispose() {
        clock.cancel();
    }
}