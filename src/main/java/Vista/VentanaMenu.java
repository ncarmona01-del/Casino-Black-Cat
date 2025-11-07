package Vista;

import Controlador.ControladorEstadisticas;
import Controlador.ControladorResultado;
import Controlador.ControladorSesion;
import Modelo.Usuario;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu {

    private final ControladorSesion sesion;
    private final JFrame ventana;
    private final JLabel etiquetaUsuario;

    public VentanaMenu(ControladorSesion sesion) {
        this.sesion = sesion;

        ventana = new JFrame("Menu - Casino Black Cat");
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

        JButton botonSalir = new JButton("Salir (Cerrar Sesión)");
        botonSalir.setBounds(20, 250, 150, 40);
        ventana.add(botonSalir);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setText("Bienvenido/a al menú principal, " + sesion.getUsuarioActual().getNombreCompleto() + ".\n\n"
                + "• Jugar: abre la ventana de juego.\n"
                + "• Ver Perfil: Edita su nombre o recarga saldo.\n"
                + "• Historial: Muestra sus jugadas previas.\n"
                + "• Estadísticas: Muestra un resumen de su desempeño.\n"
                + "• Salir: cierra sesión y vuelve al login.");
        areaTexto.setBounds(190, 20, 380, 340);
        areaTexto.setBorder(BorderFactory.createEtchedBorder());
        ventana.add(areaTexto);

        etiquetaUsuario = new JLabel("Usuario: " + sesion.getUsuarioActual().getNombreCompleto() + " | Saldo: $" + sesion.getUsuarioActual().getSaldo());
        etiquetaUsuario.setBounds(10, 380, 400, 25);
        ventana.add(etiquetaUsuario);

        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRuleta ventanaJuego = new VentanaRuleta(sesion, VentanaMenu.this);
            }
        });

        botonPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPerfil vistaPerfil = new VentanaPerfil(sesion);
                actualizarSaldo();
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
        ControladorEstadisticas ctrlStats = new ControladorEstadisticas(sesion);
        VentanaEstadisticas vistaStats = new VentanaEstadisticas(ctrlStats);
        vistaStats.mostrarVentana();
    }

    private void abrirVentanaHistorial() {
        ControladorResultado ctrlHistorial = new ControladorResultado(sesion);
        VentanaHistorial vistaHistorial = new VentanaHistorial(ctrlHistorial);
        vistaHistorial.mostrarVentana();
    }

    public void actualizarSaldo() {
        etiquetaUsuario.setText("Usuario: " + sesion.getUsuarioActual().getNombreCompleto() +
                " | Saldo: $" + sesion.getUsuarioActual().getSaldo());
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }
}