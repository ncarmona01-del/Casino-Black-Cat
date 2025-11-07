package Modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Ruleta {

    private final Random aleatorio;
    private final Map<Integer, String> TABLERO;

    public static final String ROJO = "Rojo";
    public static final String NEGRO = "Negro";
    public static final String VERDE = "Verde";

    public Ruleta(int saldoInicial) {
        this.aleatorio = new Random();
        this.TABLERO = new HashMap<>();
        inicializarTablero();
    }

    public Ruleta() {
        this(0);
    }

    private void inicializarTablero() {
        TABLERO.put(0, VERDE);
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int r : rojos) {
            TABLERO.put(r, ROJO);
        }
        for (int i = 1; i <= 36; i++) {
            if (TABLERO.get(i) == null) {
                TABLERO.put(i, NEGRO);
            }
        }
    }

    public Resultado girar() {
        int numeroGanador = aleatorio.nextInt(37);
        String colorGanador = TABLERO.get(numeroGanador);
        return new Resultado(numeroGanador, colorGanador, null, 0);
    }
}
