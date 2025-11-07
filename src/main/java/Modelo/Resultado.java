package Modelo;

public class Resultado {

    private final int numeroGanador;
    private final String colorGanador;

    private final ApuestaBase apuesta;
    private final int montoResultado;

    public Resultado(int numeroGanador, String colorGanador, ApuestaBase apuesta, int montoResultado) {
        this.numeroGanador = numeroGanador;
        this.colorGanador = colorGanador;
        this.apuesta = apuesta;
        this.montoResultado = montoResultado;
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public String getColorGanador() {
        return colorGanador;
    }

    public ApuestaBase getApuesta() {
        return apuesta;
    }

    public int getMontoResultado() {
        return montoResultado;
    }

    public int getMontoApostado() {
        return apuesta.getMontoApostado();
    }

    @Override
    public String toString() {
        String ganoPerdio = "";
        if (montoResultado > 0) {
            ganoPerdio = "GANÓ";
        } else {
            ganoPerdio = "PERDIÓ";
        }

        int gananciaNeta = montoResultado - getMontoApostado();

        return "Apostó $" + getMontoApostado() +
                " a " + apuesta.getEtiqueta() +
                ". Salió " + numeroGanador +
                " (" + colorGanador + "). Resultado: " +
                ganoPerdio + " ($" + gananciaNeta + ")";
    }
}
