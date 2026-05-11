package com.lovecode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class usuariocontrollerDAO {

    // REGISTRO
    @PostMapping("/registro")
    public Map<String, Object> registro(@RequestBody Map<String, String> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        try (Connection conn = conexionbd.getConnection()) {

            String nombre = datos.get("nombre");
            String apellido = datos.get("apellido");
            String ciudad = datos.get("ciudad");
            String direccion = datos.get("direccion");
            String usuarioNombre = datos.get("usuario");
            String email = datos.get("email");
            String contrasena = datos.get("contrasena");

            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO usuario (nombre, apellido, ciudad, direccion, usuario, email, contrasena) " +
                "VALUES ('" + nombre + "','" + apellido + "','" + ciudad + "','" + direccion + "','" + usuarioNombre + "','" + email + "','" + contrasena + "')");

            respuesta.put("success", true);
            respuesta.put("mensaje", "Usuario registrado");

        } catch (Exception e) {
            respuesta.put("success", false);
            respuesta.put("mensaje", "Error: " + e.getMessage());
        }
        return respuesta;
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> datos) {
        Map<String, Object> respuesta = new HashMap<>();
        try (Connection conn = conexionbd.getConnection()) {

            String email = datos.get("email");
            String contrasena = datos.get("contrasena");

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

    // OBTENER TODOS LOS USUARIOS PARA PERFILES
    @GetMapping("/perfiles")
    public List<Map<String, Object>> perfiles() {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection conn = conexionbd.getConnection()) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuario");

            while (rs.next()) {
                Map<String, Object> u = new HashMap<>();
                u.put("id", rs.getInt("id_usuario"));
                u.put("nombre", rs.getString("nombre"));
                u.put("ciudad", rs.getString("ciudad"));
                u.put("descripcion", rs.getString("descripcion"));
                lista.add(u);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}