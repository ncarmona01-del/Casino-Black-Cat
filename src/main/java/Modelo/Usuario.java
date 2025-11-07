package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private int saldo;
    private final List<Resultado> historial;

    public Usuario(String nombreUsuario, String contrasena, String nombreCompleto, int saldo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
        this.historial = new ArrayList<>();
    }

    public Usuario() {
        this("invitado", "invitado", "Usuario Invitado", 0);
    }

    public boolean validarCredenciales(String u, String p) {
        return this.nombreUsuario.equals(u) && this.contrasena.equals(p);
    }

    public void depositar(int monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    public boolean restarSaldo(int monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public void agregarResultado(Resultado r) {
        this.historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return this.historial;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean setNombreCompleto(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombreCompleto = nombre;
            return true;
        }
        return false;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}