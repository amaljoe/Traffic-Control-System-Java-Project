import java.awt.Color;

import java.awt.*;

public class Car {
    Color color;
    int x;
    int y;

    Car (Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void greenLight(){
        x += 3;
    }

    public void redLight(){
        if(x < 200) {}
        else {
            x += 3;
        }
    }

    public void paint (Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 30, 30);
    }
}