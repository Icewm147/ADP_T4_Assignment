/**
 *
 * @author Nicholas van der Nest (222749180)
 */

public class WorkerCourse {
    
    private String courseDescription;
    private String CourseCode;

    public WorkerCourse(String courseName, String CourseCode) {
        this.courseDescription = courseName;
        this.CourseCode = CourseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    @Override
    public String toString() {
        return "WorkerCourse{" +
                "courseName='" + courseDescription + '\'' +
                ", CourseCode='" + CourseCode + '\'' +
                '}';
    }
}
