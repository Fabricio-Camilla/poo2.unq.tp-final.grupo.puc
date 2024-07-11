package poo2.edu.unq.ar.tpFinal;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPuntoDeVenta {

	private SEM sistema;
	private PuntoDeVenta puntoDeVenta;
	private ZonaDeEstacionamiento zona;
	private AppDeUsuario appUsuario;

	@BeforeEach
	void setUp() {
		sistema = mock(SEM.class);
		zona = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = new PuntoDeVenta(sistema, zona);
		appUsuario = mock(AppDeUsuario.class);
	}

	@Test
	void testEnUnPuntoDeVentaUnUsuarioPuedeCargarCredito() throws Exception {
		HashSet usuarios = new HashSet<AppDeUsuario>();
		usuarios.add(appUsuario);
		when(sistema.getUsuariosRegistrados()).thenReturn(usuarios);
		when(appUsuario.getCelular()).thenReturn("0123456789");
		
		puntoDeVenta.cargarCredito(20d, "0123456789");
		verify(sistema, atLeastOnce()).cargarCredito(20d, "0123456789");
	}

	@Test
	void testUnPuntoDeVentaCreaYRegistraUnEstacionamientoDeCompraPuntual() throws Exception {
		when(sistema.getTickets()).thenReturn(new HashSet<Ticket>());
		when(sistema.getEstacionamientosRegistrados()).thenReturn(new HashSet<Estacionamiento>());		
				
		Estacionamiento estacionamiento = puntoDeVenta.registarEstacionamiento(appUsuario, "FIA 690", 4);
		verify(sistema, atLeastOnce()).agregarEstacionmiento(estacionamiento);
	}
}
