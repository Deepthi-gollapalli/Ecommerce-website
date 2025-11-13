import java.sql.*;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_db", "root", "root");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
