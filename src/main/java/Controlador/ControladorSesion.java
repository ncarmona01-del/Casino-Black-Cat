package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ControladorSesion {

    private final List<Usuario> usuariosRegistrados;
    private Usuario usuarioActual;

    public ControladorSesion() {
        this.usuariosRegistrados = new ArrayList<>();
        this.usuarioActual = null;
        cargarUsuariosDePrueba();
    }

    private void cargarUsuariosDePrueba() {
        usuariosRegistrados.add(new Usuario("nicolas", "1234", "Nicolás Rojas", 1000));
        usuariosRegistrados.add(new Usuario("admin", "admin", "Administrador", 5000));
    }

    public boolean iniciarSesion(String u, String p) {
        for (Usuario user : usuariosRegistrados) {
            if (user.validarCredenciales(u, p)) {
                this.usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public boolean hayUsuarioActivo() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public boolean registrarUsuario(String u, String p, String n) {
        if (u == null || u.isEmpty() || p == null || p.isEmpty() || n == null || n.isEmpty()) {
            return false;
        }

        for (Usuario user : usuariosRegistrados) {
            if (user.getNombreUsuario().equals(u)) {
                return false;
            }
        }

        Usuario nuevoUsuario = new Usuario(u, p, n, 1000);
        usuariosRegistrados.add(nuevoUsuario);
        return true;
    }
}