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

public class TestEstacionamientoCompraPuntual {

	private AppDeUsuario appUsuario;
	private EstacionamientoCompraPuntual estacionamiento;

	@BeforeEach
	void setUp() {
		appUsuario = mock(AppDeUsuario.class);
		estacionamiento = new EstacionamientoCompraPuntual(appUsuario,
				LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)), "AA20AA", 4);
	}

	@Test
	void unEstacionamientoCuandoEsCreadoTieneCantidadDeHoras() {
		assertEquals(estacionamiento.getCantidadDeHoras(), 4);
	}

	@Test
	void unEstacionamientoPuedeCalcularSuHoraDeFinSegunSuCantidadDeHoras() {
		assertEquals(estacionamiento.getHoraFin(), LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)));
	}
}
