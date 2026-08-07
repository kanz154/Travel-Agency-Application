package DatabaseLayer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // ✅ Yeh line wapas daalo - ZAROORI HAI
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✓ Database connected successfully!");
            
        } catch (ClassNotFoundException e) {
            // Yeh error aaya = JAR sahi add nahi hua
            System.err.println("✗ Driver JAR missing: " + e.getMessage());
            
        } catch (SQLException e) {
            System.err.println("✗ Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}