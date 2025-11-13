package Controlador;

import Modelo.Estadisticas;
import Modelo.Resultado;
import Repositorio.IRepositorioResultados; // Importar
import java.util.List;

public class ControladorEstadisticas {

    private final IRepositorioResultados repositorio;

    public ControladorEstadisticas(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public Estadisticas getEstadisticasUsuario() {
        List<Resultado> historial = repositorio.getHistorial();
        return new Estadisticas(historial);
    }
}