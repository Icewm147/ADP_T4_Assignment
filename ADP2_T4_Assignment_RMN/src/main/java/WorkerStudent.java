
import java.io.Serializable;


public class WorkerStudent implements Serializable{

    private int studuntID;
    private String studentFirstName;
    private String studentLastName;
    private String courseCode;

    public WorkerStudent() {
    }

    public WorkerStudent(int studuntID, String studentFirstName, String studentLastName) {
        this.studuntID = studuntID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public WorkerStudent(int studuntID, String courseCode) {
        this.studuntID = studuntID;
        this.courseCode = courseCode;
    }

    public WorkerStudent(int studuntID, String studentFirstName, String studentLastName, String courseCode) {
        this.studuntID = studuntID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.courseCode = courseCode;
    }
    
    
    

    public int getStuduntID() {
        return studuntID;
    }

    public void setStuduntID(int studuntID) {
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
        return studuntID +" " + " " + studentFirstName + " " +studentLastName +"\n"  ;
    }

    
  }
