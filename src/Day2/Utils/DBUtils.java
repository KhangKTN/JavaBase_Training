package Day2.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    private static DBUtils instance;
    private Connection connection;


    private DBUtils() {
        Properties properties = new Properties();

        try {
//            properties.load(
//                    DBUtils.class.getResourceAsStream("/dbConfig.properties"));


//            String driver = properties.getProperty("DRIVER");
//            String url = properties.getProperty("DB_URL");
//            String userName = properties.getProperty("USERNAME");
//            String password = properties.getProperty("PASSWORD");

//            String driver = properties.getProperty("DRIVER");
            String url = "jdbc:mysql://localhost:3306/FPT_Candidate";
            String userName = "root";
            String password = null;

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, userName, password);
            Log.info("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Log.error("Error connecting to database");
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
    public static DBUtils getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBUtils();
        }
        return instance;
    }

}
