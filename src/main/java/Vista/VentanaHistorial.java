package Vista;

import Controlador.ControladorResultado;
import Modelo.Resultado;
import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;

public class VentanaHistorial {

    private final JFrame ventana;
    private final ControladorResultado controlador;

    public VentanaHistorial(ControladorResultado controlador) {
        this.controlador = controlador;

        ventana = new JFrame("Historial de Jugadas");
        ventana.setSize(700, 400);
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(null);

        JTextArea areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setMargin(new java.awt.Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(areaHistorial);
        scrollPane.setBounds(10, 10, 660, 340);
        ventana.add(scrollPane);

        List<Resultado> historial = controlador.getHistorialUsuarioActual();

        if (historial.isEmpty()) {
            areaHistorial.setText("No hay jugadas registradas en el historial.");
        } else {
            String texto = "--- TU HISTORIAL DE JUGADAS ---\n\n";
            for (Resultado r : historial) {
                texto = texto + r.toString() + "\n";
            }
            areaHistorial.setText(texto);
        }
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}