
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
//DO NOT CHANGE, WORKING PERFECTLY (addStudentToDB)
    public void addStudentToDB(WorkerStudent student) throws IOException, SQLException {
        String query = "INSERT INTO Student_Table (Stud_ID, Stud_First_Name, Stud_Last_Name) VALUES (?, ?, ?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, student.getStuduntID());
        statement.setString(2, student.getStudentFirstName());
        statement.setString(3, student.getStudentLastName());

        statement.executeUpdate();
    }

    public void deleteStudent(int studentNumber) throws SQLException {
        String query = "DELETE FROM Students WHERE Stud_ID = ?"; //<<----------should work now

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        statement.executeUpdate();
    }

    public void deleteCourseFromDB(int courseID) throws SQLException {
        String query = "DELETE FROM Course WHERE Course_Code = ?"; //<<-------DOUBLE CHECK THAT Course_Code IS NOT ALL CAPS!!!!!!!!!!

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, courseID);
        statement.executeUpdate();
    }
//Working method (addCourseToDB), do not even try to touch
    public void addCourseToDB(WorkerCourse course) throws IOException, SQLException {
        String query = "INSERT INTO COURSE (COURSE_CODE, COURSE_DESCRIPTION) VALUES (?, ?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, course.getCourseCode());
        statement.setString(2, course.getCourseDescription());
        statement.executeUpdate();
    }

    public List<WorkerStudent> getAllStudents() throws SQLException {
        List<WorkerStudent> students = new ArrayList<>();
        String query = "SELECT * FROM Student_Subject";//<<-------DOUBLE CHECK THAT Studetn_Subject IS NOT ALL CAPS!!!!!!!!!!

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
        String query = "SELECT * FROM COURSE";//<<-------DOUBLE CHECK THAT Studetn_Subject IS NOT ALL CAPS!!!!!!!!!!
        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String courseID = result.getString("COURSE_CODE");     //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR COURSE((THIS SHOULD BE RIGHT))
            String courseDescription = result.getString("COURSE_DESCRIPTION");

            courses.add(new WorkerCourse(courseID, courseDescription));
        }
        return courses;
    }

    public List<WorkerStudent> studentsPerCourse(int courseCode) throws SQLException {
        String query = "SELECT student.* FROM STUDENT JOIN STUDENT_COURSE ON STUDENT.ID = STUDENT_COURSE.STUD_ID WHERE STUDENT_COURSE.COURSE_ID = ?"; //<--------- Ready to Test

        List<WorkerStudent> studentsOfCourse = new ArrayList<>();

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, courseCode);   // -------  Note change to course_Code (Might not need to since it takes it as the passed variable in the method)
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String studName = result.getString("courseID");
            String studLastName = result.getString("courseName");//FOR THE WHOLE METHOD DBL CHECK WHAT TABLE IT IS FETCHING STUDENTS FROM FOR CORRECT INFO TO BE INSERTED
            String studNum = result.getString("studNum");
            studentsOfCourse.add(new WorkerStudent(studName, studLastName, studNum));
        }
        return studentsOfCourse;
    }

    public List<WorkerCourse> coursesPerStudent(int studentNumber) throws SQLException {
        String query = "SELECT COURSE.* FROM COURSE JOIN STUDENT_COURSE ON COURSE.COURSE_CODE = STUDENT_COURSE.COURSE_ID WHERE STUDENT_COURSE.STUD_ID = ?"; //<---- Ready to Test

        List<WorkerCourse> coursesOfStudent = new ArrayList<>();

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String stud_ID = result.getString("stud_ID");
            String course_ID = result.getString("course_ID");
            coursesOfStudent.add(new WorkerCourse(stud_ID, course_ID));
        }

        return coursesOfStudent;
    }
    
    public void authenticationLogin(){
        //AUTHENTICATION FOR STUDENT AND ADMIN LOGIN 
    }
    //get Just Student info
    public List<WorkerStudent> getStudentInfo() throws SQLException {
        List<WorkerStudent> students = new ArrayList<>();
        String query = "SELECT * FROM STUDENT_TABLE";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String studentNumber = result.getString("Stud_ID");   //<<------------SHOULD BE RIGHT
            String name = result.getString("STUD_FIRST_NAME");
            String course = result.getString("STUD_LAST_NAME");

            students.add(new WorkerStudent(studentNumber, name, course));
        }
        return students;
    }
}
