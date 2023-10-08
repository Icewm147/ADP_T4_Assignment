
import java.io.Serializable;


public class WorkerStudent implements Serializable{

    private String studuntID;
    private String studentFirstName;
    private String studentLastName;
    private String courseID;

    public WorkerStudent() {
    }

    public WorkerStudent(String studuntID, String studentFirstName, String courseID) {
        this.studuntID = studuntID;
        this.studentFirstName = studentFirstName;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
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

    
  }
