package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAppDeUsuario {

	private AppDeUsuario appDeUsuario;
	private ModoDeUso modoManual;
	private ModoDeUso modoAutomatico;
	private IEstadoDeEstacionamiento vigente;
	private IEstadoDeEstacionamiento noVigente;
	private SEM sem;
	

	@BeforeEach
	void setUp() {
		appDeUsuario = new AppDeUsuario("1118654287", "SNW 025");
		modoManual = mock(ModoManual.class);
		modoAutomatico = mock(ModoAutomatico.class);
		vigente = spy(EstacionamientoVigente.class);
		noVigente = spy(EstacionamientoNoVigente.class);
		sem = spy(SEM.class);
	}

	@Test
	void testUnaAppTieneUnaPatenteAsociada() {
		assertEquals(appDeUsuario.getPatente(), "SNW 025");
	}
	@Test
	void testUnaAppSeCreaSinCredito() {
		assertEquals(appDeUsuario.getCredito(), 0d);
	}

	@Test
	void testUnaAppPuedeCargarCredito() {
		appDeUsuario.cargarCredito(20d);
		assertEquals(appDeUsuario.getCredito(), 20d);
	}

	@Test
	void testUnaAppPuedeCambiarSuModoDeUsoAManual() {
		appDeUsuario.cambiarModo(modoManual);
		assertEquals(appDeUsuario.getModo(), modoManual);
	}

	@Test
	void testUnaAppPuedeCambiarSuModoDeUsoAAutomatico() {
		appDeUsuario.cambiarModo(modoAutomatico);
		assertEquals(appDeUsuario.getModo(), modoAutomatico);
	}

	@Test
	void testUnaAppIniciaConEstacionamientoEstadoNoVigente() {
		appDeUsuario.setSEM(sem);
		assertFalse(appDeUsuario.getEstado().estaVigente());
		appDeUsuario.indicarFinDeEstacionamiento();
		verify(sem).indicarFinEstacionamiento(appDeUsuario);
	}
	
	@Test
	void testUnaAppCambiaElEstacionamientoAEstadoVigente() {
		appDeUsuario.setSEM(sem);
		appDeUsuario.indicarInicioDeEstaciomiento();
		assertFalse(appDeUsuario.getEstado().estaVigente());
		verify(sem).indicarInicioEstacionamiento(appDeUsuario);
	}
	
	@Test
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarAWalking() {
		appDeUsuario.setEstado(noVigente);
		appDeUsuario.walking();
		verify(noVigente).alertaInicioEstacionamiento(appDeUsuario);
	}
	
	@Test
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarADriving() {
		appDeUsuario.setEstado(vigente);
		appDeUsuario.driving();
		verify(vigente).alertaFinEstacionamiento(appDeUsuario);
	}
	
	
	//si falta coverage, el caso estando no vigente que le llegue driving y vigente walking 
	
	
	
	
	
	
}
