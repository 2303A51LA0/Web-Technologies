import java.sql.*;

public class StudentDBQuery {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/?user=root";  
        String user = "root";         
        String password = "Sindhu@123";  
        try {
            
           Class.forName("com.mysql.cj.jdbc.Driver");

    
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!\n");

          
            String query = "SELECT * FROM Students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            
            System.out.println("Student Records:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String major = rs.getString("Major");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Major: " + major);
            }

            
            System.out.println("\nTable Metadata:");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + metaData.getColumnName(i) + " (" + metaData.getColumnTypeName(i) + ")");
            }

            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
