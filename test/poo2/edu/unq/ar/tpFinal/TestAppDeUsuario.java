package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAppDeUsuario {

	private AppDeUsuario appDeUsuario;
	private ModoDeUso modoManual;
	private ModoDeUso modoAutomatico;
	private IEstadoDeEstacionamiento vigente;
	private IEstadoDeEstacionamiento noVigente;
	private SEM sem;
	private ZonaDeEstacionamiento zona;
	private Set<AppDeUsuario> usuarios;
	private Set<ZonaDeEstacionamiento> zonas;

	@BeforeEach
	void setUp() {
		usuarios = new HashSet<AppDeUsuario>();
		zonas = new HashSet<ZonaDeEstacionamiento>();
		zona = mock(ZonaDeEstacionamiento.class);
		vigente = spy(EstacionamientoVigente.class);
		sem = mock(SEM.class);
		appDeUsuario = new AppDeUsuario("1118654287", "SNW 025", sem);
		modoManual = mock(ModoManual.class);
		modoAutomatico = mock(ModoAutomatico.class);
		noVigente = spy(EstacionamientoNoVigente.class);
		usuarios.add(appDeUsuario);
		zonas.add(zona);
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
	void testUnaAppIniciaConEstacionamientoEstadoNoVigente() throws Exception {
		assertFalse(appDeUsuario.getEstado().estaVigente());
	}

	@Test
	void testUnaAppCambiaElEstacionamientoAEstadoVigente() throws Exception {
		Point locali = new Point(1,1);
		when(sem.calcularSaldoSuficiente(appDeUsuario)).thenReturn(true);
		when(sem.getZonasDeEstacionamiento()).thenReturn(zonas);
		when(zona.getLocalizacion()).thenReturn(locali);
		when(sem.encontrarZonaEstacionamientoEn(locali)).thenReturn(zona);
		
		assertFalse(appDeUsuario.getEstado().estaVigente()); 
		
		appDeUsuario.indicarInicioDeEstaciomiento();
		
		assertTrue(appDeUsuario.getEstado().estaVigente());
	}
	
	@Test
	void testUnaAppCambiaElEstacionamientoAEstadoNoVigente() throws Exception {
		Point locali = new Point(1,1);
		when(sem.calcularSaldoSuficiente(appDeUsuario)).thenReturn(true);
		when(sem.getZonasDeEstacionamiento()).thenReturn(zonas);
		when(zona.getLocalizacion()).thenReturn(locali);
		when(sem.encontrarZonaEstacionamientoEn(locali)).thenReturn(zona); 
		
		appDeUsuario.indicarInicioDeEstaciomiento();
		appDeUsuario.indicarFinDeEstacionamiento();
		
		assertFalse(appDeUsuario.getEstado().estaVigente());
		verify(sem, atLeastOnce()).finalizarEstacionamiento(appDeUsuario.getCelular());
	}
	

	@Test
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarAWalking() throws Exception {
		appDeUsuario.setEstado(noVigente);
		appDeUsuario.walking();
		
		verify(noVigente).alertaInicioEstacionamiento(appDeUsuario);
	}

	@Test
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarADriving() throws Exception {
		when(sem.getUsuariosRegistrados()).thenReturn(usuarios);
		
		appDeUsuario.setEstado(vigente);
		appDeUsuario.driving();
		
		verify(vigente).alertaFinEstacionamiento(appDeUsuario);
	}
	
	@Test
	void testAUnaAppLeLlegaNotificacioDeInicioFinEstacionamientoAutomatico() {
		appDeUsuario.notificarFinEstacionamiento("Se realizo fin de estacionamiento automaticamente");
		appDeUsuario.notificarInicioEstacionamiento("Se realizo inicio de estacionamiento automaticamente");
		
		assertEquals(appDeUsuario.getNotificaciones().size(), 2);
	}
	
	@Test
	void testAUnUsuairoSeLeDescuentaDeSuCredito() {
		appDeUsuario.cargarCredito(40d);
		appDeUsuario.cobrarEstacionamiento(20d);
		
		assertEquals(appDeUsuario.getCredito(), 20d);
	}

	// si falta coverage, el caso estando no vigente que le llegue driving y vigente
	// walking

}
