/**
 * Queue data structure to start the cars one by one from halt
 */
public class Queue {
    int size;
    Car[] cars;
    int front = -1;
    int rear = -1;

    Queue(int size) {
        this.size = size;
        cars = new Car[size];
    }

    public void insert(Car car) {
        int next = (rear + 1) % size;
        if (next == front) {
            return;
        }
        if (front == -1) {
            front = 0;
        }
        cars[next] = car;
        rear = next;
    }

    public Car remove() {
        if (front == -1){
            return null;
        }
        int removedIndex = front;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front++;
        }
        return cars[removedIndex];
    }
}
