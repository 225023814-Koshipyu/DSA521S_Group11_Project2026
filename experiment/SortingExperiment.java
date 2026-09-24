import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

/** Reproducible comparison of the four required sorting algorithms. */
public final class SortingExperiment {
    public static final class Result {
        public final String inputType;
        public final String algorithm;
        public final int inputSize;
        public final long comparisons;
        public final long executionTimeNs;

        private Result(String inputType, String algorithm, int inputSize,
                       long comparisons, long executionTimeNs) {
            this.inputType = inputType;
            this.algorithm = algorithm;
            this.inputSize = inputSize;
            this.comparisons = comparisons;
            this.executionTimeNs = executionTimeNs;
        }
    }

    private static final int[] SIZES = {20, 50, 100, 500};
    private static final String[] ALGORITHMS = {
        "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"
    };
    private static final long RANDOM_SEED = 5212026L;

    private SortingExperiment() {
    }

    public static Result[] runAll(PrintStream output) {
        Random random = new Random(RANDOM_SEED);
        Result[] results = new Result[20];
        int resultIndex = 0;
        int[] originalOneHundred = null;

        for (int sizeIndex = 0; sizeIndex < SIZES.length; sizeIndex++) {
            int size = SIZES[sizeIndex];
            int[] original = generateValues(size, random);
            if (size == 100) {
                originalOneHundred = SortingAlgorithms.copyArray(original);
            }
            for (int algorithmIndex = 0; algorithmIndex < ALGORITHMS.length;
                 algorithmIndex++) {
                results[resultIndex++] = runOne(
                        "Random", ALGORITHMS[algorithmIndex], original);
            }
        }

        int[] almostSorted = makeAlmostSorted(originalOneHundred);
        for (int algorithmIndex = 0; algorithmIndex < ALGORITHMS.length;
             algorithmIndex++) {
            results[resultIndex++] = runOne(
                    "Almost-sorted", ALGORITHMS[algorithmIndex], almostSorted);
        }

        if (output != null) {
            printResults(results, output);
        }
        return results;
    }

    private static int[] generateValues(int size, Random random) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = 1 + random.nextInt(1000);
        }
        return values;
    }

    private static int[] makeAlmostSorted(int[] original) {
        int[] values = SortingAlgorithms.copyArray(original);
        SortingAlgorithms.selectionSort(values);
        int[] leftIndexes = {9, 24, 39, 59, 79};
        for (int i = 0; i < leftIndexes.length; i++) {
            int left = leftIndexes[i];
            int temporary = values[left];
            values[left] = values[left + 1];
            values[left + 1] = temporary;
        }
        return values;
    }

    private static Result runOne(String inputType, String algorithm, int[] original) {
        int[] working = SortingAlgorithms.copyArray(original);
        long start = System.nanoTime();
        SortingAlgorithms.Stats stats;
        if ("Selection Sort".equals(algorithm)) {
            stats = SortingAlgorithms.selectionSort(working);
        } else if ("Insertion Sort".equals(algorithm)) {
            stats = SortingAlgorithms.insertionSort(working);
        } else if ("Merge Sort".equals(algorithm)) {
            stats = SortingAlgorithms.mergeSort(working);
        } else {
            stats = SortingAlgorithms.quickSort(working);
        }
        long end = System.nanoTime();

        if (!SortingAlgorithms.isSorted(working)) {
            throw new IllegalStateException(algorithm + " failed to sort the array.");
        }
        return new Result(inputType, algorithm, original.length,
                stats.comparisons, end - start);
    }

    public static void printResults(Result[] results, PrintStream output) {
        output.printf("%-14s %-16s %6s %13s %18s%n",
                "Input", "Algorithm", "Size", "Comparisons", "Time (ns)");
        output.println("-----------------------------------------------------------------------");
        for (int i = 0; i < results.length; i++) {
            Result result = results[i];
            output.printf("%-14s %-16s %6d %13d %18d%n",
                    result.inputType, result.algorithm, result.inputSize,
                    result.comparisons, result.executionTimeNs);
        }
    }

    public static void writeCsv(Result[] results, String path) throws IOException {
        FileWriter writer = new FileWriter(path);
        try {
            writer.write("input_type,algorithm,input_size,comparisons,execution_time_ns\n");
            for (int i = 0; i < results.length; i++) {
                Result result = results[i];
                writer.write(result.inputType + "," + result.algorithm + ","
                        + result.inputSize + "," + result.comparisons + ","
                        + result.executionTimeNs + "\n");
            }
        } finally {
            writer.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Result[] results = runAll(System.out);
        if (args.length > 0) {
            writeCsv(results, args[0]);
            System.out.println("CSV written to " + args[0]);
        }
    }
}
