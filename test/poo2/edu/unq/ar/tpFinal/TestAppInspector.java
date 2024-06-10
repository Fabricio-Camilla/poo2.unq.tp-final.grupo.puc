package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAppInspector {

	private AppInspector inspector;
	private SEM sem;
	private ZonaDeEstacionamiento zonaEstacionamiento;

	@BeforeEach

	void setUp() {
		sem = mock(SEM.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
		inspector = new AppInspector(zonaEstacionamiento, sem);
	}

	@Test

	void unInspectorAlSerCreadoPerteneceAUnSEM() {
		assertTrue(inspector.perteneceAlSem(sem));
	}

	@Test

	void unInspectorAlSerCreadoPerteneceAUnaZonaDeEstacionamiento() {
		assertTrue(inspector.estaAsignadoALaZonaDeEstacionamiento(zonaEstacionamiento));
	}
}
