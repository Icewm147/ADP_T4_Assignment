
import java.io.Serializable;

/**
 *
 * @author Nicholas van der Nest (222749180)
 */

public class WorkerCourse implements Serializable{
    
    private String CourseCode;
    private String courseDescription;

    public WorkerCourse() {
    }

    public WorkerCourse(String CourseCode, String courseDescription) {
        this.CourseCode = CourseCode;
        this.courseDescription = courseDescription;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public String toString() {
        return "WorkerCourse{" + "CourseCode=" + CourseCode + ", courseDescription=" + courseDescription + '}';
    }
    

    
}
