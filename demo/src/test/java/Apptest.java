import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Apptest {
    @Test
    public void loginCorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);
        respuesta.put("nombre", "sergio");

        assertEquals(true, respuesta.get("success"));
        assertEquals("sergio", respuesta.get("nombre"));
    }
     @Test
    public void loginIncorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", false);
        respuesta.put("mensaje", "Email o contraseña incorrectos");

        assertEquals(false, respuesta.get("success"));
    }
    @Test
    public void registroCorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);
        respuesta.put("mensaje", "Usuario registrado");

        assertEquals(true, respuesta.get("success"));
    }
    @Test
    public void registroIncorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", false);
        respuesta.put("mensaje", "Error al registrar");

        assertEquals(false, respuesta.get("success"));
    }
     @Test
    public void likeCorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);

        assertEquals(true, respuesta.get("success"));
    }
    @Test
    public void likeIncorrecto() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", false);

        assertEquals(false, respuesta.get("success"));
    }
}
