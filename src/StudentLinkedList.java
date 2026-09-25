/**
 * A custom singly linked list for completed student service records.
 */
public final class StudentLinkedList {
    private static final class Node {
        private final Student data;
        private Node next;

        private Node(Student data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public void insertStudent(Student student) {
        insertAtEnd(student);
    }

    public void insertAtBeginning(Student student) {
        requireStudent(student);
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtEnd(Student student) {
        requireStudent(student);
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    /** Inserts at a one-based position in the range 1..size+1. */
    public void insertStudent(Student student, int position) {
        requireStudent(student);
        if (position < 1 || position > size + 1) {
            throw new IllegalArgumentException(
                    "Position must be between 1 and " + (size + 1) + ".");
        }
        if (position == 1) {
            insertAtBeginning(student);
            return;
        }
        if (position == size + 1) {
            insertAtEnd(student);
            return;
        }

        Node previous = head;
        for (int index = 1; index < position - 1; index++) {
            previous = previous.next;
        }
        Node newNode = new Node(student);
        newNode.next = previous.next;
        previous.next = newNode;
        size++;
    }

    public boolean deleteStudent(String studentNumber) {
        if (studentNumber == null || head == null) {
            return false;
        }
        if (head.data.getStudentNumber().equalsIgnoreCase(studentNumber.trim())) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getStudentNumber()
                    .equalsIgnoreCase(studentNumber.trim())) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Student searchStudent(String studentNumber) {
        if (studentNumber == null) {
            return null;
        }
        Node current = head;
        while (current != null) {
            if (current.data.getStudentNumber()
                    .equalsIgnoreCase(studentNumber.trim())) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No student service records.");
            return;
        }
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            position++;
            current = current.next;
        }
    }

    public String toDiagram() {
        if (head == null) {
            return "HEAD -> null";
        }
        StringBuilder diagram = new StringBuilder("HEAD -> ");
        Node current = head;
        while (current != null) {
            diagram.append('[')
                    .append(current.data.getStudentNumber())
                    .append(" | next]");
            if (current.next == null) {
                diagram.append(" -> null");
            } else {
                diagram.append(" -> ");
            }
            current = current.next;
        }
        return diagram.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    private static void requireStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
    }
}
