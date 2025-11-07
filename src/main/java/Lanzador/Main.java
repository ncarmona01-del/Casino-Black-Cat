package Lanzador;

import Controlador.ControladorSesion;
import Vista.VentanaLogin;

public class Main {

    public static void main(String[] args) {
        ControladorSesion sesion = new ControladorSesion();
        VentanaLogin login = new VentanaLogin(sesion);
        login.mostrarVentana();
    }
}