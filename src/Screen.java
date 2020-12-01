import java.awt.*;
import java.awt.event.*;

/**
 * Screen for displaying all elements
 */
public class Screen extends Frame implements ItemListener {
    private static final long serialVersionUID = 1L;

    int controller_x = 400;
    int controller_y = 100;
    String status = "Red";
    TrafficLight trafficLight;
    Road road;

    Screen() {
        setSize(640, 480);
        setVisible(true);
        setLayout(null);

        trafficLight = new TrafficLight();
        road = new Road();
        CheckboxGroup controller = new CheckboxGroup();
        Checkbox green = new Checkbox("Green", controller, false);
        Checkbox yellow = new Checkbox("Yellow", controller, false);
        Checkbox red = new Checkbox("Red", controller, true);
        trafficLight.setBounds(150, 30, 100, 260);
        
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
    public void dispose() {
        road.dispose();
        super.dispose();
    }

    public void itemStateChanged(ItemEvent e) {
        Object item = e.getItem();
        status = item.toString();
        trafficLight.changeLight(status);
        road.controlCar(status);
    }
}
