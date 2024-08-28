package FinalJava.Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static DBUtil instance;
    private Connection connection;


    private DBUtil() {
        Properties properties = new Properties();

        try {
            String url = "jdbc:mysql://localhost:3306/FPT_FinalJava";
            String userName = "root";
            String password = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Get the connection from the instance
     *
     * @return {@link Connection}
     */
    public Connection getConnection() {
        return connection;
    }


    /**
     * Create new instance which connects with the database.
     *
     * @return DBUtils
     * @throws SQLException if connection false.
     */
    public static DBUtil getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBUtil();
        }
        return instance;
    }

}
