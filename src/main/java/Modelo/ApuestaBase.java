package Modelo;

public abstract class ApuestaBase {

    private int montoApostado;

    public ApuestaBase(int monto) {
        this.montoApostado = monto;
    }

    public int getMontoApostado() {
        return montoApostado;
    }

    public abstract int calcularPremio(Resultado giro);

    public abstract String getTipo();

    public abstract String getValor();

    public String getEtiqueta() {
        return getTipo() + " (" + getValor() + ")";
    }
}