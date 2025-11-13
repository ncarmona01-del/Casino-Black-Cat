package Repositorio;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMemoria implements IRepositorioResultados {

    private final List<Resultado> historial;

    public RepositorioMemoria() {
        this.historial = new ArrayList<>();
    }

    @Override
    public void guardarResultado(Resultado resultado) {
        this.historial.add(resultado);
    }

    @Override
    public List<Resultado> getHistorial() {
        return this.historial;
    }

    @Override
    public void limpiarHistorial() {
        this.historial.clear();
    }
}