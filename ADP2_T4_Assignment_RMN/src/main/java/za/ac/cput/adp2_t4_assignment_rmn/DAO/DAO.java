/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.cput.adp2_t4_assignment_rmn.DAO;

/**
 *
 * @author nicho
 */
public class DAO {
    
    public Connection connectToDB(){
	Connection conn = null;
	try {
		String url = ""; // add DB url
		String username = ""; // add DB username
		String password = ""; // add DB password

		conn = DriverManager.getConnection(url, username, password);
		if(conn != null){
			System.out.println("Connected to DB");
		} catch(SQLException ex) {
			ex.printStackTrace();
                        System.out.println("Failed to connect to the database.");
		}
	} return conn;
    }

	//Student Functions

    public boolean addStudentToDB(WorkerStudent student){
    	boolean studentIsAdded = false;
	String query = "INSERT INTO Students (studentNumber, name, course)"; // enter correct details to match DB & getters and setters
	try{
	PreparedStatement statement = connectToDB.prepareStatement(query);
		ps.setInt(1, student.getStudentNumber());
		ps.setString(2, student.getName());
		ps.setString(3, student.getCourse());

		studentIsAdded = ps.executeUpdate() == 1; // Execute the query. If it returns 1, the insertion was successful
	}catch(SQLException e){
		e.printStackTrace();
	}
	return isAdded;
    }

    public boolean addCourseToDB(){
    	boolean courseIsAdded = false;
	String query = "INSERT INTO Course (CourseID, CourseName, CourseDescription)";
	try{
		PreparedStatement statement = connectToDB.preparedStatement(query);
		statement.setInt(1, courseID);
		statement.setInt(2, courseName);
		statement.setInt(3, courseDescription);

		isAdded = statement.executeUpdate() == 1;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return isAdded;
    }

    public List<WorkerStudent> getAllStudents() {
    List<WorkerStudent> students = new ArrayList<>();
    String query = "SELECT * FROM Students";
    try {
        Statement stmt = connectToDB.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            int studentNumber = rs.getInt("Student_Number");
            String name = rs.getString("Name");
            String course = rs.getString("Course");
            
            WorkerStudent student = new WorkerStudent(studentNumber, name, course);
            students.add(student);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return students;
}
	//Admin Functions
}
