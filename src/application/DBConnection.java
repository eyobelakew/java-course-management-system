package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://vps.eyobl.com:3306/CourseManagement";
    private static final String USER = "Java3rdYearAssignment";
    private static final String PASSWORD = "GroupAssignment123";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL driver (only necessary for older versions of JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
