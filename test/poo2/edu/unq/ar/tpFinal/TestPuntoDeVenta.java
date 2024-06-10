package poo2.edu.unq.ar.tpFinal;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPuntoDeVenta {
	
	private SEM sistema;
	private PuntoDeVenta puntoDeVenta;
	private ZonaDeEstacionamiento zona;
	
	@BeforeEach
	void setUp() {
		sistema = mock(SEM.class);
		zona = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = new PuntoDeVenta(sistema, zona);
	}
	
	@Test
	void testEnUnPuntoDeVentaUnUsuarioPuedeCargarCredito() {
		puntoDeVenta.cargarCredito(20d, "0123456789");
		verify(sistema, atLeastOnce()).cargarCredito(20d, "0123456789");
	}
	
	@Test
	void testUnPuntoDeVentaCreaYRegistraUnEstacionamientoDeCompraPuntual() throws Exception {
		EstacionamientoCompraPuntual estacionamiento = this.puntoDeVenta.registarEstacionamiento("FIA 690", 4);
		verify(sistema, atLeastOnce()).registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona);
	}
}
