
import java.io.Serializable;


public class WorkerStudent implements Serializable{

    private String studuntID;
    private String studentFirstName;
    private String studentLastName;

    public WorkerStudent() {
    }

    public WorkerStudent(String studuntID, String studentFirstName, String studentLastName) {
        this.studuntID = studuntID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public String getStuduntID() {
        return studuntID;
    }

    public void setStuduntID(String studuntID) {
        this.studuntID = studuntID;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    @Override
    public String toString() {
        return "WorkerStudent{" + "studuntID=" + studuntID + ", studentFirstName=" + studentFirstName + ", studentLastName=" + studentLastName + '}';
    }
    
  }
