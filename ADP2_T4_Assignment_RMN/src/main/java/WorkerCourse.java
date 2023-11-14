
import java.io.Serializable;

/**
 *
 * @author Nicholas van der Nest (222749180)
 */

public class WorkerCourse implements Serializable{
    
    private String CourseCode;
    private String courseDescription;
    private boolean available;

    public WorkerCourse() {
    }

    public WorkerCourse(String CourseCode, String courseDescription, boolean available) {
        this.CourseCode = CourseCode;
        this.courseDescription = courseDescription;
        this.available = available;
    }

    public WorkerCourse(String CourseCode, String courseDescription) {
        this.CourseCode = CourseCode;
        this.courseDescription = courseDescription;
    }

    public WorkerCourse(String CourseCode) {
        this.CourseCode = CourseCode;
    }
    

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return CourseCode + " " + courseDescription + " " + available + "\n";
    }

    
   
    

    
}
