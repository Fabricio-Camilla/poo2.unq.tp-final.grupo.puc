package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		estacionamiento = new EstacionamientoViaApp(appUsuario, LocalTime.of(7, 0), LocalTime.of(20, 0), "AA20AA");
	}

	@Test
	void unEstacionamientoCuandoEsCreadoTieneUnaHoraDeInicio() {
		assertEquals(estacionamiento.getHoraInicio(), LocalTime.of(7, 0));
	}

	@Test
	void unEstacionamientoRevisaLaVigenciaConUnInspectorDelSem() throws Exception {
		when(sem.estaVigenteElEstacionamientoConPatente("AA20AA")).thenReturn(false);
		estacionamiento.revisarVigenciaCon(inspector, sem);
		verify(inspector, atLeastOnce()).notificarAlSemPorEstacionamientoNoVigente("AA20AA");
	}
}
