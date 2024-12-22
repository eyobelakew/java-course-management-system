package application;

import java.sql.*;
import java.util.List;  // Add this import
import java.util.ArrayList;  // Add this import

public class CourseDAO {

    private static final String URL = "jdbc:mysql://vps.eyobl.com:3306/CourseManagement";
    private static final String USER = "Java3rdYearAssignment";
    private static final String PASSWORD = "GroupAssignment123";

    // Get connection method
    private Connection getConnection() throws SQLException {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }

    // Method to add a course to the database
    public void addCourse(CourseModel course) {
        String query = "INSERT INTO Courses (CourseID, CourseName, InstructorName, Duration, Credits) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseID());  // Use String for CourseID
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getInstructorName());
            stmt.setInt(4, course.getDuration());
            stmt.setInt(5, course.getCredits());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get all courses
    public List<CourseModel> getAllCourses() {
        List<CourseModel> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String courseID = rs.getString("CourseID");
                String courseName = rs.getString("CourseName");
                String instructorName = rs.getString("InstructorName");
                int duration = rs.getInt("Duration");
                int credits = rs.getInt("Credits");

                courses.add(new CourseModel(courseID, courseName, instructorName, duration, credits));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Method to update a course
    public void updateCourse(CourseModel course) {
        String query = "UPDATE Courses SET CourseName = ?, InstructorName = ?, Duration = ?, Credits = ? WHERE CourseID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getInstructorName());
            stmt.setInt(3, course.getDuration());
            stmt.setInt(4, course.getCredits());
            stmt.setString(5, course.getCourseID());  // Use String for CourseID
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a course
    public void deleteCourse(String courseID) {
        String query = "DELETE FROM Courses WHERE CourseID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseID);  // Use String for CourseID
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
