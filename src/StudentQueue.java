/**
 * A fixed-capacity circular-array queue. No built-in Queue class is used.
 */
public final class StudentQueue {
    private final Student[] items;
    private int front;
    private int rear;
    private int size;

    public StudentQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be positive.");
        }
        items = new Student[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        if (size == items.length) {
            throw new IllegalStateException("Waiting queue is full.");
        }
        items[rear] = student;
        rear = (rear + 1) % items.length;
        size++;
    }

    public Student dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Waiting queue is empty.");
        }
        Student student = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        return student;
    }

    public Student peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Waiting queue is empty.");
        }
        return items[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    public void displayQueue() {
        System.out.println(toDiagram());
    }

    public String toDiagram() {
        if (isEmpty()) {
            return "FRONT -> [empty] <- REAR";
        }

        StringBuilder diagram = new StringBuilder("FRONT -> ");
        for (int i = 0; i < size; i++) {
            Student student = items[(front + i) % items.length];
            diagram.append('[')
                    .append(student.getStudentNumber())
                    .append(": ")
                    .append(student.getName())
                    .append(']');
            if (i < size - 1) {
                diagram.append(" -> ");
            }
        }
        diagram.append(" <- REAR");
        return diagram.toString();
    }
}
