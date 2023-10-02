package za.ac.cput.adp2_t4_assignment_rmn.WorkerClass;

/**
 *
 * @author nicho
 */
public class WorkerCourse {
    private String courseName;
    private String CourseCode;

    public WorkerCourse(String courseName, String CourseCode) {
        this.courseName = courseName;
        this.CourseCode = CourseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
                "courseName='" + courseName + '\'' +
                ", CourseCode='" + CourseCode + '\'' +
                '}';
    }
}
