package Repositorio;

import Modelo.Resultado;
import java.util.List;

public interface IRepositorioResultados {

    void guardarResultado(Resultado resultado);

    List<Resultado> getHistorial();

    void limpiarHistorial();
}