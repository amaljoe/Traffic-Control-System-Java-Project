/**
 * Queue data structure to start the cars one by one
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

    
    /**
     * Test code for queue
     * Change all occurences of Car datatype to int before testing
     * Expected Output: 1  2  3  error
     */
    public static void main(String[] args) {
        Queue q = new Queue(5);
        // q.insert(1);
        // q.insert(2);
        // q.insert(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
