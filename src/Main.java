public class Main {

    public static void main(String[] args) {

        Queue queue = new Queue(10);

        // Six students arrive
        Student student1 = new Student("2000000", "Ileka");
        Student student2 = new Student("2000001", "Namupala");
        Student student3 = new Student("2000002", "Joshua");
        Student student4 = new Student("2000003", "Tangi");
        Student student5 = new Student("2000004", "David");
        Student student6 = new Student("2000005", "Sarah");

        queue.enqueue(student1);
        queue.enqueue(student2);
        queue.enqueue(student3);
        queue.enqueue(student4);
        queue.enqueue(student5);
        queue.enqueue(student6);

        System.out.println("After six students arrive:");
        queue.displayQueue();

        // Three students are served
        System.out.println("\nStudents being served:");

        System.out.println("Served: " + queue.dequeue());
        System.out.println("Served: " + queue.dequeue());
        System.out.println("Served: " + queue.dequeue());

        // Display remaining students
        System.out.println("\nStudents remaining in the queue:");
        queue.displayQueue();

        // Peek at the next student
        System.out.println("\nNext student to be served:");
        System.out.println(queue.peek());
    }
}