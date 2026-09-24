/** Manual traversal of service-time values stored in an array. */
public final class DailyStatistics {
    public static final class Result {
        public final int totalStudents;
        public final int totalServiceTime;
        public final double averageServiceTime;
        public final int highestServiceTime;
        public final int lowestServiceTime;
        public final int longerThanTenMinutes;

        private Result(int totalStudents, int totalServiceTime,
                       double averageServiceTime, int highestServiceTime,
                       int lowestServiceTime, int longerThanTenMinutes) {
            this.totalStudents = totalStudents;
            this.totalServiceTime = totalServiceTime;
            this.averageServiceTime = averageServiceTime;
            this.highestServiceTime = highestServiceTime;
            this.lowestServiceTime = lowestServiceTime;
            this.longerThanTenMinutes = longerThanTenMinutes;
        }

        @Override
        public String toString() {
            return "Students served: " + totalStudents
                    + "\nTotal service time: " + totalServiceTime + " min"
                    + String.format("%nAverage service time: %.2f min", averageServiceTime)
                    + "\nHighest service time: " + highestServiceTime + " min"
                    + "\nLowest service time: " + lowestServiceTime + " min"
                    + "\nServices longer than 10 min: " + longerThanTenMinutes;
        }
    }

    private DailyStatistics() {
    }

    public static Result analyze(int[] serviceTimes, int count) {
        if (serviceTimes == null) {
            throw new IllegalArgumentException("Service-time array cannot be null.");
        }
        if (count < 0 || count > serviceTimes.length) {
            throw new IllegalArgumentException("Invalid number of service times.");
        }
        if (count == 0) {
            return new Result(0, 0, 0.0, 0, 0, 0);
        }

        int total = 0;
        int highest = serviceTimes[0];
        int lowest = serviceTimes[0];
        int longerThanTen = 0;

        for (int i = 0; i < count; i++) {
            int time = serviceTimes[i];
            total += time;
            if (time > highest) {
                highest = time;
            }
            if (time < lowest) {
                lowest = time;
            }
            if (time > 10) {
                longerThanTen++;
            }
        }

        double average = (double) total / count;
        return new Result(count, total, average, highest, lowest, longerThanTen);
    }
}
