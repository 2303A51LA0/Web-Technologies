import java.sql.*;
import java.util.Scanner;

public class CollegeManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/collegeDB";;
    private static final String USER = "root"; // Change if necessary
    private static final String PASSWORD = "Sindhu@123"; // Change this

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            createCoursesTable(conn);
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nCollege Management System:");
                System.out.println("1. Insert a new course");
                System.out.println("2. Update course credits");
                System.out.println("3. Delete a course");
                System.out.println("4. Display all courses");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Course Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Credits: ");
                        int credits = scanner.nextInt();
                        insertCourse(conn, name, credits);
                        break;
                    case 2:
                        System.out.print("Enter Course ID: ");
                        int updateId = scanner.nextInt();
                        System.out.print("Enter new Credits: ");
                        int newCredits = scanner.nextInt();
                        updateCourseCredits(conn, updateId, newCredits);
                        break;
                    case 3:
                        System.out.print("Enter Course ID: ");
                        int deleteId = scanner.nextInt();
                        deleteCourse(conn, deleteId);
                        break;
                    case 4:
                        displayCourses(conn);
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 5);
            scanner.close();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void createCoursesTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Courses (" +
                     "CourseID INT PRIMARY KEY AUTO_INCREMENT, " +
                     "Name VARCHAR(100), " +
                     "Credits INT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'Courses' created or already exists.");
        }
    }

    private static void insertCourse(Connection conn, String name, int credits) throws SQLException {
        String sql = "INSERT INTO Courses (Name, Credits) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, credits);
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");
        }
    }

    private static void updateCourseCredits(Connection conn, int courseId, int newCredits) throws SQLException {
        String sql = "UPDATE Courses SET Credits = ? WHERE CourseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newCredits);
            pstmt.setInt(2, courseId);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("Course not found.");
            }
        }
    }

    private static void deleteCourse(Connection conn, int courseId) throws SQLException {
        String sql = "DELETE FROM Courses WHERE CourseID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            int deleted = pstmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Course not found.");
            }
        }
    }

    private static void displayCourses(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Courses";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nCourses Table Records:");
            System.out.println("-----------------------------------------");
            while (rs.next()) {
                int courseId = rs.getInt("CourseID");
                String name = rs.getString("Name");
                int credits = rs.getInt("Credits");
                System.out.println("CourseID: " + courseId + " | Name: " + name + " | Credits: " + credits);
            }
        }
    }
}
