/**
 * Immutable student data used by the queue and linked-list implementations.
 */
public final class Student {
    private final String studentNumber;
    private final String name;
    private final String serviceType;
    private final int estimatedServiceTime;

    public Student(String studentNumber, String name, String serviceType,
                   int estimatedServiceTime) {
        if (studentNumber == null || studentNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Student number is required.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name is required.");
        }
        if (serviceType == null || serviceType.trim().isEmpty()) {
            throw new IllegalArgumentException("Service type is required.");
        }
        if (estimatedServiceTime <= 0) {
            throw new IllegalArgumentException("Service time must be positive.");
        }

        this.studentNumber = studentNumber.trim();
        this.name = name.trim();
        this.serviceType = serviceType.trim();
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getEstimatedServiceTime() {
        return estimatedServiceTime;
    }

    @Override
    public String toString() {
        return studentNumber + " | " + name + " | " + serviceType
                + " | " + estimatedServiceTime + " min";
    }
}
