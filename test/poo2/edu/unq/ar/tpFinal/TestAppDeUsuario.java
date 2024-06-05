package poo2.edu.unq.ar.tpFinal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestAppDeUsuario {
	
	private AppDeUsuario appDeUsuario;
	private ModoDeUso modoManual;
	private ModoDeUso modoAutomatico;
	
	@BeforeEach
	void setUp() {
		appDeUsuario = new AppDeUsuario("1118654287", "SNW 025");
		modoManual = mock(ModoManual.class);
		modoAutomatico = mock(ModoAutomatico.class);
	}
	
	@Test
	void testUnaAppSeCreaSinCredito(){
		assertEquals(appDeUsuario.getCredito(), 0.0);
	}
	
	@Test
	void testUnaAppPuedeCargarCredito() {
		appDeUsuario.cargarCredito(20);
		assertEquals(appDeUsuario.getCredito(), 20);
	}
	
	@Test
	void testUnaAppPuedeCambiarSuModoDeUso() {
		appDeUsuario.cambiarModo(modoManual);
		assertEquals(appDeUsuario.getModo(), modoManual);
		appDeUsuario.cambiarModo(modoAutomatico);
		assertEquals(appDeUsuario.getModo(), modoAutomatico);
	}
	
	@Test
	void testUnaAppPuedeResponderSiSuEstadoDeEstacionamientoEstaVigenteONo() {
		
	}
}
