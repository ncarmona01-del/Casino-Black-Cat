package Modelo;

public class ApuestaNumero extends ApuestaBase {

    private int numero;

    public ApuestaNumero(int monto, int numero) {
        super(monto);
        this.numero = numero;
    }

    @Override
    public int calcularPremio(Resultado giro) {
        if (giro.getNumeroGanador() == this.numero) {
            return getMontoApostado() * 36;
        }
        return 0;
    }

    @Override
    public String getTipo() {
        return "Numero";
    }

    @Override
    public String getValor() {
        return String.valueOf(numero);
    }
}
