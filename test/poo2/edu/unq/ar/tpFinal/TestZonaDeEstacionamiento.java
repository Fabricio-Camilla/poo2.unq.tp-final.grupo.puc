package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.awt.Point;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestZonaDeEstacionamiento {

	private ZonaDeEstacionamiento zonaEstacionamiento;
	private AppInspector inspector;
	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Point localizacion;

	@BeforeEach
	void setUp() {
		horaInicio = LocalTime.of(7, 0);
		horaFin = LocalTime.of(20, 0);
		inspector = mock(AppInspector.class);
		sem = mock(SEM.class);
		localizacion = new Point(1, 2);
		zonaEstacionamiento = new ZonaDeEstacionamiento(sem, localizacion, inspector, horaInicio, horaFin, 40d);
	}

	@Test
	void cuandoSeCreaUnaZonaDeEstacionamientoSeEncuentraEnUnPuntoDelMapa() {
		assertTrue(zonaEstacionamiento.seEncuentraUbicadaEnLaLocalizacion(new Point(1, 2)));
	}
}
