public class Student {
    
    private final String studentNumber;
    private final String name;

    public Student(String studentNumber, String name) {
        this.studentNumber = studentNumber;
        this.name = name;
    }

    //Get Student Name
    public String getName() {
        return name;
    }

    //Get Student Number
    public String getStudentNumber() {
        return studentNumber;
    }
    
    //Display Student Information
    @Override
    public String toString() {
        return "Student Number: " + studentNumber + ", Name: " + name;
}
