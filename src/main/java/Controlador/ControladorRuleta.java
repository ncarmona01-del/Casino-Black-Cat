package Controlador;

import Modelo.Ruleta;
import Modelo.Usuario;
import Modelo.Resultado;
import Modelo.ApuestaBase;

public class ControladorRuleta {

    private final Ruleta motorRuleta;
    private final ControladorSesion sesion;

    public ControladorRuleta(ControladorSesion sesion) {
        this.sesion = sesion;
        this.motorRuleta = new Ruleta();
    }

    public String jugarApuesta(ApuestaBase apuesta) {
        Usuario usuario = sesion.getUsuarioActual();
        if (usuario == null) {
            return "Error: No hay sesión de usuario.";
        }

        if (!usuario.restarSaldo(apuesta.getMontoApostado())) {
            return "Error: Saldo insuficiente.";
        }

        Resultado giro = motorRuleta.girar();

        int premio = apuesta.calcularPremio(giro);

        Resultado resultadoJugada = new Resultado(
                giro.getNumeroGanador(),
                giro.getColorGanador(),
                apuesta,
                premio);

        usuario.agregarResultado(resultadoJugada);

        String mensaje;
        if (premio > 0) {
            usuario.depositar(premio);
            mensaje = "¡GANASTE! Salió " + giro.getNumeroGanador() + " (" + giro.getColorGanador() + "). Ganaste $" + (premio - apuesta.getMontoApostado());
        } else {
            mensaje = "PERDISTE. Salió " + giro.getNumeroGanador() + " (" + giro.getColorGanador() + "). Apostaste a " + apuesta.getEtiqueta();
        }
        return mensaje;
    }

    public int getSaldoActual() {
        if(sesion.hayUsuarioActivo()) {
            return sesion.getUsuarioActual().getSaldo();
        }
        return 0;
    }
}