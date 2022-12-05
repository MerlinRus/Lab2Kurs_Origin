package lab9;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "AlevTinaGreat1";

    private static Connection connection = null;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            DriverManager.registerDriver(new Driver());

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        return connection;
    }

}
