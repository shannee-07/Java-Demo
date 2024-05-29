import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.validate;

public class JDBCTest {
    // JDBC Credentials
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/amazon";
    static final String USERNAME = "user-01";
    static final String PASSWORD = "098765";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // Execute a query
            String sql = "SELECT orderId, userId, grandTotal FROM orders";
            resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                // Display the data
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
