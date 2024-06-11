package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestSEM {

	private SEM sem;
	private AppDeUsuario usuario;
	private ZonaDeEstacionamiento zonaEstacionamiento;
	private PuntoDeVenta puntoDeVenta;
	private Estacionamiento estacionamiento;
	private Infraccion infraccion;
	private EstacionamientoVigente vigente;

	@BeforeEach

	void setUp() {
		sem = new SEM(40d, LocalTime.of(7, 0), LocalTime.of(20, 0));
		usuario = mock(AppDeUsuario.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		estacionamiento = mock(EstacionamientoViaApp.class);
		infraccion = mock(Infraccion.class);
		vigente = mock(EstacionamientoVigente.class);
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
	void testElSemTieneUnMontoPorHora() {
		Double monto = 40d;
		assertEquals(sem.getMontoPorHora() , monto);
	}
	
	@Test
	void testElSemTieneUnaHoraDeAperturaYDeCierre() {
		LocalTime cierre= LocalTime.of(20, 0);
		LocalTime apertura= LocalTime.of(7, 0);
		
		assertEquals(sem.getHoraFin(), cierre);
		assertEquals(sem.getHoraInicio(), apertura);
	}
	
	@Test
	void testElSemCargaCreditoAUnUsuarioPorSuNumeroDeCelular() throws Exception {
		when(usuario.getCelular()).thenReturn("11224456");
		
		sem.registrarAlUsuario(usuario);
		sem.cargarCredito(20d, "11224456");
		
		verify(usuario, atLeastOnce()).cargarCredito(20d);
	}
	
	@Test
	void testElSemCalculaMontoACobrarPorEstacionar() {
		assertEquals(sem.montoACobrarPor(sem.getMontoPorHora(), LocalTime.of(13, 0), sem.getHoraFin()),(Double)280d);
	}
	
	@Test
	void testElSemIndicaSiUnUsuarioTieneCreditoSuficiente() {
		when(usuario.getCredito()).thenReturn(500d);
		
		assertTrue(sem.calcularSaldoSuficiente(usuario));
	}
	
	@Test
	void testElSemRegistraUnaNuevaInfraccion() {
		sem.registrarInfraccion(infraccion);
		assertEquals(sem.cantidadDeInfraccionesRegistradas(), 1);
	}
	
	@Test
	void testElSemIndicaSiUnEstacionamientoEstaVigente() throws Exception {
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD213GU");
		when(vigente.estaVigente()).thenReturn(true);
		when(usuario.getEstado()).thenReturn(vigente);
		when(usuario.estaVigente()).thenReturn(true);
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zonaEstacionamiento);
		
		assertTrue(sem.estaVigenteElEstacionamientoConPatente("AD213GU"));
	}
	
	@Test
	void testElSemFinalizaUnEstacionamientoDeUnCelular() throws Exception {
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		estacionamientos.add(estacionamiento);
		
		when(usuario.getCelular()).thenReturn("11223345");
		when(usuario.getPatente()).thenReturn("AD012TF");
		when(zonaEstacionamiento.estaRegistradoElEstacionamiento(estacionamiento)).thenReturn(true);
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD012TF");
		when(estacionamiento.getHoraInicio()).thenReturn(LocalTime.now());
		when(zonaEstacionamiento.getEstacionamientosRegistrados()).thenReturn(estacionamientos);
		
		sem.registrarAlUsuario(usuario);
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zonaEstacionamiento);
		
		sem.finalizarEstacionamiento("11223345");
		
		assertFalse(sem.getEstacionamientosRegistrados().contains(estacionamiento));
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
	void testElSemBuscaUnaZonaDeEstacionamientoEspecifica() throws Exception {
		Point localizacion = new Point(1,1);
		when(zonaEstacionamiento.getLocalizacion()).thenReturn(localizacion);
		
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		
		assertEquals(sem.encontrarZonaEstacionamientoEn(localizacion), zonaEstacionamiento);
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
		}, "No existe una zona de estacionamiento registrada");
	}

	@Test
	void testCuandoElSemRegistraUnaZonaDeEstacionamientoRegistraAlInspectorQueContieneLaZona() {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		assertTrue(sem.tieneRegistradosInspectores());
		verify(zonaEstacionamiento, atLeastOnce()).inspector();
	}

	@Test
	void testElSemRegistraUnNuevoEstacionamiento() throws Exception {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zonaEstacionamiento);
		verify(zonaEstacionamiento, atLeastOnce()).registrarEstacionamiento(estacionamiento);
		assertTrue(sem.tieneRegistradoElEstacionamiento(estacionamiento));
	}

	@Test
	void testElSemNoPuedeRegistrarUnEstacionamientoSiNoSeEncuentraLaZonaDeEstacionamientoRegistrada() throws Exception {
		assertThrows(Exception.class, () -> {
			sem.registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zonaEstacionamiento);
		}, "No existe una zona de estacionamiento registrada");
	}
	
	@Test
	void testElSemRegistraTicketsYaSeaDeCargasDeCreditoODeEstacionamientos() {
		TicketDeEstacionamiento ticketDeEstacionamiento = mock(TicketDeEstacionamiento.class);
		TicketDeRecargaCredito ticketDeRecarga = mock(TicketDeRecargaCredito.class);
		sem.registrarTicket(ticketDeRecarga);
		sem.registrarTicket(ticketDeEstacionamiento);
		assertEquals(sem.cantidadDeTickets(), 2);
	}
	
	
}
