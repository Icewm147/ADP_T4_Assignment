package za.ac.cput.adp2_t4_assignment_rmn.WorkerStudent;

/**
 *
 * @author nicho
 */
public class WorkerStudent {
    private String studentName;
    private int studentNumber;

    public WorkerStudent(String studentName, int studentNumber) {
        this.studentName = studentName;
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }
}
