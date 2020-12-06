import java.awt.Color;
import java.awt.*;

/**
 * Car holds the logic for driving the car and drawing it on screen
 */
public class Car {
    static final int carWidth = 40;
    static final int carHeight = 20;
    static final int safeDistance = 15;

    Color color;
    Queue carsToStart;
    int x;
    int track;
    int y;
    int[] markers;
    boolean moving = true;

    Car(Color color, int x, int track, int[] markers, Queue carsToStart) {
        this.color = color;
        this.x = x;
        this.track = track;
        this.markers = markers;
        this.carsToStart = carsToStart;
        y = 3 + track * 30;
    }

    private void goTo(int marker) {
        marker -= safeDistance;
        if (x + carWidth + 1 <= marker) {
            move(1);
        } else {
            stop();
        }
    }

    public void start() {
        moving = true;
        if(markers[track] == x) {
            markers[track] = 700;
        }
    }

    public void stop() {
        move(0);
        moving = false;
        markers[track] = x;
        carsToStart.insert(this);
    }

    public void drive() {
        if(!moving) {
            return;
        }

        if (x + carWidth < markers[track]) {
            goTo(markers[track]);
        } else if (x + carWidth == markers[track]) {
            stop();
        } else {
            goTo(700);
        }
    }

    private void move(int distance) {
        x += distance;
        if (x > 640) {
            x = 400 - x;
        }
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x + 0, y + carHeight / 2, carWidth / 5, carHeight / 2);
        g.fillRect(x + carWidth / 5, y, 3 * carWidth / 5, carHeight);
        g.fillRect(x + 4 * carWidth / 5, y + carHeight / 2, carWidth / 5, carHeight / 2);
        g.setColor(Color.white);
        g.fillRoundRect(x + carWidth / 5 + 2, y + 2, 7, 7, 2, 2);
        g.fillRoundRect(x + 2 * carWidth / 5 + 6, y + 2, 7, 7, 2, 2);
        g.setColor(Color.black.brighter());
        g.fillOval(x + carWidth / 8, y + 3 * carHeight / 4, carHeight / 2, carHeight / 2);
        g.fillOval(x + 5 * carWidth / 7, y + 3 * carHeight / 4, carHeight / 2, carHeight / 2);
    }
}