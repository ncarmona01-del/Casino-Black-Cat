package Modelo;

public class ApuestaColor extends ApuestaBase {

    private String color;

    public ApuestaColor(int monto, String color) {
        super(monto);
        this.color = color;
    }

    @Override
    public int calcularPremio(Resultado giro) {
        if (giro.getColorGanador().equals(Ruleta.VERDE)) {
            return 0;
        }
        if (giro.getColorGanador().equals(this.color)) {
            return getMontoApostado() * 2;
        }
        return 0;
    }

    @Override
    public String getTipo() {
        return "Color";
    }

    @Override
    public String getValor() {
        return color;
    }
}
