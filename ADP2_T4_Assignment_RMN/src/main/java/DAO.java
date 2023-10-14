
import java.io.*;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
        String query = "INSERT INTO COURSE (COURSE_CODE, COURSE_DESCRIPTION, AVAILABLE) VALUES (?, ?,?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, course.getCourseCode());
        statement.setString(2, course.getCourseDescription());
        statement.setBoolean(3, course.isAvailable());
        statement.executeUpdate();
    }

    //add Subject to Subject_Table
    public void addSubject(WorkerSubject subject) throws SQLException {
        List<WorkerSubject> students = new ArrayList<>();
        String query = "INSERT INTO Subject_Table(Subject_ID1,Subject_Name1,Subject_ID2,Subject_Name2, Subject_ID3,Subject_Name3,Subject_ID4,Subject_Name4,Course_Code) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, subject.getSubjectID1());
        statement.setString(2, subject.getSubjectName1());
        statement.setString(3, subject.getSubjectID2());
        statement.setString(4, subject.getSubjectName2());
        statement.setString(5, subject.getSubjectID3());
        statement.setString(6, subject.getSubjectName3());
        statement.setString(7, subject.getSubjectID4());
        statement.setString(8, subject.getSubjectName4());
        statement.setString(9, subject.getCourseID());
        statement.executeUpdate();
    }
    //getting all information on Subjects

//    public List<WorkerSubject> getAllSubjects() throws SQLException {
//        List<WorkerSubject> students = new ArrayList<>();
//        String query = "SELECT * FROM Subject_Table";//<<-------DOUBLE CHECK THAT Studetn_Subject IS NOT ALL CAPS!!!!!!!!!!
//
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        ResultSet result = statement.executeQuery();
//
//        while (result.next()) {
//            String studentNumber = result.getString("Subject_ID");   //<<------------ENTER CORRECT INFO FROM DB AND GETTERS AND SETTERS FOR STUDENTS
//            String name = result.getString("Subject_Name");
//            String course = result.getString("Course_Code");
//            //WorkerStudent student = new WorkerStudent(studentNumber, name, course); <------Delete after testing if not needed
//            students.add(new WorkerSubject(studentNumber, name, course));
//        }
//        return students;
//    }
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

//    public void authenticationLogin(String username, String password, String userAccessType) throws SQLException {
//        String query = "SELECT * FROM LOGIN_CREDENTIALS WHERE USERNAME=? PASSWORD=? USER_ACCESS_TYPE=?";
//
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//
//        statement.setString(1, username);
//        statement.setString(2, password);
//        statement.setString(3, userAccessType);
//
//        ResultSet result = statement.executeQuery();
//
////        if(userAccessType.equals("Admin")){
////            System.out.println("Admin Login succesfull");
////        } else if (userAccessType.equals("Student")){
////            System.out.println("Student lLogin Successful");
////        } else {
////            System.out.println("Login Failure");
////            JOptionPane.showMessageDialog(null, "invalid type of user or login failure");
////            System.out.println("Faield at authenticateLogin()");
////        }
//    }
    //change this one to add user access type , change the table name also
//    public void authenticateAdmin(String username, String password) throws SQLException{
//        String query = "SELECT * FROM ADMIN_LOGIN WHERE USERNAME = ? AND PASSWORD = ?"; //---table name
//        
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        statement.setString(1, username);
//        statement.setString(1, password);
//        ResultSet result = statement.executeQuery();
//        if (!result.next()){
//            JOptionPane.showMessageDialog(null, "Admin login failed!");
//        }
//    }
//    //delete this method
//    public void authenticateStudent(String username, String password) throws SQLException{
//        String query = "SELECT * FROM STUDENT_LOGIN WHERE USERNAME = ? AND PASSWORD = ?";
//        
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        statement.setString(1, username);
//        statement.setString(1, password);
//        ResultSet result = statement.executeQuery();
//        if (!result.next()){
//            JOptionPane.showMessageDialog(null, "Student login failed!");
//        }
//    }
//    //dont need this cuz we got one table for all logins
//    public void authenticateAdmin(String username, String password) throws SQLException{
//        String query = "SELECT * FROM ADMIN_LOGIN WHERE USERNAME = ? AND PASSWORD = ?";
//        
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        statement.setString(1, username);
//        statement.setString(1, password);
//        ResultSet result = statement.executeQuery();
//        if (!result.next()){
//            JOptionPane.showMessageDialog(null, "Admin login failed!");
//        }
//    }
//    //dont need this cuz we got one table for all logins
//    public void authenticateStudent(String username, String password) throws SQLException{
//        String query = "SELECT * FROM STUDENT_LOGIN WHERE USERNAME = ? AND PASSWORD = ?";
//        
//        PreparedStatement statement = connectToDB().prepareStatement(query);
//        statement.setString(1, username);
//        statement.setString(1, password);
//        ResultSet result = statement.executeQuery();
//        if (!result.next()){
//            JOptionPane.showMessageDialog(null, "Student login failed!");
//        }
//    }
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
}
