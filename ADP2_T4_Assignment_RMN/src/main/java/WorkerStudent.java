
import java.io.Serializable;


public class WorkerStudent implements Serializable{

    private String studentName;
    private String studentLastName;
    private String studentNumber;

    public WorkerStudent() {
    }

    public WorkerStudent(String studentName, String studentLastName, String studentNumber) {
        this.studentName = studentName;
        this.studentLastName = studentLastName;
        this.studentNumber = studentNumber;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String toString() {
        return "WorkerStudent{" + "studentName=" + studentName + ", studentLastName=" + studentLastName + ", studentNumber=" + studentNumber + '}';
    }

}
