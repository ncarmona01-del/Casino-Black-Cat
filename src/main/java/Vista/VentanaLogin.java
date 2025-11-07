package Vista;

import Controlador.ControladorSesion;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin {

    private final ControladorSesion sesion;
    private final JFrame ventana;
    private final JTextField campoUsuario;
    private final JPasswordField campoContrasena;
    private final JButton botonIngresar;
    private final JButton botonRegistrar;

    public VentanaLogin(ControladorSesion sesion) {
        this.sesion = sesion;

        ventana = new JFrame("Login - Casino Black Cat");
        ventana.setSize(500, 350);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 40, 100, 25);
        ventana.add(lblUsuario);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setBounds(50, 80, 100, 25);
        ventana.add(lblClave);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(150, 40, 200, 25);
        ventana.add(campoUsuario);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(150, 80, 200, 25);
        ventana.add(campoContrasena);

        botonIngresar = new JButton("Ingresar");
        botonIngresar.setBounds(150, 130, 150, 30);
        ventana.add(botonIngresar);

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setBounds(150, 170, 150, 30);
        ventana.add(botonRegistrar);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intentarLogin();
            }
        });

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });

        KeyAdapter adaptadorTeclaEnter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonIngresar.doClick();
                }
            }
        };
        campoUsuario.addKeyListener(adaptadorTeclaEnter);
        campoContrasena.addKeyListener(adaptadorTeclaEnter);
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }

    private void abrirRegistro() {
        VentanaRegistro vistaRegistro = new VentanaRegistro(sesion);
        vistaRegistro.mostrarVentana();
    }

    private void intentarLogin() {
        String u = campoUsuario.getText();
        String p = new String(campoContrasena.getPassword());

        if (sesion.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(ventana, "¡Bienvenido, " + sesion.getUsuarioActual().getNombreCompleto() + "!", "Inicio de sesión exitoso", JOptionPane.INFORMATION_MESSAGE);
            ventana.dispose();
            VentanaMenu ventanaMenu = new VentanaMenu(sesion);
            ventanaMenu.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        }
    }
}
