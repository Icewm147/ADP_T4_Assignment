
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 222749180
 */
public class DAO {

    public Connection connectToDB() {
        Connection con = null;
        try {
            con = DBConnection.derbyConnection();
            if (con != null) {
                System.out.println("Connected to DB");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
        return con;
    }

    public boolean addStudentToDB(WorkerStudent student) {
        boolean studentIsAdded = false;
        String query = "INSERT INTO Students (studentNumber, name, course)"; // enter correct details to match DB & getters and setters
        try {
            PreparedStatement statement = connectToDB().prepareStatement(query);
            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getStudentName());
            statement.setString(3, student.getStudentLastName());

            studentIsAdded = statement.executeUpdate() == 1; // Execute the query. If it returns 1, the insertion was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentIsAdded;
    }

    public boolean deleteCourseFromDB(int courseID) {
        boolean courseDeleted = false;
        String query = "DELETE FROM Course WHERE CourseID = ?";

        try {
            PreparedStatement statement = connectToDB().prepareStatement(query);
            statement.setInt(1, courseID);

            courseDeleted = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseDeleted;
    }

    public boolean addCourseToDB() {
        boolean courseIsAdded = false;
        String query = "INSERT INTO COURSE (COURSE_CODE, COURSE_DESCRIPTION) VALUES (?, ?)";
        try {
            PreparedStatement statement = connectToDB().prepareStatement(query);
            //statement.setString(1, courseCode);
            //statement.setString(2, courseName);                  //<<------------I think this will come from the GUI?
            //statement.setString(3, courseDescription);

            courseIsAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseIsAdded;
    }

    public List<WorkerStudent> getAllStudents() {
        List<WorkerStudent> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try {
            Statement statement = connectToDB().createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String studentNumber = result.getString("Student_Number");   //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR STUDENTS
                String name = result.getString("Name");
                String course = result.getString("Course");

                WorkerStudent student = new WorkerStudent(studentNumber, name, course);
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<WorkerCourse> getAllCourses() {
        List<WorkerCourse> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try {
            Statement statement = connectToDB().createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                String courseID = result.getString("COURSE_CODE");     //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR COURSE((THIS OE SHOULD BE RIGHT))
                String courseDescription = result.getString("COURSE_DESCRIPTION");

                WorkerCourse course = new WorkerCourse(courseID, courseDescription);
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    //Delete Student Method
    //Enroll student into course Method
    //Add student to course
    //CRUD operations that are missing
}
