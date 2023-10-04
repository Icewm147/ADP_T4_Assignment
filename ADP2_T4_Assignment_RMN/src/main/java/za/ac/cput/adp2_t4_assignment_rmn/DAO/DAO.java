/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.adp2_t4_assignment_rmn.DAO;
//
//import java.awt.List;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;

/**
 *
 * @author Nicholas van der Nest (222749180)
 */
public class DAO {
    
//    public Connection connectToDB(){
//	Connection conn = null;
//	try {
//		 con = DBConnection.derbyConnection();
//		if(conn != null){
//			System.out.println("Connected to DB");
//		} catch(SQLException ex) {
//			ex.printStackTrace();
//                        System.out.println("Failed to connect to the database.");
//		}
//	} return conn;
//    }
//
//    public boolean addStudentToDB(WorkerStudent student){
//    	boolean studentIsAdded = false;
//	String query = "INSERT INTO Students (studentNumber, name, course)"; // enter correct details to match DB & getters and setters
//	try{
//	PreparedStatement statement = connectToDB.prepareStatement(query);
//		ps.setInt(1, student.getStudentNumber());
//		ps.setString(2, student.getName());
//		ps.setString(3, student.getCourse());
//
//		studentIsAdded = ps.executeUpdate() == 1; // Execute the query. If it returns 1, the insertion was successful
//	}catch(SQLException e){
//		e.printStackTrace();
//	}
//	return isAdded;
//    }
//
//    public boolean deleteCourseFromDB(int courseID){
//    	boolean courseDeleted = false;
//        String query = "DELETE FROM Course WHERE CourseID = ?";
//
//	try {
//	  PreparedStatement statement = connectToDB.prepareStatement(query);
//	  statement.setInt(1, courseID);
//
//	  courseDeleted = statement.executeUpdate() == 1;
//	} catch (SQLException e) {
//	  e.printStackTrace();
//	}
//    }
//
//    public boolean addCourseToDB(){
//    	boolean courseIsAdded = false;
//	String query = "INSERT INTO Course (CourseID, CourseName, CourseDescription)";
//	try{
//		PreparedStatement statement = connectToDB.preparedStatement(query);
//		statement.setInt(1, courseID);
//		statement.setInt(2, courseName);
//		statement.setInt(3, courseDescription);
//
//		isAdded = statement.executeUpdate() == 1;
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return isAdded;
//    }
//
//    public List<WorkerStudent> getAllStudents() {
//    List<WorkerStudent> students = new ArrayList<>();
//    String query = "SELECT * FROM Students";
//    try {
//        Statement statement = connectToDB.createStatement();
//        ResultSet result = stmt.executeQuery(query);
//        
//        while (result.next()) {
//            int studentNumber = result.getInt("Student_Number");
//            String name = result.getString("Name");
//            String course = result.getString("Course");
//            
//            WorkerStudent student = new WorkerStudent(studentNumber, name, course);
//            students.add(student);
//        }
//        
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return students;
//    }
//
//    public List<WorkerCourse> getAllCourses() {
//    List<WorkerStudent> courses = new ArrayList<>();
//    String query = "SELECT * FROM Course";
//    try {
//        Statement statement = connectToDB.createStatement();
//        ResultSet result = stmt.executeQuery(query);
//        
//        while (result.next()) {
//            int courseID = rs.getInt("CourseNumber");
//            String courseName = rs.getString("CourseName");
//            String courseDescription = rs.getString("CourseDescription");
//            
//            WorkerCourse course = new WorkerCourse(courseID, courseName, courseDescription);
//            coursess.add(course);
//        }
//        
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return students;
//}
}
