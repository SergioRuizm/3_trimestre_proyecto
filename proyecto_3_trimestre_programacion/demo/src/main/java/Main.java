import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = conexionbd.getConnection()) {

            if (conn != null) {

                String query = "SELECT * FROM usuario";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_usuario"));
                    System.out.println("Nombre: " + rs.getString("nombre"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Contraseña: " + rs.getString("contrasena"));
                    System.out.println("Descripcion: " + rs.getString("descripcion"));
                    System.out.println("Fecha registro: " + rs.getString("fecha_registro"));
                    System.out.println("Ciudad: " + rs.getString("ciudad"));
                    System.out.println("estado cuenta:" + rs.getString("estado_cuenta"));
                    System.out.println("-------------------");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}