package com.lovecode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class usuarioDAO {

    public Map<String, Object> registro(Map<String, Object> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        try (Connection conn = conexionbd.getConnection()) {

            String nombre = (String) datos.get("nombre");
            String apellido = (String) datos.get("apellido");
            String ciudad = (String) datos.get("ciudad");
            String direccion = (String) datos.get("direccion");
            String usuarioNombre = (String) datos.get("usuario");
            String email = (String) datos.get("email");
            String contrasena = (String) datos.get("contrasena");
            String descripcion = (String) datos.get("descripcion");

            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO usuario (nombre, apellido, ciudad, direccion, usuario, email, contrasena, descripcion) " +
            "VALUES ('" + nombre + "','" + apellido + "','" + ciudad + "','" + direccion + "','" + usuarioNombre + "','" + email + "','" + contrasena + "','" + descripcion + "')",
            Statement.RETURN_GENERATED_KEYS);

            ResultSet keys = st.getGeneratedKeys();
            if (keys.next()) {
                int idUsuario = keys.getInt(1);
                List<Object> tecnologias = (List<Object>) datos.get("tecnologias");
                if (tecnologias != null) {
                    for (Object idTec : tecnologias) {
                        st.executeUpdate("INSERT INTO usuario_tecnologia (id_usuario, id_tecnologia) " +
                            "VALUES (" + idUsuario + ", " + idTec + ")");
                    }
                }
            }

            respuesta.put("success", true);
            respuesta.put("mensaje", "Usuario registrado");

        } catch (Exception e) {
            respuesta.put("success", false);
            respuesta.put("mensaje", "Error: " + e.getMessage());
        }
        return respuesta;
    }

    public Map<String, Object> login(String email, String contrasena) {
        Map<String, Object> respuesta = new HashMap<>();
        try (Connection conn = conexionbd.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE email = '" + email + "' AND contrasena = '" + contrasena + "'");

            if (rs.next()) {
                respuesta.put("success", true);
                respuesta.put("nombre", rs.getString("nombre"));
                respuesta.put("id", rs.getInt("id_usuario"));
            } else {
                respuesta.put("success", false);
                respuesta.put("mensaje", "Email o contraseña incorrectos");
            }

        } catch (Exception e) {
            respuesta.put("success", false);
            respuesta.put("mensaje", "Error: " + e.getMessage());
        }
        return respuesta;
    }

    public List<Map<String, Object>> perfilesPorId(int id) {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection conn = conexionbd.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT u.id_usuario, u.nombre, u.ciudad, u.descripcion, " +
                "GROUP_CONCAT(t.nombre SEPARATOR ',') AS tecnologias " +
                "FROM usuario u " +
                "LEFT JOIN usuario_tecnologia ut ON u.id_usuario = ut.id_usuario " +
                "LEFT JOIN tecnologia t ON ut.id_tecnologia = t.id_tecnologia " +
                "WHERE u.id_usuario != " + id +
                " GROUP BY u.id_usuario"
            );

            while (rs.next()) {
                Map<String, Object> u = new HashMap<>();
                u.put("id", rs.getInt("id_usuario"));
                u.put("nombre", rs.getString("nombre"));
                u.put("ciudad", rs.getString("ciudad"));
                u.put("descripcion", rs.getString("descripcion"));
                u.put("tecnologias", rs.getString("tecnologias"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public List<Map<String, Object>> tecnologias() {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection conn = conexionbd.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tecnologia");

            while (rs.next()) {
                Map<String, Object> t = new HashMap<>();
                t.put("id", rs.getInt("id_tecnologia"));
                t.put("nombre", rs.getString("nombre"));
                t.put("categoria", rs.getString("categoria"));
                lista.add(t);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public Map<String, Object> obtenerUsuario(int id) {
    Map<String, Object> u = new HashMap<>();
    try (Connection conn = conexionbd.getConnection()) {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE id_usuario = " + id);

        if (rs.next()) {
            u.put("nombre", rs.getString("nombre"));
            u.put("ciudad", rs.getString("ciudad"));
            u.put("descripcion", rs.getString("descripcion"));
            u.put("email", rs.getString("email"));
            u.put("usuario", rs.getString("usuario"));
        }

        ResultSet rsTec = st.executeQuery(
    "SELECT GROUP_CONCAT(t.nombre SEPARATOR ',') AS tecnologias " +
    "FROM usuario_tecnologia ut " +
    "JOIN tecnologia t ON ut.id_tecnologia = t.id_tecnologia " +
    "WHERE ut.id_usuario = " + id
);
if (rsTec.next()) {
    u.put("tecnologias", rsTec.getString("tecnologias"));
}

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    return u;
}
public List<Map<String, Object>> getLikes(int id) {
    List<Map<String, Object>> lista = new ArrayList<>();
    try (Connection conn = conexionbd.getConnection()) {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT u.id_usuario, u.nombre, u.ciudad FROM likes l " +
            "JOIN usuario u ON l.usuario_recibe = u.id_usuario " +
            "WHERE l.usuario_manda = " + id
        );

        while (rs.next()) {
            Map<String, Object> u = new HashMap<>();
            u.put("id", rs.getInt("id_usuario"));
            u.put("nombre", rs.getString("nombre"));
            u.put("ciudad", rs.getString("ciudad"));
            lista.add(u);
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    return lista;
}

public List<Map<String, Object>> getMatches(int id) {
    List<Map<String, Object>> lista = new ArrayList<>();
    try (Connection conn = conexionbd.getConnection()) {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT u.id_usuario, u.nombre, u.ciudad FROM matches m " +
            "JOIN usuario u ON m.usuario_recibe = u.id_usuario " +
            "WHERE m.usuario_manda = " + id
        );

        while (rs.next()) {
            Map<String, Object> u = new HashMap<>();
            u.put("id", rs.getInt("id_usuario"));
            u.put("nombre", rs.getString("nombre"));
            u.put("ciudad", rs.getString("ciudad"));
            lista.add(u);
        }

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    return lista;
}
public void darLike(int usuarioManda, int usuarioRecibe) {
    try (Connection conn = conexionbd.getConnection()) {
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO likes (usuario_manda, usuario_recibe) " +
            "VALUES (" + usuarioManda + ", " + usuarioRecibe + ")");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public void darMatch(int usuarioManda, int usuarioRecibe) {
    try (Connection conn = conexionbd.getConnection()) {
        Statement st = conn.createStatement();
        st.executeUpdate("INSERT INTO matches (usuario_manda, usuario_recibe) " +
            "VALUES (" + usuarioManda + ", " + usuarioRecibe + ")");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
public int contarMatchesRecibidos(int id) {
    int total = 0;
    try (Connection conn = conexionbd.getConnection()) {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
            "SELECT COUNT(*) FROM matches WHERE usuario_manda = " + id + 
            " OR usuario_recibe = " + id
        );
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
    return total;
}
}