package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestSEM {

	private SEM sem;
	private AppDeUsuario usuario;
	private ZonaDeEstacionamiento zonaEstacionamiento;
	private PuntoDeVenta puntoDeVenta;

	@BeforeEach

	void setUp() {
		sem = new SEM();
		usuario = mock(AppDeUsuario.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
	}

	@Test

	void testSeCreaUnSistemaDeEstacionamientoMedido() {
		assertEquals(sem.cantidadDeUsuarioRegistrados(), 0);
		assertEquals(sem.cantidadDeZonasDeEstacionamiento(), 0);
	}

	@Test
	void testElSemRegistraAUnUsuario() {
		sem.registrarAlUsuario(usuario);
		assertTrue(sem.tieneRegistradoAlUsuario(usuario));
	}

	@Test
	void testElSemNoRegistraDosVecesAlMismoUsuario() {
		sem.registrarAlUsuario(usuario);
		sem.registrarAlUsuario(usuario);
		assertEquals(sem.cantidadDeUsuarioRegistrados(), 1);
	}

	@Test
	void testElSemRegistraUnaZonaDeEstacionamiento() {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		assertEquals(sem.cantidadDeZonasDeEstacionamiento(), 1);
	}

	@Test
	void testNoSeRegistraUnaMismaZonaDeEstacionamientoDosVecesEnElSem() {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		assertEquals(sem.cantidadDeZonasDeEstacionamiento(), 1);
	}

	@Test
	void testCuandoElSemRegistraUnPuntoDeVentaEnUnaZonaDeEstacionamientoElSemLoTieneRegistrado() throws Exception {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.agregarPuntoDeVentaEnLaZonaDeEstacionamiento(puntoDeVenta, zonaEstacionamiento);
		assertTrue(sem.tieneRegistradoElPuntoDeVenta(puntoDeVenta));
	}

	@Test
	void testElSemRegistraUnPuntoDeVentaEnUnaZonaDeEstacionamiento() throws Exception {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.agregarPuntoDeVentaEnLaZonaDeEstacionamiento(puntoDeVenta, zonaEstacionamiento);
		verify(zonaEstacionamiento, atLeastOnce()).agregarPuntoDeVenta(puntoDeVenta);
	}

	@Test
	void testElSemNoPuedeRegistrarUnPuntoDeVentaSiNoSeEncuentraLaZonaDeEstacionamiento() throws Exception {
		assertThrows(Exception.class, () -> {
			sem.agregarPuntoDeVentaEnLaZonaDeEstacionamiento(puntoDeVenta, zonaEstacionamiento);
		}, "No existen cajas disponibles.");
	}

	@Test
	void testCuandoElSemRegistraUnaZonaDeEstacionamientoRegistraAlInspectorQueContieneLaZona() {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		assertTrue(sem.tieneRegistradosInspectores());
		verify(zonaEstacionamiento, atLeastOnce()).inspector();
	}

}
