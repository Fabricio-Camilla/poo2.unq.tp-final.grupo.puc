package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestZonaDeEstacionamiento {

	private ZonaDeEstacionamiento zonaEstacionamiento;
	private SEM sem;
	private Point localizacion;
	private PuntoDeVenta puntoDeVenta;
	private Estacionamiento estacionamiento;

	@BeforeEach
	void setUp() {
		sem = mock(SEM.class);
		localizacion = new Point(1, 2);
		puntoDeVenta = mock(PuntoDeVenta.class);
		zonaEstacionamiento = new ZonaDeEstacionamiento(sem, localizacion);
		estacionamiento = mock(EstacionamientoViaApp.class);
	}

	@Test
	void testCuandoSeCreaUnaZonaDeEstacionamientoSeEncuentraEnUnPuntoDelMapa() {
		assertTrue(zonaEstacionamiento.seEncuentraUbicadaEnLaLocalizacion(new Point(1, 2)));
	}

	@Test
	void testCuandoSeCreaUnaZonaDeEstacionamientoLaMismaTieneUnInspectorAsignado() {
		assertTrue(zonaEstacionamiento.tieneAsignadoUnInspector());
	}

	@Test
	void testUnaZonaDeEstacionamientoPuedeAgregarUnPuntoDeVenta() {
		zonaEstacionamiento.agregarPuntoDeVenta(puntoDeVenta);
		assertEquals(zonaEstacionamiento.cantidadDePuntosDeVenta(), 1);
	}

	@Test
	void testUnaZonaDeEstacionamientoNoAgregaDosVecesElMismoPuntoDeVenta() {
		zonaEstacionamiento.agregarPuntoDeVenta(puntoDeVenta);
		zonaEstacionamiento.agregarPuntoDeVenta(puntoDeVenta);
		assertEquals(zonaEstacionamiento.cantidadDePuntosDeVenta(), 1);
	}

	@Test

	void testUnaZonaDeEstacionamientoRegistraLosEstacionamientos() {
		zonaEstacionamiento.registrarEstacionamiento(estacionamiento);
		assertTrue(zonaEstacionamiento.estaRegistradoElEstacionamiento(estacionamiento));
	}

}
