package Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApuestasTest {

    @Test
    public void testApuestaColorGana() {
        ApuestaColor apuesta = new ApuestaColor(100, Ruleta.ROJO);
        Resultado giroSimulado = new Resultado(1, Ruleta.ROJO, null, 0);

        int premio = apuesta.calcularPremio(giroSimulado);
        assertEquals(200, premio);
    }

    @Test
    public void testApuestaColorPierde() {
        ApuestaColor apuesta = new ApuestaColor(100, Ruleta.ROJO);
        Resultado giroSimulado = new Resultado(2, Ruleta.NEGRO, null, 0);

        int premio = apuesta.calcularPremio(giroSimulado);
        assertEquals(0, premio);
    }

    @Test
    public void testApuestaNumeroGana() {
        ApuestaNumero apuesta = new ApuestaNumero(100, 15);
        Resultado giroSimulado = new Resultado(15, Ruleta.NEGRO, null, 0);

        int premio = apuesta.calcularPremio(giroSimulado);
        assertEquals(3600, premio);
    }

    @Test
    public void testCeroPierdeColores() {
        ApuestaColor apuesta = new ApuestaColor(100, Ruleta.ROJO);
        Resultado giroCero = new Resultado(0, Ruleta.VERDE, null, 0);

        int premio = apuesta.calcularPremio(giroCero);
        assertEquals(0, premio);
    }
}