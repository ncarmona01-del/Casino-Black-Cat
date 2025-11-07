package Controlador;

import Modelo.Resultado;
import Modelo.Usuario;
import java.util.List;

public class ControladorResultado {

    private final ControladorSesion sesion;

    public ControladorResultado(ControladorSesion sesion) {
        this.sesion = sesion;
    }

    public List<Resultado> getHistorialUsuarioActual() {
        Usuario usuario = sesion.getUsuarioActual();
        return usuario.getHistorial();
    }
}