package com.lovecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class usuariocontrollerDAO {

    usuarioDAO dao = new usuarioDAO();

    @PostMapping("/registro")
    public Map<String, Object> registro(@RequestBody Map<String, Object> datos) {
        return dao.registro(datos);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> datos) {
        return dao.login(datos.get("email"), datos.get("contrasena"));
    }

    @GetMapping("/perfiles/{id}")
    public List<Map<String, Object>> perfilesPorId(@PathVariable int id) {
        return dao.perfilesPorId(id);
    }

    @GetMapping("/tecnologias")
    public List<Map<String, Object>> tecnologias() {
        return dao.tecnologias();
    }
    @GetMapping("/usuario/{id}")
public Map<String, Object> obtenerUsuario(@PathVariable int id) {
    return dao.obtenerUsuario(id);
}
@GetMapping("/likes/{id}")
public List<Map<String, Object>> getLikes(@PathVariable int id) {
    return dao.getLikes(id);
}

@GetMapping("/matches/{id}")
public List<Map<String, Object>> getMatches(@PathVariable int id) {
    return dao.getMatches(id);
}
@PostMapping("/like")
public Map<String, Object> darLike(@RequestBody Map<String, Integer> datos) {
    Map<String, Object> respuesta = new HashMap<>();
    try {
        dao.darLike(datos.get("usuarioManda"), datos.get("usuarioRecibe"));
        respuesta.put("success", true);
    } catch (Exception e) {
        respuesta.put("success", false);
    }
    return respuesta;
}
@PostMapping("/match")
public Map<String, Object> darMatch(@RequestBody Map<String, Integer> datos) {
    Map<String, Object> respuesta = new HashMap<>();
    try {
        dao.darMatch(datos.get("usuarioManda"), datos.get("usuarioRecibe"));
        respuesta.put("success", true);
    } catch (Exception e) {
        respuesta.put("success", false);
    }
    return respuesta;
}
@GetMapping("/matches/total/{id}")
public Map<String, Object> matchesTotal(@PathVariable int id) {
    Map<String, Object> respuesta = new HashMap<>();
    respuesta.put("total", dao.contarMatchesRecibidos(id));
    return respuesta;
}
}