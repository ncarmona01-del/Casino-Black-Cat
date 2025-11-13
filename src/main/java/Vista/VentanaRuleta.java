package Vista;

import Controlador.ControladorRuleta;
import Controlador.ControladorSesion;
import Modelo.ApuestaBase;
import Modelo.ApuestaColor;
import Modelo.ApuestaNumero;
import Modelo.ApuestaParImpar;
import Repositorio.IRepositorioResultados; // IMPORTANTE

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRuleta {

    private final ControladorSesion sesion;
    private final ControladorRuleta controladorRuleta;
    private final VentanaMenu menuPadre;

    private final JFrame ventana;
    private final JLabel etiquetaSaldo;
    private final JLabel etiquetaResultado;
    private final JComboBox<String> comboTipoApuesta;
    private final JComboBox<String> comboValorApuesta;
    private final JTextField campoMonto;

    private final String[] APUESTAS_NUMERO = new String[37];
    private final String[] APUESTAS_COLOR = {Modelo.Ruleta.ROJO, Modelo.Ruleta.NEGRO};
    private final String[] APUESTAS_PAR_IMPAR = {"PAR", "IMPAR"};
    private final String[] TIPOS_APUESTA = {"Color", "Numero", "Par/Impar"};

    public VentanaRuleta(ControladorSesion sesion, VentanaMenu menuPadre, IRepositorioResultados repositorio) {
        this.sesion = sesion;
        this.menuPadre = menuPadre;

        this.controladorRuleta = new ControladorRuleta(sesion, repositorio);

        for (int i = 0; i <= 36; i++) {
            APUESTAS_NUMERO[i] = String.valueOf(i);
        }

        ventana = new JFrame("Juego de Ruleta");
        ventana.setSize(700, 350);
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);

        JLabel lblTipo = new JLabel("Tipo de apuesta:");
        lblTipo.setBounds(20, 20, 150, 25);
        ventana.add(lblTipo);

        comboTipoApuesta = new JComboBox<>(TIPOS_APUESTA);
        comboTipoApuesta.setBounds(180, 20, 150, 25);
        ventana.add(comboTipoApuesta);

        JLabel lblValor = new JLabel("Seleccione valor:");
        lblValor.setBounds(20, 60, 150, 25);
        ventana.add(lblValor);

        comboValorApuesta = new JComboBox<>(APUESTAS_COLOR); // Por defecto Color
        comboValorApuesta.setBounds(180, 60, 150, 25);
        ventana.add(comboValorApuesta);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(20, 100, 150, 25);
        ventana.add(lblMonto);

        campoMonto = new JTextField("100");
        campoMonto.setBounds(180, 100, 100, 25);
        ventana.add(campoMonto);

        etiquetaSaldo = new JLabel("Saldo: $" + controladorRuleta.getSaldoActual());
        etiquetaSaldo.setBounds(300, 100, 150, 25);
        ventana.add(etiquetaSaldo);

        JButton botonGirar = new JButton("GIRAR");
        botonGirar.setBounds(180, 150, 100, 40);
        ventana.add(botonGirar);

        etiquetaResultado = new JLabel("¡Haga su apuesta!");
        etiquetaResultado.setBounds(20, 220, 660, 30);
        etiquetaResultado.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaResultado.setBorder(BorderFactory.createEtchedBorder());
        ventana.add(etiquetaResultado);

        comboTipoApuesta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarOpcionesApuesta();
            }
        });

        botonGirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugarApuesta();
            }
        });

        ventana.setVisible(true);
    }

    private void actualizarOpcionesApuesta() {
        String tipo = (String) comboTipoApuesta.getSelectedItem();
        comboValorApuesta.removeAllItems();

        if (tipo.equals("Color")) {
            for(String s : APUESTAS_COLOR) comboValorApuesta.addItem(s);
        } else if (tipo.equals("Numero")) {
            for(String s : APUESTAS_NUMERO) comboValorApuesta.addItem(s);
        } else if (tipo.equals("Par/Impar")) {
            for(String s : APUESTAS_PAR_IMPAR) comboValorApuesta.addItem(s);
        }
    }

    private void jugarApuesta() {
        String tipoSeleccionado = (String) comboTipoApuesta.getSelectedItem();
        String valorSeleccionado = (String) comboValorApuesta.getSelectedItem();
        int monto;

        try {
            monto = Integer.parseInt(campoMonto.getText());
            if (monto <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventana, "Monto inválido. Use un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ApuestaBase apuesta = null;
        if (tipoSeleccionado.equals("Color")) {
            apuesta = new ApuestaColor(monto, valorSeleccionado);
        } else if (tipoSeleccionado.equals("Numero")) {
            apuesta = new ApuestaNumero(monto, Integer.parseInt(valorSeleccionado));
        } else if (tipoSeleccionado.equals("Par/Impar")) {
            apuesta = new ApuestaParImpar(monto, valorSeleccionado);
        }

        if (apuesta == null) return;

        String mensaje = controladorRuleta.jugarApuesta(apuesta);

        if (mensaje.startsWith("Error:")) {
            JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            etiquetaResultado.setText(mensaje);
            etiquetaSaldo.setText("Saldo: $" + controladorRuleta.getSaldoActual());
            menuPadre.actualizarSaldo(); // Actualizamos el saldo en el menú de atrás
        }
    }
}