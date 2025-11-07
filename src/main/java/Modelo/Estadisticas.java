package Modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Estadisticas {

    private int totalApostado;
    private int gananciasPerdidas;
    private int victorias;
    private int derrotas;
    private int rachaMaxima;
    private String tipoMasJugado;

    public Estadisticas(List<Resultado> historial) {
        this.totalApostado = 0;
        this.gananciasPerdidas = 0;
        this.victorias = 0;
        this.derrotas = 0;
        this.rachaMaxima = 0;
        this.tipoMasJugado = null;

        procesarHistorial(historial);
    }

    public Estadisticas() {
        this(new ArrayList<>());
    }

    private void procesarHistorial(List<Resultado> historial) {
        if (historial == null || historial.isEmpty()) {
            return;
        }

        int rachaActual = 0;
        Map<String, Integer> conteoApuestas = new HashMap<>();

        for (Resultado r : historial) {
            ApuestaBase apuesta = r.getApuesta();
            if (apuesta == null) continue;

            this.totalApostado += apuesta.getMontoApostado();

            if (r.getMontoResultado() > 0) {
                this.victorias++;
                this.gananciasPerdidas += (r.getMontoResultado() - apuesta.getMontoApostado());
                rachaActual++;
            } else {
                this.derrotas++;
                this.gananciasPerdidas -= apuesta.getMontoApostado();

                if (rachaActual > this.rachaMaxima) {
                    this.rachaMaxima = rachaActual;
                }
                rachaActual = 0;
            }

            String tipo = apuesta.getTipo();
            if (tipo != null) {
                Integer conteo = conteoApuestas.get(tipo);
                if (conteo == null) {
                    conteoApuestas.put(tipo, 1);
                } else {
                    conteoApuestas.put(tipo, conteo + 1);
                }
            }
        }

        if (rachaActual > this.rachaMaxima) {
            this.rachaMaxima = rachaActual;
        }

        int maxConteo = 0;
        for (Map.Entry<String, Integer> entry : conteoApuestas.entrySet()) {
            if (entry.getValue() > maxConteo) {
                maxConteo = entry.getValue();
                this.tipoMasJugado = entry.getKey();
            }
        }
    }

    public int getTotalApostado() {
        return totalApostado;
    }

    public void setTotalApostado(int totalApostado) {
        this.totalApostado = totalApostado;
    }

    public int getGananciasPerdidas() {
        return gananciasPerdidas;
    }

    public void setGananciasPerdidas(int gananciasPerdidas) {
        this.gananciasPerdidas = gananciasPerdidas;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getRachaMaxima() {
        return rachaMaxima;
    }

    public void setRachaMaxima(int rachaMaxima) {
        this.rachaMaxima = rachaMaxima;
    }

    public String getTipoMasJugado() {
        return tipoMasJugado;
    }

    public void setTipoMasJugado(String tipoMasJugado) {
        this.tipoMasJugado = tipoMasJugado;
    }

    public int getJugadasTotales() {
        return victorias + derrotas;
    }

    public double getPorcentajeVictorias() {
        if (getJugadasTotales() == 0) {
            return 0.0;
        }
        return (victorias * 100.0) / getJugadasTotales();
    }
}