package Controlador;

import Modelo.Estadisticas;
import Modelo.Resultado;
import Modelo.Usuario;
import java.util.List;

public class ControladorEstadisticas {

    private final ControladorSesion sesion;

    public ControladorEstadisticas(ControladorSesion sesion) {
        this.sesion = sesion;
    }

    public Estadisticas getEstadisticasUsuario() {
        Usuario usuario = sesion.getUsuarioActual();
        List<Resultado> historial = usuario.getHistorial();
        return new Estadisticas(historial);
    }
}