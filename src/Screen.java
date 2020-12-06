import java.awt.*;
import java.awt.event.*;

/**
 * Screen for displaying all elements
 */
public class Screen extends Frame implements ItemListener {
    private static final long serialVersionUID = 1L;

    int controller_x = 480;
    int controller_y = 100;
    int title_y = 130;
    String status = "Green";
    TrafficLight trafficLight;
    Road road;

    Screen() {
        setSize(640, 470);
        setVisible(true);
        setLayout(null);
        setTitle("Traffic Simulation");
        
        trafficLight = new TrafficLight();
        road = new Road();
        CheckboxGroup controller = new CheckboxGroup();
        Checkbox green = new Checkbox("Green", controller, true);
        Checkbox yellow = new Checkbox("Yellow", controller, false);
        Checkbox red = new Checkbox("Red", controller, false);
        
        trafficLight.setBounds(320, 40, 100, 260);
        road.setBounds(0, 310, 640, 150);
        green.setBounds(controller_x, controller_y, 100, 30);
        yellow.setBounds(controller_x, controller_y + 30, 100, 30);
        red.setBounds(controller_x, controller_y + 60, 100, 30);

        green.addItemListener(this);
        yellow.addItemListener(this);
        red.addItemListener(this);

        add(road);
        add(trafficLight);
        add(green);
        add(yellow);
        add(red);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("arial", Font.BOLD, 40));
        g.setColor(Color.black);
        g.drawString("Traffic", 90, title_y);
        g.drawString("Simulation", 50, title_y + 50);
        paintCar(g, 95, title_y + 70, 40, 20, Color.red);
        paintCar(g, 150, title_y + 70, 40, 20, Color.magenta);
    }

    public void paintCar(Graphics g, int x, int y, int carWidth, int carHeight, Color color) {
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

    @Override
    public void dispose() {
        road.dispose();
        super.dispose();
    }

    public void itemStateChanged(ItemEvent e) {
        Object item = e.getItem();
        status = item.toString();
        trafficLight.changeLight(status);
        road.controlCars(status);
    }
}
