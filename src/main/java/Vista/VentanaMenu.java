package Vista;

import Controlador.ControladorEstadisticas;
import Controlador.ControladorResultado;
import Controlador.ControladorSesion;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu {

    private final ControladorSesion sesion;
    private final JFrame ventana;
    private final JLabel etiquetaUsuario;

    public VentanaMenu(ControladorSesion sesion) {
        this.sesion = sesion;

        ventana = new JFrame("Menu - Casino Black Cat (Versión 08)");
        ventana.setSize(600, 450);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);

        JButton botonJugar = new JButton("Jugar Ruleta");
        botonJugar.setBounds(20, 50, 150, 40);
        ventana.add(botonJugar);

        JButton botonPerfil = new JButton("Ver Perfil");
        botonPerfil.setBounds(20, 100, 150, 40);
        ventana.add(botonPerfil);

        JButton botonHistorial = new JButton("Historial");
        botonHistorial.setBounds(20, 150, 150, 40);
        ventana.add(botonHistorial);

        JButton botonEstadisticas = new JButton("Estadísticas");
        botonEstadisticas.setBounds(20, 200, 150, 40);
        ventana.add(botonEstadisticas);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(20, 250, 150, 40);
        ventana.add(botonSalir);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido/a " + sesion.getUsuarioActual().getNombreCompleto() + ".\n\n"
                + "Esta es la Iteración 08 (Patrón Repositorio).\n"
                + "Ahora el historial se guarda separado del usuario.\n\n"
                + "• Jugar: Prueba tu suerte.\n"
                + "• Historial: Consulta el repositorio de jugadas.\n"
                + "• Estadísticas: Análisis de datos del repositorio.");
        areaTexto.setBounds(190, 20, 380, 340);
        areaTexto.setBorder(BorderFactory.createEtchedBorder());
        ventana.add(areaTexto);

        etiquetaUsuario = new JLabel("Usuario: " + sesion.getUsuarioActual().getNombreCompleto() + " | Saldo: $" + sesion.getUsuarioActual().getSaldo());
        etiquetaUsuario.setBounds(10, 380, 400, 25);
        ventana.add(etiquetaUsuario);

        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRuleta ventanaJuego = new VentanaRuleta(sesion, VentanaMenu.this, sesion.getRepositorio());
            }
        });

        botonPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPerfil vistaPerfil = new VentanaPerfil(sesion);
                actualizarSaldo(); // Por si recarga saldo
            }
        });

        botonHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaHistorial();
            }
        });

        botonEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEstadisticas();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sesion.cerrarSesion();
                ventana.dispose();
                VentanaLogin login = new VentanaLogin(sesion);
                login.mostrarVentana();
            }
        });
    }

    private void abrirVentanaEstadisticas() {
        ControladorEstadisticas ctrlStats = new ControladorEstadisticas(sesion.getRepositorio());
        VentanaEstadisticas vistaStats = new VentanaEstadisticas(ctrlStats);
        vistaStats.mostrarVentana();
    }

    private void abrirVentanaHistorial() {
        ControladorResultado ctrlHistorial = new ControladorResultado(sesion.getRepositorio());
        VentanaHistorial vistaHistorial = new VentanaHistorial(ctrlHistorial);
        vistaHistorial.mostrarVentana();
    }

    public void actualizarSaldo() {
        if (sesion.hayUsuarioActivo()) {
            etiquetaUsuario.setText("Usuario: " + sesion.getUsuarioActual().getNombreCompleto() +
                    " | Saldo: $" + sesion.getUsuarioActual().getSaldo());
        }
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}