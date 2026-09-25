/**
 * Student implementations of the four required sorting algorithms.
 * No built-in sorting method is used.
 */
public final class SortingAlgorithms {
    public static final class Stats {
        public long comparisons;
        public long swaps;
        public long shifts;

        @Override
        public String toString() {
            return "comparisons=" + comparisons + ", swaps=" + swaps
                    + ", shifts=" + shifts;
        }
    }

    private SortingAlgorithms() {
    }

    public static Stats selectionSort(int[] values) {
        return selectionSort(values, null);
    }

    public static Stats selectionSort(int[] values, StringBuilder trace) {
        requireArray(values);
        Stats stats = new Stats();
        for (int pass = 0; pass < values.length - 1; pass++) {
            int minimumIndex = pass;
            for (int index = pass + 1; index < values.length; index++) {
                stats.comparisons++;
                if (values[index] < values[minimumIndex]) {
                    minimumIndex = index;
                }
            }
            if (minimumIndex != pass) {
                swap(values, pass, minimumIndex);
                stats.swaps++;
            }
            if (trace != null && pass < 3) {
                trace.append("Pass ").append(pass + 1).append(": ")
                        .append(formatArray(values)).append(System.lineSeparator());
            }
        }
        return stats;
    }

    public static Stats insertionSort(int[] values) {
        return insertionSort(values, null);
    }

    public static Stats insertionSort(int[] values, StringBuilder trace) {
        requireArray(values);
        Stats stats = new Stats();
        for (int pass = 1; pass < values.length; pass++) {
            int key = values[pass];
            int index = pass - 1;

            while (index >= 0) {
                stats.comparisons++;
                if (values[index] <= key) {
                    break;
                }
                values[index + 1] = values[index];
                stats.shifts++;
                index--;
            }
            values[index + 1] = key;
            if (trace != null && pass <= 3) {
                trace.append("Pass ").append(pass).append(": ")
                        .append(formatArray(values)).append(System.lineSeparator());
            }
        }
        return stats;
    }

    public static Stats mergeSort(int[] values) {
        return mergeSort(values, null);
    }

    public static Stats mergeSort(int[] values, StringBuilder trace) {
        requireArray(values);
        Stats stats = new Stats();
        if (values.length > 1) {
            int[] temporary = new int[values.length];
            mergeSort(values, temporary, 0, values.length - 1, stats, trace, 0);
        }
        return stats;
    }

    private static void mergeSort(int[] values, int[] temporary, int left, int right,
                                  Stats stats, StringBuilder trace, int depth) {
        if (left >= right) {
            if (trace != null) {
                appendIndent(trace, depth);
                trace.append("Base case: [").append(values[left]).append("]")
                        .append(System.lineSeparator());
            }
            return;
        }

        int middle = left + (right - left) / 2;
        if (trace != null) {
            appendIndent(trace, depth);
            trace.append("Divide ").append(formatRange(values, left, right))
                    .append(" into ").append(formatRange(values, left, middle))
                    .append(" and ").append(formatRange(values, middle + 1, right))
                    .append(System.lineSeparator());
        }
        mergeSort(values, temporary, left, middle, stats, trace, depth + 1);
        mergeSort(values, temporary, middle + 1, right, stats, trace, depth + 1);
        merge(values, temporary, left, middle, right, stats);
        if (trace != null) {
            appendIndent(trace, depth);
            trace.append("Merge -> ").append(formatRange(values, left, right))
                    .append(System.lineSeparator());
        }
    }

    private static void merge(int[] values, int[] temporary, int left, int middle,
                              int right, Stats stats) {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int outputIndex = left;

        while (leftIndex <= middle && rightIndex <= right) {
            stats.comparisons++;
            if (values[leftIndex] <= values[rightIndex]) {
                temporary[outputIndex++] = values[leftIndex++];
            } else {
                temporary[outputIndex++] = values[rightIndex++];
            }
        }
        while (leftIndex <= middle) {
            temporary[outputIndex++] = values[leftIndex++];
        }
        while (rightIndex <= right) {
            temporary[outputIndex++] = values[rightIndex++];
        }
        for (int index = left; index <= right; index++) {
            values[index] = temporary[index];
        }
    }

    public static Stats quickSort(int[] values) {
        return quickSort(values, null);
    }

    public static Stats quickSort(int[] values, StringBuilder trace) {
        requireArray(values);
        Stats stats = new Stats();
        int[] partitionNumber = {0};
        quickSort(values, 0, values.length - 1, stats, trace, partitionNumber);
        return stats;
    }

    private static void quickSort(int[] values, int low, int high, Stats stats,
                                  StringBuilder trace, int[] partitionNumber) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(values, low, high, stats);
        partitionNumber[0]++;
        if (trace != null && partitionNumber[0] <= 2) {
            trace.append("Stage ").append(partitionNumber[0])
                    .append(": pivot=").append(values[pivotIndex])
                    .append(", left=").append(formatRange(values, low, pivotIndex - 1))
                    .append(", right=").append(formatRange(values, pivotIndex + 1, high))
                    .append(System.lineSeparator());
        }
        quickSort(values, low, pivotIndex - 1, stats, trace, partitionNumber);
        quickSort(values, pivotIndex + 1, high, stats, trace, partitionNumber);
    }

    /** Lomuto partition with the final element selected as the pivot. */
    private static int partition(int[] values, int low, int high, Stats stats) {
        int pivot = values[high];
        int smallerBoundary = low - 1;
        for (int index = low; index < high; index++) {
            stats.comparisons++;
            if (values[index] <= pivot) {
                smallerBoundary++;
                if (smallerBoundary != index) {
                    swap(values, smallerBoundary, index);
                    stats.swaps++;
                }
            }
        }
        int pivotPosition = smallerBoundary + 1;
        if (pivotPosition != high) {
            swap(values, pivotPosition, high);
            stats.swaps++;
        }
        return pivotPosition;
    }

    public static int[] copyArray(int[] source) {
        requireArray(source);
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    public static boolean isSorted(int[] values) {
        requireArray(values);
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                return false;
            }
        }
        return true;
    }

    public static String formatArray(int[] values) {
        requireArray(values);
        return formatRange(values, 0, values.length - 1);
    }

    private static String formatRange(int[] values, int left, int right) {
        if (left > right) {
            return "[]";
        }
        StringBuilder text = new StringBuilder("[");
        for (int index = left; index <= right; index++) {
            if (index > left) {
                text.append(", ");
            }
            text.append(values[index]);
        }
        return text.append(']').toString();
    }

    private static void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }

    private static void appendIndent(StringBuilder trace, int depth) {
        for (int i = 0; i < depth; i++) {
            trace.append("  ");
        }
    }

    private static void requireArray(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Array cannot be null.");
        }
    }
}
