package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstacionamientoViaApp {

	private AppInspector inspector;
	private SEM sem;
	private AppDeUsuario appUsuario;
	private EstacionamientoViaApp estacionamiento;

	@BeforeEach
	void setUp() {
		inspector = mock(AppInspector.class);
		sem = mock(SEM.class);
		appUsuario = mock(AppDeUsuario.class);
		estacionamiento = new EstacionamientoViaApp(appUsuario, LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)), "AA20AA");
	}

	@Test
	void unEstacionamientoCuandoEsCreadoTieneUnaHoraDeInicio() {
		assertEquals(estacionamiento.getHoraInicio(), LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)));
	}

	@Test
	void unEstacionamientoRevisaLaVigenciaConUnInspectorDelSem() throws Exception {
		when(sem.estaVigenteElEstacionamientoConPatente("AA20AA")).thenReturn(false);
		estacionamiento.revisarVigenciaCon(inspector, sem);
		verify(inspector, atLeastOnce()).notificarAlSemPorEstacionamientoNoVigente("AA20AA");
	}

	@Test
	void unEstacionamientoEstaVigenteSiNoSeSuperaElLimiteHorario() {
		Estacionamiento estacionamientoVigente = new EstacionamientoViaApp(appUsuario,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 59)), "AA20AA");
		
		assertTrue(estacionamientoVigente.estaVigente());
	}
	
	@Test
	void unEstacionamientoNoEstaVigenteSiSuperaElLimiteHorarioDeCierre() {
		Estacionamiento estacionamientoVigente = new EstacionamientoViaApp(appUsuario,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 01)), "AA20AA");
		
		assertFalse(estacionamientoVigente.estaVigente());
	}

	@Test
	void unEstacionamientoNoEstaVigenteUnaVezSuperadoElLimiteHorario() {
		Estacionamiento estacionamientoNoVigente = new EstacionamientoViaApp(appUsuario,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), "AA20AA");
		;
		assertFalse(estacionamientoNoVigente.estaVigente());
	}
	
	@Test
	void unEstacionamientoNoEstaVigenteUnaVezPasadaLasOchoDeLaNoche() {
		Estacionamiento estacionamientoNoVigente = new EstacionamientoViaApp(appUsuario,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 59)), "AA20AA");
		;
		assertTrue(estacionamientoNoVigente.estaVigente());
	}
}
