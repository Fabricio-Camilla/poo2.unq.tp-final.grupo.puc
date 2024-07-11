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
		modoManual = spy(ModoManual.class);
		modoAutomatico = spy(ModoAutomatico.class);
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
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarAWalking() throws Exception {
		when(sem.getUsuariosRegistrados()).thenReturn(usuarios);
		
		appDeUsuario.cambiarModo(modoAutomatico);
		appDeUsuario.setEstado(noVigente);
		appDeUsuario.walking();
		
		verify(noVigente, atLeastOnce()).alertaInicioEstacionamiento(appDeUsuario);
	}

	@Test
	void testAUnaAppLeLlegaNotificacionDeAlertaAlCambiarADriving() throws Exception {
		when(sem.getUsuariosRegistrados()).thenReturn(usuarios);
		
		appDeUsuario.cambiarModo(modoAutomatico);;
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
	void testUnUsuarioPuedeActivarElModoDeDesplazamiento() {
		appDeUsuario.activarModoDesplazamiento();
		assertTrue(appDeUsuario.getModoDesplazamiento());
	}
	
	@Test
	void testUnUsuarioPuedeDesactivarElModoDeDesplazamiento() {
		appDeUsuario.desactivarModoDesplazamiento();
		assertFalse(appDeUsuario.getModoDesplazamiento());
	}
	@Test
	void testAUnUsuairoSeLeDescuentaDeSuCredito() {
		appDeUsuario.cargarCredito(40d);
		appDeUsuario.cobrarEstacionamiento(20d);
		
		assertEquals(appDeUsuario.getCredito(), 20d);
	}
	
	@Test
	void testUnUsuarioIndicaFinDeEstacionamiento() throws Exception {
		appDeUsuario.activarModoDesplazamiento();
		appDeUsuario.cambiarModo(modoAutomatico);
		appDeUsuario.indicarFinDeEstacionamiento();
		
		verify(modoAutomatico).finDeEstacionamiento(appDeUsuario);
	}
 
	@Test
	void testUnUsuarioIndicaFinDeEstacionamientoConDesplazamientoDesactivado() throws Exception {
		appDeUsuario.cambiarModo(modoAutomatico);
		appDeUsuario.indicarFinDeEstacionamiento();
		
		verify(modoAutomatico, never()).finDeEstacionamiento(appDeUsuario);
	}
	
	@Test
	void testUnUsuarioIndicaIncioDeEstacionamiento() throws Exception {
		appDeUsuario.activarModoDesplazamiento();
		appDeUsuario.cambiarModo(modoAutomatico);
		appDeUsuario.indicarInicioDeEstacionamiento();
		
		verify(modoAutomatico).inicioDeEstacionamiento(appDeUsuario);
	}
 
	@Test
	void testUnUsuarioIndicaInicioDeEstacionamientoConDesplazamientoDesactivado() throws Exception {
		appDeUsuario.cambiarModo(modoAutomatico);
		appDeUsuario.indicarInicioDeEstacionamiento();
		
		verify(modoAutomatico, never()).inicioDeEstacionamiento(appDeUsuario);
	}
}
