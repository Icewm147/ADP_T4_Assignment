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

    public boolean addStudentToDB(WorkerStudent student){
    	boolean isAdded = false;
	String query = "INSERT INTO Students (studentNumber, name, course)"; // enter correct details to match DB & getters and setters
	try{
	PreparedStatement statement = dbConnection.prepareStatement(query);
		ps.setInt(student.getStudentNumber());
		ps.setString(student.getName());
		ps.setString(student.getCourse());

		isAdded = ps.executeUpdate() == 1; // Execute the query. If it returns 1, the insertion was successful
	}catch(SQLException e){
		e.printStackTrace();
	}
	return isAdded;
    }
}
