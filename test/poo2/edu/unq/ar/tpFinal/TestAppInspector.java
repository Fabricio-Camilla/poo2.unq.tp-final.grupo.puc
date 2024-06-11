package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAppInspector {

	private AppInspector inspector;
	private AppDeUsuario appUsuario;
	private SEM sem;
	private ZonaDeEstacionamiento zonaEstacionamiento;
	private Estacionamiento estacionamiento;

	@BeforeEach

	void setUp() {
		sem = mock(SEM.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
		inspector = new AppInspector(zonaEstacionamiento, sem);
		estacionamiento = mock(Estacionamiento.class);
		appUsuario = mock(AppDeUsuario.class);
	}

	@Test

	void unInspectorAlSerCreadoPerteneceAUnSEM() {
		assertTrue(inspector.perteneceAlSem(sem));
	}

	@Test

	void unInspectorAlSerCreadoPerteneceAUnaZonaDeEstacionamiento() {
		assertTrue(inspector.estaAsignadoALaZonaDeEstacionamiento(zonaEstacionamiento));
	}

	@Test
	
	void unInspectorPuedeConsultarAlSemSiUnEstacionamientoEstaVigente() throws Exception {
		when(sem.estaVigenteElEstacionamientoConPatente("AF245GF")).thenReturn(true);
		assertTrue(inspector.estaVigenteElEstacionamientoConPatente("AF245GF"));
	}
	
	@Test

	void unInspectorRecorreLaZonaDeEstacionamientoVerificandoLaVigenciaDeLosEstacionamientos() throws Exception {
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		estacionamientos.add(estacionamiento);

		when(sem.getEstacionamientosRegistrados()).thenReturn(estacionamientos);
		when(zonaEstacionamiento.getEstacionamientosRegistrados()).thenReturn(estacionamientos);
		when(zonaEstacionamiento.sem()).thenReturn(sem);
		when(zonaEstacionamiento.inspector()).thenReturn(inspector);
		when(appUsuario.getPatente()).thenReturn("AF254AF");
		when(appUsuario.estaVigente()).thenReturn(false);
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AF254AF");
		when(estacionamiento.estaVigente()).thenReturn(false);
		when(sem.estaVigenteElEstacionamientoConPatente("AF254AF")).thenReturn(true);

		inspector.recorrerZonaDeEstacionamiento();

		assertEquals(inspector.cantidadDeInfraccionesEmitidas(), 1);
	}
}
