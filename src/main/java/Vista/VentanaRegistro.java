package Vista;

import Controlador.ControladorSesion;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro {

    private final ControladorSesion sesion;
    private final JFrame ventana;
    private final JTextField campoUsuario;
    private final JPasswordField campoContra1;
    private final JPasswordField campoContra2;
    private final JTextField campoNombre;
    private final JButton botonRegistrar;

    public VentanaRegistro(ControladorSesion sesion) {
        this.sesion = sesion;

        ventana = new JFrame("Registro de Usuario");
        ventana.setSize(500, 350);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 40, 100, 25);
        ventana.add(lblUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(200, 40, 200, 25);
        ventana.add(campoUsuario);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setBounds(50, 80, 150, 25);
        ventana.add(lblNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(200, 80, 200, 25);
        ventana.add(campoNombre);

        JLabel lblContra1 = new JLabel("Contraseña:");
        lblContra1.setBounds(50, 120, 100, 25);
        ventana.add(lblContra1);

        campoContra1 = new JPasswordField();
        campoContra1.setBounds(200, 120, 200, 25);
        ventana.add(campoContra1);

        JLabel lblContra2 = new JLabel("Confirmar Contraseña:");
        lblContra2.setBounds(50, 160, 150, 25);
        ventana.add(lblContra2);

        campoContra2 = new JPasswordField();
        campoContra2.setBounds(200, 160, 200, 25);
        ventana.add(campoContra2);

        botonRegistrar = new JButton("Registrar Cuenta");
        botonRegistrar.setBounds(150, 220, 200, 30);
        ventana.add(botonRegistrar);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intentarRegistro();
            }
        });
    }

    public void mostrarVentana() {
        ventana.setVisible(true);
    }

    private void intentarRegistro() {
        String u = campoUsuario.getText();
        String n = campoNombre.getText();
        String p1 = new String(campoContra1.getPassword());
        String p2 = new String(campoContra2.getPassword());

        if (u.isEmpty() || n.isEmpty() || p1.isEmpty()) {
            JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!p1.equals(p2)) {
            JOptionPane.showMessageDialog(ventana, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = sesion.registrarUsuario(u, p1, n);

        if (exito) {
            JOptionPane.showMessageDialog(ventana, "¡Usuario registrado con éxito! Ya puede iniciar sesión.");
            ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(ventana, "Error: El nombre de usuario ya existe o los datos son inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}