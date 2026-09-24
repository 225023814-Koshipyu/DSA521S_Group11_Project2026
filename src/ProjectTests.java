/** Lightweight tests runnable without JUnit. */
public final class ProjectTests {
    private static int checks;

    private ProjectTests() {
    }

    public static void main(String[] args) {
        testQueue();
        testLinkedList();
        testPostfix();
        testStatistics();
        testSorts();
        testExperiment();
        System.out.println("ALL TESTS PASSED (" + checks + " checks)");
    }

    private static void testQueue() {
        Student first = student("1", 4);
        Student second = student("2", 5);
        StudentQueue queue = new StudentQueue(3);
        check(queue.isEmpty(), "New queue should be empty");
        queue.enqueue(first);
        queue.enqueue(second);
        check(queue.peek() == first, "peek should return first arrival");
        check(queue.dequeue() == first, "dequeue should be FIFO");
        check(queue.dequeue() == second, "second arrival should be served second");
        check(queue.isEmpty(), "Queue should be empty after two removals");
    }

    private static void testLinkedList() {
        StudentLinkedList list = new StudentLinkedList();
        list.insertAtEnd(student("2", 5));
        list.insertAtBeginning(student("1", 4));
        list.insertStudent(student("3", 6), 3);
        check(list.size() == 3, "List should contain three records");
        check(list.searchStudent("2") != null, "Search should find middle node");
        check(list.deleteStudent("2"), "Delete should unlink matching node");
        check(list.searchStudent("2") == null, "Deleted record should be absent");
        check(list.size() == 2, "List size should decrease after deletion");
    }

    private static void testPostfix() {
        double result = DoubleStack.evaluatePostfix("5 3 + 2 *", null);
        check(result == 16.0, "Postfix example should equal 16");
        check(DoubleStack.evaluatePostfix("20 5 / 3 -", null) == 1.0,
                "Division and subtraction should preserve operand order");
    }

    private static void testStatistics() {
        DailyStatistics.Result result =
                DailyStatistics.analyze(new int[] {12, 5, 8, 4, 15, 7}, 6);
        check(result.totalStudents == 6, "Student total");
        check(result.totalServiceTime == 51, "Service-time total");
        check(result.averageServiceTime == 8.5, "Average service time");
        check(result.highestServiceTime == 15, "Highest service time");
        check(result.lowestServiceTime == 4, "Lowest service time");
        check(result.longerThanTenMinutes == 2, "Services longer than ten");
    }

    private static void testSorts() {
        int[] original = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
        int[][] copies = {
            SortingAlgorithms.copyArray(original),
            SortingAlgorithms.copyArray(original),
            SortingAlgorithms.copyArray(original),
            SortingAlgorithms.copyArray(original)
        };
        SortingAlgorithms.selectionSort(copies[0]);
        SortingAlgorithms.insertionSort(copies[1]);
        SortingAlgorithms.mergeSort(copies[2]);
        SortingAlgorithms.quickSort(copies[3]);
        for (int i = 0; i < copies.length; i++) {
            check(SortingAlgorithms.isSorted(copies[i]),
                    "Sorting algorithm " + i + " should sort ascending");
        }
    }

    private static void testExperiment() {
        SortingExperiment.Result[] results = SortingExperiment.runAll(null);
        check(results.length == 20, "Experiment should create 20 result rows");
        for (int i = 0; i < results.length; i++) {
            check(results[i].comparisons >= 0, "Comparison count should be non-negative");
            check(results[i].executionTimeNs >= 0, "Execution time should be non-negative");
        }
    }

    private static Student student(String number, int minutes) {
        return new Student(number, "Student " + number, "Test", minutes);
    }

    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
