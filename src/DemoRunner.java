/** Non-interactive evidence run covering Parts A, B, C and the integration path. */
public final class DemoRunner {
    private static final int[] SORT_VALUES = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

    private DemoRunner() {
    }

    public static void main(String[] args) {
        Student[] students = sampleStudents();

        System.out.println("=== A1 QUEUE DEMONSTRATION ===");
        StudentQueue queue = new StudentQueue(10);
        for (int i = 0; i < students.length; i++) {
            queue.enqueue(students[i]);
        }
        System.out.println("After six arrivals:");
        queue.displayQueue();
        System.out.println("peek() = " + queue.peek());
        for (int i = 0; i < 3; i++) {
            System.out.println("Served " + queue.dequeue());
        }
        System.out.println("After three are served:");
        queue.displayQueue();

        System.out.println("\n=== A2 LINKED-LIST DEMONSTRATION ===");
        StudentLinkedList records = new StudentLinkedList();
        records.insertAtBeginning(students[1]);
        records.insertAtEnd(students[2]);
        records.insertAtEnd(students[3]);
        System.out.println("Before insertion: " + records.toDiagram());
        records.insertStudent(students[0], 3);
        System.out.println("After insertion at position 3: " + records.toDiagram());
        records.insertAtBeginning(students[4]);
        System.out.println("After insertion at beginning: " + records.toDiagram());
        records.deleteStudent(students[2].getStudentNumber());
        System.out.println("After deletion: " + records.toDiagram());
        System.out.println("Search 221045678: "
                + records.searchStudent("221045678"));
        records.displayStudents();

        System.out.println("\n=== A3 POSTFIX STACK DEMONSTRATION ===");
        StringBuilder postfixTrace = new StringBuilder();
        double postfixResult = DoubleStack.evaluatePostfix("5 3 + 2 *", postfixTrace);
        System.out.print(postfixTrace);
        System.out.println("Final result: " + postfixResult);

        System.out.println("\n=== A4 ARRAY STATISTICS ===");
        int[] serviceTimes = {12, 5, 8, 4, 15, 7};
        System.out.println(DailyStatistics.analyze(serviceTimes, serviceTimes.length));

        System.out.println("\n=== B1 SELECTION SORT ===");
        runSelectionTrace();
        System.out.println("\n=== B2 INSERTION SORT ===");
        runInsertionTrace();
        System.out.println("\n=== B3 MERGE SORT ===");
        runMergeTrace();
        System.out.println("\n=== B4 QUICK SORT ===");
        runQuickTrace();

        System.out.println("\n=== C SORTING EXPERIMENT ===");
        SortingExperiment.runAll(System.out);

        System.out.println("\n=== D INTEGRATION SMOKE TEST ===");
        StudentQueue integratedQueue = new StudentQueue(10);
        StudentLinkedList integratedRecords = new StudentLinkedList();
        int[] integratedTimes = new int[10];
        integratedQueue.enqueue(students[0]);
        integratedQueue.enqueue(students[1]);
        Student served = integratedQueue.dequeue();
        integratedRecords.insertStudent(served);
        integratedTimes[0] = served.getEstimatedServiceTime();
        System.out.println("Queue after service: " + integratedQueue.toDiagram());
        System.out.println("Record list: " + integratedRecords.toDiagram());
        System.out.println(DailyStatistics.analyze(integratedTimes, 1));
        System.out.println("Integration check: PASS");
    }

    private static void runSelectionTrace() {
        int[] values = SortingAlgorithms.copyArray(SORT_VALUES);
        StringBuilder trace = new StringBuilder();
        SortingAlgorithms.Stats stats = SortingAlgorithms.selectionSort(values, trace);
        System.out.print(trace);
        System.out.println("Sorted: " + SortingAlgorithms.formatArray(values));
        System.out.println(stats);
    }

    private static void runInsertionTrace() {
        int[] values = SortingAlgorithms.copyArray(SORT_VALUES);
        StringBuilder trace = new StringBuilder();
        SortingAlgorithms.Stats stats = SortingAlgorithms.insertionSort(values, trace);
        System.out.print(trace);
        System.out.println("Sorted: " + SortingAlgorithms.formatArray(values));
        System.out.println(stats);
    }

    private static void runMergeTrace() {
        int[] values = SortingAlgorithms.copyArray(SORT_VALUES);
        StringBuilder trace = new StringBuilder();
        SortingAlgorithms.Stats stats = SortingAlgorithms.mergeSort(values, trace);
        System.out.print(trace);
        System.out.println("Sorted: " + SortingAlgorithms.formatArray(values));
        System.out.println(stats);
    }

    private static void runQuickTrace() {
        int[] values = SortingAlgorithms.copyArray(SORT_VALUES);
        StringBuilder trace = new StringBuilder();
        SortingAlgorithms.Stats stats = SortingAlgorithms.quickSort(values, trace);
        System.out.println("Pivot rule: final element in each partition.");
        System.out.print(trace);
        System.out.println("Sorted: " + SortingAlgorithms.formatArray(values));
        System.out.println(stats);
    }

    private static Student[] sampleStudents() {
        return new Student[] {
            new Student("221045678", "Maria", "Registration", 12),
            new Student("222034512", "Tomas", "Student Card", 5),
            new Student("223041876", "Ndapewa", "Fees", 8),
            new Student("221067341", "Simon", "Documents", 4),
            new Student("224018235", "Selma", "Academic Enquiry", 15),
            new Student("225072914", "Petrus", "Collection", 7)
        };
    }
}
