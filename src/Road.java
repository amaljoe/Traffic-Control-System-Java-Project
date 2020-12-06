import java.awt.*;
import java.util.Random;
import java.awt.image.*;

/**
 * Road displays the road and handle the cars
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
    BufferedImage bufferImage;
    Graphics bufferGraphics;

    Road() {
        setSize(640, 150);
        setBackground(Color.gray.brighter());
        bufferImage = new BufferedImage(640, 150, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = bufferImage.getGraphics();
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
                break;
            case "Red":
                redLight();
                break;
        }
    }

    private void generateCars() {
        for(int i = 0; i < numberOfCars; i ++){
            Color color = generateRandomColor();
            Position position = new Position();
            generateRandomPosition(position);
            cars[i] = new Car(color, position.x * (Car.carWidth + Car.safeDistance * 2) , position.y, markers, carsToStart);
        }
    }

    private Color generateRandomColor() {
        Color color;
        switch (random.nextInt(6)) {
            case 0:
                color = Color.blue;
                break;
            case 1:
                color = Color.green.darker();
                break;
            case 2:
                color = Color.red.darker();
                break;
            case 3:
                color = Color.red;
                break;
            case 4:
                color = Color.red.brighter();
                break;
            case 5:
                color = Color.magenta.darker();
                break;
            default:
                color = Color.orange;
        }
        return color;
    }

    private void generateRandomPosition(Position position){
        int x = random.nextInt(10);
        int y = random.nextInt(5);
        boolean unique = false;
        while(!unique) {
            if (initialPositions[x][y] == 0) {
                // it is an unique position
                initialPositions[x][y] = 1;
                unique = true;
            } else {
                // not an unique position
                x = random.nextInt(10);
                y = random.nextInt(5);
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

    private void greenLight() {
        for(int i = 0; i < 5; i ++){
            markers[i] = 700;
        }
        startCars = true;
    }

    private void startCars() {
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

    private void redLight() {
        for(int i = 0; i < 5; i ++){
            markers[i] = crossingLinePosition;
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        bufferGraphics.clearRect(0, 0, 640, 150);
        paintBuffer();
        g.drawImage(bufferImage, 0, 0, this);
    }

    public void paintBuffer() {
        paintBackground(bufferGraphics);
        paintCrossing(bufferGraphics);
        for(int i = 0; i < numberOfCars; i ++){
            cars[i].paint(bufferGraphics);
        }
    }

    public void paintBackground(Graphics g) {
        g.setColor(Color.gray.darker());
        g.fillRect(0, 0, 640, 150);
    }

    public void paintCrossing(Graphics g) {
        g.setColor(Color.white);
        for(int i = 5; i < 150; i += 15) {
            g.fillRect(crossingLinePosition, i, 2, 5);
        }
        for(int i = 10; i < 150; i += 25) {
            g.fillRect(crossingLinePosition + 10, i, 20, 10);
        }
        for(int i = 5; i < 150; i += 15) {
            g.fillRect(crossingLinePosition + 40, i, 2, 5);
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