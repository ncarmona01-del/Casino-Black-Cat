package Vista;

import Controlador.ControladorEstadisticas;
import Modelo.Estadisticas;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class VentanaEstadisticas {

    private final JFrame ventana;
    private final ControladorEstadisticas controlador;

    public VentanaEstadisticas(ControladorEstadisticas controlador) {
        this.controlador = controlador;

        ventana = new JFrame("Estadísticas del Jugador");
        ventana.setSize(450, 400);
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);

        Estadisticas stats = controlador.getEstadisticasUsuario();

        JLabel lblTitulo = new JLabel("Tus Estadísticas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(140, 20, 200, 30);
        ventana.add(lblTitulo);

        JLabel lblJugadas = new JLabel("Jugadas Totales:");
        lblJugadas.setBounds(50, 70, 200, 25);
        ventana.add(lblJugadas);

        JLabel valJugadas = new JLabel(String.valueOf(stats.getJugadasTotales()));
        valJugadas.setBounds(250, 70, 150, 25);
        ventana.add(valJugadas);

        JLabel lblVictorias = new JLabel("Victorias:");
        lblVictorias.setBounds(50, 100, 200, 25);
        ventana.add(lblVictorias);

        JLabel valVictorias = new JLabel(String.valueOf(stats.getVictorias()));
        valVictorias.setBounds(250, 100, 150, 25);
        ventana.add(valVictorias);

        JLabel lblDerrotas = new JLabel("Derrotas:");
        lblDerrotas.setBounds(50, 130, 200, 25);
        ventana.add(lblDerrotas);

        JLabel valDerrotas = new JLabel(String.valueOf(stats.getDerrotas()));
        valDerrotas.setBounds(250, 130, 150, 25);
        ventana.add(valDerrotas);

        JLabel lblPorcentaje = new JLabel("Porcentaje de Victorias:");
        lblPorcentaje.setBounds(50, 160, 200, 25);
        ventana.add(lblPorcentaje);

        JLabel valPorcentaje = new JLabel(String.format("%.2f %%", stats.getPorcentajeVictorias()));
        valPorcentaje.setBounds(250, 160, 150, 25);
        ventana.add(valPorcentaje);

        JLabel lblTotalApostado = new JLabel("Total Apostado:");
        lblTotalApostado.setBounds(50, 190, 200, 25);
        ventana.add(lblTotalApostado);

        JLabel valTotalApostado = new JLabel("$ " + stats.getTotalApostado());
        valTotalApostado.setBounds(250, 190, 150, 25);
        ventana.add(valTotalApostado);

        JLabel lblGananciaNeta = new JLabel("Ganancia/Pérdida Neta:");
        lblGananciaNeta.setBounds(50, 220, 200, 25);
        ventana.add(lblGananciaNeta);

        JLabel valGananciaNeta = new JLabel("$ " + stats.getGananciasPerdidas());
        valGananciaNeta.setForeground(stats.getGananciasPerdidas() >= 0 ? new Color(0, 150, 0) : Color.RED);
        valGananciaNeta.setBounds(250, 220, 150, 25);
        ventana.add(valGananciaNeta);

        JLabel lblRacha = new JLabel("Mejor Racha de Victorias:");
        lblRacha.setBounds(50, 250, 200, 25);
        ventana.add(lblRacha);

        JLabel valRacha = new JLabel(String.valueOf(stats.getRachaMaxima()));
        valRacha.setBounds(250, 250, 150, 25);
        ventana.add(valRacha);

        JLabel lblTipoFavorito = new JLabel("Tipo de Apuesta Favorita:");
        lblTipoFavorito.setBounds(50, 280, 200, 25);
        ventana.add(lblTipoFavorito);

        String tipoFavorito = stats.getTipoMasJugado() != null ? stats.getTipoMasJugado() : "N/A";
        JLabel valTipoFavorito = new JLabel(tipoFavorito);
        valTipoFavorito.setBounds(250, 280, 150, 25);
        ventana.add(valTipoFavorito);
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}