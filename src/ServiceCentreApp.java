import java.util.Scanner;

/** Interactive Part D program that integrates the queue, list, array, and sorts. */
public final class ServiceCentreApp {
    private static final int MAX_STUDENTS = 1000;
    private static final int[] REQUIRED_SORT_VALUES = {
        17, 5, 23, 8, 14, 3, 11, 20, 6, 9
    };

    private final Scanner input = new Scanner(System.in);
    private final StudentQueue waitingQueue = new StudentQueue(100);
    private final StudentLinkedList serviceRecords = new StudentLinkedList();
    private final int[] completedServiceTimes = new int[MAX_STUDENTS];
    private int completedCount;

    public static void main(String[] args) {
        new ServiceCentreApp().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("Welcome to the NUST Campus Service Centre Simulation.");
        while (running) {
            printMenu();
            int option = readInt("Select option: ");
            try {
                switch (option) {
                    case 1:
                        addToQueue();
                        break;
                    case 2:
                        serveNextStudent();
                        break;
                    case 3:
                        waitingQueue.displayQueue();
                        break;
                    case 4:
                        addServiceRecord();
                        break;
                    case 5:
                        serviceRecords.displayStudents();
                        break;
                    case 6:
                        searchRecord();
                        break;
                    case 7:
                        removeRecord();
                        break;
                    case 8:
                        displayStatistics();
                        break;
                    case 9:
                        sortServiceTimes();
                        break;
                    case 10:
                        SortingExperiment.runAll(System.out);
                        break;
                    case 11:
                        running = false;
                        System.out.println("Simulation closed.");
                        break;
                    default:
                        System.out.println("Choose an option from 1 to 11.");
                }
            } catch (IllegalArgumentException error) {
                System.out.println("Input error: " + error.getMessage());
            } catch (IllegalStateException error) {
                System.out.println("Operation unavailable: " + error.getMessage());
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("========================================");
        System.out.println("CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student (remove from queue)");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record (Linked List)");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
    }

    private void addToQueue() {
        Student student = readStudent();
        waitingQueue.enqueue(student);
        System.out.println("Added to waiting queue. Next student: " + waitingQueue.peek());
    }

    private void serveNextStudent() {
        Student served = waitingQueue.dequeue();
        serviceRecords.insertStudent(served);
        if (completedCount < completedServiceTimes.length) {
            completedServiceTimes[completedCount++] = served.getEstimatedServiceTime();
        }
        System.out.println("Served: " + served);
        System.out.println("The completed visit was appended to the linked-list records.");
    }

    private void addServiceRecord() {
        Student student = readStudent();
        int position = readInt(
                "Position (1 to " + (serviceRecords.size() + 1) + ", 0 for end): ");
        if (position == 0) {
            serviceRecords.insertStudent(student);
        } else {
            serviceRecords.insertStudent(student, position);
        }
        if (completedCount < completedServiceTimes.length) {
            completedServiceTimes[completedCount++] = student.getEstimatedServiceTime();
        }
        System.out.println("Service record inserted.");
    }

    private void searchRecord() {
        String studentNumber = readLine("Student number to search: ");
        Student found = serviceRecords.searchStudent(studentNumber);
        System.out.println(found == null ? "Record not found." : "Found: " + found);
    }

    private void removeRecord() {
        String studentNumber = readLine("Student number to remove: ");
        boolean deleted = serviceRecords.deleteStudent(studentNumber);
        System.out.println(deleted ? "Record removed." : "Record not found.");
    }

    private void displayStatistics() {
        DailyStatistics.Result statistics =
                DailyStatistics.analyze(completedServiceTimes, completedCount);
        System.out.println(statistics);
    }

    private void sortServiceTimes() {
        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");
        int choice = readInt("Algorithm: ");
        int[] values = SortingAlgorithms.copyArray(REQUIRED_SORT_VALUES);
        StringBuilder trace = new StringBuilder();
        SortingAlgorithms.Stats stats;

        if (choice == 1) {
            stats = SortingAlgorithms.selectionSort(values, trace);
        } else if (choice == 2) {
            stats = SortingAlgorithms.insertionSort(values, trace);
        } else if (choice == 3) {
            stats = SortingAlgorithms.mergeSort(values, trace);
        } else if (choice == 4) {
            stats = SortingAlgorithms.quickSort(values, trace);
            System.out.println("Pivot rule: final element of each partition.");
        } else {
            System.out.println("Choose an algorithm from 1 to 4.");
            return;
        }

        System.out.print(trace);
        System.out.println("Sorted: " + SortingAlgorithms.formatArray(values));
        System.out.println(stats);
    }

    private Student readStudent() {
        String number = readLine("Student number: ");
        String name = readLine("Student name: ");
        String serviceType = readLine("Service type: ");
        int serviceTime = readInt("Estimated service time (minutes): ");
        return new Student(number, name, serviceType, serviceTime);
    }

    private int readInt(String prompt) {
        while (true) {
            String value = readLine(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException error) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }
}
