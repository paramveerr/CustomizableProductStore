import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CustomizableProductStore";
    private static final String DB_USERNAME = "root"; // Change this to your MySQL username
    private static final String DB_PASSWORD = "password"; // Change this to your MySQL password

    // JDBC connection method
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver (not necessary with newer versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
