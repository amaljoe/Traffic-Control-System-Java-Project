import java.awt.Color;

import java.awt.*;

public class Car {
    static final int carWidth = 40;
    static final int carHeight = 20;
    static final int safeDistance = 15;

    Color color;
    int x;
    int track;
    int y;
    int[] markers;
    boolean moving = true;

    Car(Color color, int x, int track, int[] markers) {
        this.color = color;
        this.x = x;
        this.track = track;
        this.markers = markers;
        y = track * 30;
    }

    private void goTo(int marker) {
        marker -= safeDistance;
        if (x + carWidth + 3 <= marker) {
            move(3);
        } else if (x + carWidth + 2 <= marker) {
            move(2);
        } else if (x + carWidth + 1 <= marker) {
            move(1);
        } else {
            move(0);
            stop();
            System.out.println("error: car going while touching the marker" + marker);
        }
    }

    public void start() {
        moving = true;
    }

    public void stop() {
        moving = false;
        markers[track] = x;
    }

    public void drive() {
        if(!moving) {
            return;
        }

        if (x + carWidth < markers[track]) {
            goTo(markers[track]);
        } else if (x + carWidth == markers[track]) {
            stop();
        } else if (x + carWidth > markers[track]) {
            goTo(700);
        }
    }

    private void move(int distance) {
        x += distance;
        if (x > 640) {
            x = 0 - carWidth;
        }
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x + 0, y + carHeight / 2, carWidth / 5, carHeight / 2);
        g.fillRect(x + carWidth / 5, y, 3 * carWidth / 5, carHeight);
        g.fillRect(x + 4 * carWidth / 5, y + carHeight / 2, carWidth / 5, carHeight / 2);
        g.setColor(Color.black);
        g.fillOval(x + carWidth / 8, y + 3 * carHeight / 4, carHeight / 2, carHeight / 2);
        g.fillOval(x + 5 * carWidth / 7, y + 3 * carHeight / 4, carHeight / 2, carHeight / 2);
    }
}