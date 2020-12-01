import java.awt.*;
import java.util.Random;

/**
 * Road displays the road and controls the car
 */
public class Road extends Canvas {
    private static final long serialVersionUID = 1L;
    static final int crossingLinePosition = 200;
    static final int numberOfCars = 10;
    
    Queue carsToStart;
    Clock clock;
    String status = "Green";
    Car[] cars;
    Random random;
    int[] markers;
    int[][] initialPositions;
    boolean startCars = false;

    Road() {
        setSize(640, 150);
        setBackground(Color.gray.brighter());
        random = new Random();
        carsToStart = new Queue(numberOfCars);
        markers = new int[5];
        initialPositions = new int[10][5];
        for(int i = 0; i < 5; i++){
            markers[i] = 700;
        }
        for(int i = 0; i < initialPositions.length; i++){
            for(int j = 0; j < initialPositions[0].length; j++){
                initialPositions[i][j] = 0;
            }
        }
        cars = new Car[numberOfCars];
        generateCars();
        clock = new Clock(this);
        clock.start();
    }

    public void controlCars(String status) {
        this.status = status;
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
    }

    public void generateCars() {
        for(int i = 0; i < numberOfCars; i ++){
            Color color = generateRandomColor();
            Position position = new Position();
            generateRandomPosition(position);
            cars[i] = new Car(color, position.x * (Car.carWidth + Car.safeDistance * 2) , position.y, markers, carsToStart);
        }
    }

    public Color generateRandomColor() {
        Color color;
        switch (random.nextInt(6)) {
            case 0:
                color = Color.blue;
                break;
            case 1:
                color = Color.green;
                break;
            case 2:
                color = Color.yellow;
                break;
            case 3:
                color = Color.magenta;
                break;
            case 4:
                color = Color.pink;
                break;
            case 5:
                color = Color.red;
                break;
            default:
                color = Color.orange;
        }
        switch (random.nextInt(3)) {
            case 0:
                break;
            case 1:
                color = color.darker();
                break;
            case 2:
                color = color.brighter();
                break;
        }
        return color;
    }

    public void generateRandomPosition(Position position){
        int x = random.nextInt(10);
        int y = random.nextInt(5);
        boolean unique = false;
        while(!unique) {
            if (initialPositions[x][y] == 0) {
                // it is an unique position
                initialPositions[x][y] = 1;
                unique = true;
                System.out.println("Unique position");
            } else {
                // not an unique position
                x = random.nextInt(10);
                y = random.nextInt(5);
                System.out.println("Not an unique position");
            }
        }
        position.x = x;
        position.y = y;
    }

    public void clockTick() {
        startCars();
        for(int i = 0; i < numberOfCars; i ++){
            cars[i].drive();
        }
        repaint();
    }

    public void greenLight() {
        for(int i = 0; i < 5; i ++){
            markers[i] = 700;
        }
        startCars = true;
    }

    public void startCars() {
        Car car = null;
        if (startCars) {
            car = carsToStart.remove();
        }
        if (car == null) {
            startCars = false;
            return;
        }
        car.start();
    }

    public void redLight() {
        for(int i = 0; i < 5; i ++){
            markers[i] = crossingLinePosition;
        }
    }

    @Override
    public void paint(Graphics g) {
        paintCrossing(g);
        for(int i = 0; i < 10; i ++){
            cars[i].paint(g);
        }
    }

    public void paintCrossing(Graphics g) {
        g.setColor(Color.white);
        for(int i = 10; i < 150; i += 30) {
            g.fillRect(200, i, 5, 15);
        }
    }


    public void dispose() {
        clock.cancel();
    }
}

class Position {
    int x;
    int y;
}