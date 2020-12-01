public class Queue {
    int size;
    int[] cars;
    int front = -1;
    int rear = -1;

    Queue(int size) {
        this.size = size;
        cars = new int[size];
    }

    public void insert(int car) {
        int next = (rear + 1) % size;
        if (next == front) {
            System.out.println("Error: queue is full");
            return;
        }
        if (front == -1) {
            front = 0;
        }
        cars[next] = car;
        rear = next;
    }

    public int remove() {
        if (front == -1){
            System.out.println("Error: queue is empty");
            return -1;
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
     * Expected Output: 1  2  3  error
     */
    public static void main(String[] args) {
        Queue q = new Queue(5);
        q.insert(1);
        q.insert(2);
        q.insert(3);
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
    }
}
