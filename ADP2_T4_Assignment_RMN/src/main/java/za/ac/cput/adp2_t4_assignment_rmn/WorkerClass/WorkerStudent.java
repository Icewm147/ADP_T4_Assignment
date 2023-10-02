package za.ac.cput.adp2_t4_assignment_rmn.WorkerClass;

public class WorkerStudent {

    private String studentName;
    private String studentLastName;
    private int studentNumber;

    public WorkerStudent(String studentName, String studentLastName, int studentNumber) {
        this.studentName = studentName;
        this.studentLastName = studentLastName;
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "WorkerStudent{" +
                "studentName='" + studentName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }
}
