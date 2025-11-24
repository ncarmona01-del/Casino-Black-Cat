package Test;

import Modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("test", "1234", "Tester", 1000);
    }

    @Test
    public void testCredencialesCorrectas() {
        boolean resultado = usuario.validarCredenciales("test", "1234");
        assertTrue(resultado);
    }

    @Test
    public void testCredencialesIncorrectas() {
        boolean resultado = usuario.validarCredenciales("test", "0000");
        assertFalse(resultado);
    }

    @Test
    public void testDepositarMontoValido() {
        usuario.depositar(500);
        assertEquals(1500, usuario.getSaldo());
    }

    @Test
    public void testDepositarMontoNegativo() {
        usuario.depositar(-100);
        assertEquals(1000, usuario.getSaldo());
    }

    @Test
    public void testDepositarCero() {
        usuario.depositar(0);
        assertEquals(1000, usuario.getSaldo());
    }

    @Test
    public void testRestarSaldoSuficiente() {
        boolean resultado = usuario.restarSaldo(500);
        assertTrue(resultado);
        assertEquals(500, usuario.getSaldo());
    }

    @Test
    public void testRestarSaldoInsuficiente() {
        boolean resultado = usuario.restarSaldo(2000);
        assertFalse(resultado);
        assertEquals(1000, usuario.getSaldo());
    }

    @Test
    public void testRestarSaldoExacto() {
        boolean resultado = usuario.restarSaldo(1000);
        assertTrue(resultado);
        assertEquals(0, usuario.getSaldo());
    }

    @Test
    public void testCrearUsuarioConSaldoNegativo() {
        Usuario usuarioNegativo = new Usuario("mal", "123", "Malo", -500);
        assertEquals(0, usuarioNegativo.getSaldo());
    }
}