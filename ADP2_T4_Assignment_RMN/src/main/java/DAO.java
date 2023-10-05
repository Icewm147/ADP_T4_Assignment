
import java.io.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author 222749180
 */
public class DAO {

//    public DAO(){
//        connectToDB(); <---------------------------CHECK IF IT'S NEEDED
//    }
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

    public void addStudentToDB(WorkerStudent student) throws IOException, SQLException { //Add outputStream.writeObject(student);
        String query = "INSERT INTO Students (studentNumber, name, course) VALUES (?, ?, ?)"; // enter correct details to match DB & getters and setters
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, student.getStudentNumber());
        statement.setString(2, student.getStudentName());
        statement.setString(3, student.getStudentLastName());

        statement.executeUpdate();
//
//            if (studentIsAdded) {
//                Socket socket = new Socket("127.0.0.1", 12345);
//                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//                outputStream.writeObject(student);
//                outputStream.flush();
//                socket.close();
//            }
    }

    public void deleteStudent(int studentNumber) throws SQLException {
        String query = "DELETE FROM Students WHERE Stud_Number = ?";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        statement.executeUpdate();
    }

    public void deleteCourseFromDB(int courseID) throws SQLException {
        String query = "DELETE FROM Course WHERE CourseID = ?";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, courseID);
        statement.executeUpdate();
    }

    public void addCourseToDB(WorkerCourse course) throws IOException, SQLException {
        String query = "INSERT INTO COURSE (COURSE_CODE, COURSE_DESCRIPTION) VALUES (?, ?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        //statement.setString(1, courseCode);
        //statement.setString(2, courseName);                  //<<------------I think this will come from the GUI?
        //statement.setString(3, courseDescription);
        statement.executeUpdate();
    }

    public List<WorkerStudent> getAllStudents() throws SQLException {
        List<WorkerStudent> students = new ArrayList<>();
        String query = "SELECT * FROM Students";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String studentNumber = result.getString("Student_Number");   //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR STUDENTS
            String name = result.getString("Name");
            String course = result.getString("Course");

            //WorkerStudent student = new WorkerStudent(studentNumber, name, course); <------Delete after testing if not needed
            students.add(new WorkerStudent(studentNumber, name, course));
        }
        return students;
    }

    public List<WorkerCourse> getAllCourses() throws SQLException {
        List<WorkerCourse> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String courseID = result.getString("COURSE_CODE");     //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR COURSE((THIS OE SHOULD BE RIGHT))
            String courseDescription = result.getString("COURSE_DESCRIPTION");

            //WorkerCourse course = new WorkerCourse(courseID, courseDescription); <------Delete after testing if not needed
            courses.add(new WorkerCourse(courseID, courseDescription));
        }
        return courses;
    }

    public List<WorkerStudent> studentsPerCourse(int courseID) throws SQLException {
        String query = "SELECT student.* FROM student JOIN student_course ON student.id = student_course.student_id WHERE student_course.course_id = ?"; //<--------- query will need fixing with right table names

        List<WorkerStudent> studentsOfCourse = new ArrayList<>();

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, courseID);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String studName = result.getString("courseID");
            String studLastName = result.getString("courseName");
            String studNum = result.getString("studNum");
            studentsOfCourse.add(new WorkerStudent(studName, studLastName, studNum));
        }
        return studentsOfCourse;
    }

    public List<WorkerCourse> coursesPerStudent(int studentNumber) throws SQLException {
        String query = "SELECT course.* FROM course JOIN student_coourse ON course.id = student_course.course_id WHERE student_course.student_id = ?"; //<---- same as query@studentsPerCourse()

        List<WorkerCourse> coursesOfStudent = new ArrayList<>();

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String courseID = result.getString("courseID");
            String courseName = result.getString("courseName");
            coursesOfStudent.add(new WorkerCourse(courseID, courseName));
        }

        return coursesOfStudent;
    }
}
