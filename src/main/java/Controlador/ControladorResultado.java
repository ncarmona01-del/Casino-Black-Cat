package Controlador;

import Modelo.Resultado;
import Repositorio.IRepositorioResultados; // Importar
import java.util.List;

public class ControladorResultado {

    private final IRepositorioResultados repositorio;

    public ControladorResultado(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public List<Resultado> getHistorialUsuarioActual() {
        return repositorio.getHistorial();
    }
}