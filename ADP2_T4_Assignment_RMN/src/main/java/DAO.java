
import java.io.*;
import java.util.List;
import java.sql.*;
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
        statement.setInt(1, student.getStuduntID());
        statement.setString(2, student.getStudentFirstName());
        statement.setString(3, student.getStudentLastName());

        statement.executeUpdate();
    }

    public void deleteStudent(int studentNumber) throws SQLException {
        String query = "DELETE FROM Student_Table WHERE Stud_ID = ?"; 

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        statement.executeUpdate();
    }

    public void deleteCourseFromDB(int courseID) throws SQLException {
        String query = "DELETE FROM Course WHERE Course_Code = ?"; 

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, courseID);
        statement.executeUpdate();
    }
//Working method (addCourseToDB), do not even try to touch

    public void addCourseToDB(WorkerCourse course) throws IOException, SQLException {
        String query = "INSERT INTO COURSE (COURSE_CODE, COURSE_DESCRIPTION, AVAILABLE) VALUES (?, ?,?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, course.getCourseCode());
        statement.setString(2, course.getCourseDescription());
        statement.setBoolean(3, course.isAvailable());
        statement.executeUpdate();
    }

    //add Subject to Subject_Table
    public void addSubject(WorkerSubject subject) throws SQLException {
        String query = "INSERT INTO Subject_Table(Subject_ID,Subject_Name,Course_Code) VALUES (?,?,?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, subject.getSubjectID1());
        statement.setString(2, subject.getSubjectName1());
        statement.setString(3, subject.getCourseID());
        statement.executeUpdate();
    }

    //getting all information on Subjects
    public List<WorkerSubject> getAllSubjects() throws SQLException {
        List<WorkerSubject> students = new ArrayList<>();
        String query = "SELECT * FROM Subject_Table ";//<<-------DOUBLE CHECK THAT Studetn_Subject IS NOT ALL CAPS!!!!!!!!!!

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String studentNumber = result.getString("Subject_ID");   //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR STUDENTS
            String name = result.getString("Subject_Name");
            String course = result.getString("Course_Code");
            //WorkerStudent student = new WorkerStudent(studentNumber, name, course); <------Delete after testing if not needed
            students.add(new WorkerSubject(studentNumber, name, course));
        }
        return students;
    }
//    public List<WorkerCourse> getAllCourses() throws SQLException {
//        List<WorkerCourse> courses = new ArrayList<>();
//        String query = "SELECT * FROM COURSE";//<<-------DOUBLE CHECK THAT Studetn_Subject IS NOT ALL CAPS!!!!!!!!!!
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        ResultSet result = statement.executeQuery();
//
//        while (result.next()) {
//            String courseID = result.getString("COURSE_CODE");     //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR COURSE((THIS SHOULD BE RIGHT))
//            String courseDescription = result.getString("COURSE_DESCRIPTION");
//
//            courses.add(new WorkerCourse(courseID, courseDescription));
//        }
//        return courses;
//    }
    //uncomment when implemented
//    public List<WorkerStudent> studentsPerCourse(int courseCode) throws SQLException {
//        String query = "SELECT student.* FROM STUDENT JOIN STUDENT_COURSE ON STUDENT.ID = STUDENT_COURSE.STUD_ID WHERE STUDENT_COURSE.COURSE_ID = ?"; //<--------- Ready to Test
//
//        List<WorkerStudent> studentsOfCourse = new ArrayList<>();
//
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        statement.setInt(1, courseCode);   // -------  Note change to course_Code (Might not need to since it takes it as the passed variable in the method)
//        ResultSet result = statement.executeQuery();
//        while (result.next()) {
//            String studName = result.getString("courseID");
//            String studLastName = result.getString("courseName");//FOR THE WHOLE METHOD DBL CHECK WHAT TABLE IT IS FETCHING STUDENTS FROM FOR CORRECT INFO TO BE INSERTED
//            String studNum = result.getString("studNum");
//            studentsOfCourse.add(new WorkerStudent(studName, studLastName, studNum));
//        }
//        return studentsOfCourse;
//    }
    public List<String> coursesPerStudent(int studentNumber) throws SQLException {
        String query = "SELECT COURSE.* FROM COURSE JOIN STUDENT_COURSE ON COURSE.COURSE_CODE = STUDENT_COURSE.COURSE_ID WHERE STUDENT_COURSE.STUD_ID = ?"; //<---- Ready to Test

        List<String> coursesOfStudent = new ArrayList<>();

        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setInt(1, studentNumber);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int stud_ID = result.getInt("stud_ID");
            String course_ID = result.getString("course_ID");
            coursesOfStudent.add(stud_ID, course_ID);
        }
        return coursesOfStudent;
    }

    //test
    public boolean authenticateUser(String username, String password, String userAccessType) {

        String query = "SELECT * FROM LOGIN_CREDENTIALS WHERE USERNAME=? AND PASSWORD=? AND USER_ACCESS_TYPE=?";
        try {
            PreparedStatement statement = connectToDB().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userAccessType);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }

            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    //get Just Student info
    public List<WorkerStudent> getStudentInfo() throws SQLException {
        List<WorkerStudent> students = new ArrayList<>();
        String query = "SELECT * FROM STUDENT_TABLE";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            int studentNumber = result.getInt("Stud_ID");   //<<------------SHOULD BE RIGHT
            String name = result.getString("STUD_FIRST_NAME");
            String course = result.getString("STUD_LAST_NAME");

            students.add(new WorkerStudent(studentNumber, name, course));
        }
        return students;
    }

    //get Just Course info
    public List<WorkerCourse> getCourseInfo() throws SQLException {
        List<WorkerCourse> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String courseCode = result.getString("Course_Code");   //<<------------SHOULD BE RIGHT
            String desc = result.getString("Course_Description");
            Boolean availability = result.getBoolean("Available");

            courses.add(new WorkerCourse(courseCode, desc, availability));
        }
        return courses;
    }

    public List<WorkerCourse> getAvailableCourses() throws SQLException {
        List<WorkerCourse> courses = new ArrayList<>();
        String query = "SELECT * FROM Course WHERE Available = 'true'";

        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String courseCode = result.getString("Course_Code");   //<<------------SHOULD BE RIGHT
            String desc = result.getString("Course_Description");
            Boolean availability = result.getBoolean("Available");

            courses.add(new WorkerCourse(courseCode, desc, availability));
        }
        return courses;
    }

    public List<String> getCourseCode() throws SQLException {
        List<String> coursesCode = new ArrayList<>();
        String query = "SELECT COURSE_CODE FROM COURSE WHERE Available = 'true'";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String courseCode = result.getString("Course_Code");   //<<------------SHOULD BE RIGHT
            coursesCode.add(courseCode);
        }
        return coursesCode;
    }
    
    public void enrollStudent(int StudID, String courseCode) throws SQLException{
        String query = "INSERT INTO Student_Course (Stud_ID, Course_Code) VALUES (?, ?)";
        
        PreparedStatement statement = connectToDB().prepareStatement(query);
        
        statement.setInt(1, StudID);
        statement.setString(2, courseCode);
        
        statement.executeUpdate();
    }
    
    public void deregisterStudent(int StudID, String courseCode) throws SQLException{
        String query = "DELETE FROM Student_Course WHERE Stud_ID = ? AND Course_Code = ?";
        
        PreparedStatement statement = connectToDB().prepareStatement(query);
        
        statement.setInt(1, StudID);
        statement.setString(2, courseCode);
        
        statement.executeUpdate();
    }
}
