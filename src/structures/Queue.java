// A first-in, first-out queue backed by linked nodes. */
public class Queue {
  private Student[] students;
    private int front;
    private int rear;
    private int size;

    //Constructor to initialize the queue with a given capacity
    public Queue(int capacity) {
        students = new Student[20];
        front = 0;
        rear = -1;
        size = 0;
    }

     // IsEmty 
    //Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }   

    //Enqueue a student to the rear of the queue
    public void enqueue(Student student) {
        if (size == students.length) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % students.length;
        students[rear] = student;
        size++;

        System.out.println("Enqueued: " + student);
    }
    
    //Dequeue a student from the front of the queue
     public Student dequeue() {

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }

        Student student = students[front];
        students[front] = null;
        front = (front + 1) % students.length;
        size--;

        return student;
    }

     // PEEK
    public Student peek() {

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }

        return students[front];
    }

      // DISPLAY QUEUE
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Students waiting in the queue:");

        for (int i = 0; i < size; i++) {
            int index = (front + i) % students.length;
            System.out.println(students[index]);
        }
    }
}