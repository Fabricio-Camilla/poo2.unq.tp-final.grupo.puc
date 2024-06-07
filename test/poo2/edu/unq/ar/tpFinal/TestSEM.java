package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestSEM {

	private SEM sem;
	private AppDeUsuario usuario;
	private ZonaDeEstacionamiento zonaEstacionamiento;

	@BeforeEach

	void setUp() {
		sem = new SEM();
		usuario = mock(AppDeUsuario.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
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
		sem.registrarZonaDeEstacionamiento(new Point(1,2));
		assertEquals(sem.cantidadDeZonasDeEstacionamiento(), 1);
	}

}
