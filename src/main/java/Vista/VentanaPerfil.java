package Vista;

import Controlador.ControladorSesion;
import Modelo.Usuario;
import javax.swing.*;

public class VentanaPerfil {

    private final ControladorSesion sesion;

    public VentanaPerfil(ControladorSesion sesion) {
        this.sesion = sesion;

        Usuario usuario = sesion.getUsuarioActual();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Usuario: " + usuario.getNombreUsuario() + " (no editable)"));

        panel.add(new JLabel("Nombre (editable):"));
        JTextField campoNombre = new JTextField(usuario.getNombreCompleto());
        panel.add(campoNombre);

        panel.add(new JLabel("Saldo Actual: $" + usuario.getSaldo()));
        panel.add(new JLabel("Monto a Recargar:"));
        JTextField campoRecarga = new JTextField("0");
        panel.add(campoRecarga);

        int resultado = JOptionPane.showConfirmDialog(null, panel, "Mi Perfil",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            if (!usuario.setNombreCompleto(campoNombre.getText())) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                int monto = Integer.parseInt(campoRecarga.getText());
                usuario.depositar(monto);
            } catch (NumberFormatException e) {
            }
        }
    }
}