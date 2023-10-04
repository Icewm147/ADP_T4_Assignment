package za.ac.cput.adp2_t4_assignment_rmn.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nicho
 */
public class DBConnection {
    public static Connection derbyConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/Enrollment_DB";
        String username = "administrator";
        String password = "admin";

        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
