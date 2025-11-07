package Modelo;

public class ApuestaParImpar extends ApuestaBase {

    private String tipo;

    public ApuestaParImpar(int monto, String tipo) {
        super(monto);
        this.tipo = tipo;
    }

    @Override
    public int calcularPremio(Resultado giro) {
        if (giro.getColorGanador().equals(Ruleta.VERDE)) {
            return 0;
        }

        boolean esPar = giro.getNumeroGanador() % 2 == 0;

        if (tipo.equals("PAR") && esPar) {
            return getMontoApostado() * 2;
        }
        if (tipo.equals("IMPAR") && !esPar) {
            return getMontoApostado() * 2;
        }
        return 0;
    }

    @Override
    public String getTipo() {
        return "Par/Impar";
    }

    @Override
    public String getValor() {
        return tipo;
    }
}