import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionbd {

    private static final String URL = "jdbc:mysql://192.168.220.129:3306/LoveCode";
    private static final String USER = "sergio\"%";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }

        return connection;
    }
}
