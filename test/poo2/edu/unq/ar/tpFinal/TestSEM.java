package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private Notificable suscriptor;
	private AppDeUsuario usuario2;

	@BeforeEach

	void setUp() {
		sem = new SEM(40d, LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0)),
				LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
		usuario = spy(AppDeUsuario.class);
		usuario2 = mock(AppDeUsuario.class);
		zonaEstacionamiento = mock(ZonaDeEstacionamiento.class);
		puntoDeVenta = mock(PuntoDeVenta.class);
		estacionamiento = spy(EstacionamientoViaApp.class);
		infraccion = mock(Infraccion.class);
		vigente = mock(EstacionamientoVigente.class);
		suscriptor = spy(Notificable.class);
	}

	@Test
	void testElSemDevuelveQueUnaLocalizacionEsValida() {
		assertTrue(sem.validarLocalizacionParaEstacionamiento(new Point(1, 1)));
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
		assertEquals(sem.getMontoPorHora(), monto);
	}

	@Test
	void testElSemTieneUnaHoraDeAperturaYDeCierre() {
		LocalDateTime cierre = LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0));
		LocalDateTime apertura = LocalDateTime.of(LocalDate.now(), LocalTime.of(7, 0));

		assertEquals(sem.getHoraFin(), cierre);
		assertEquals(sem.getHoraInicio(), apertura);
	}

	@Test
	void testElSemCargaCreditoAUnUsuarioPorSuNumeroDeCelular() throws Exception {
		when(usuario2.getCelular()).thenReturn("11224456");
		
		sem.registrarAlUsuario(usuario2);
		sem.cargarCredito(20d, "11224456");
		
		verify(usuario2, atLeastOnce()).cargarCredito(20d);
	}

	@Test
	void testElSemCalculaMontoACobrarPorEstacionar() {
		assertEquals(sem.montoACobrarPor(sem.getMontoPorHora(), LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 0)),
				sem.getHoraFin()), (Double) 280d);
	}

	@Test
	void testElSemIndicaQueUnUsuarioTieneCreditoSuficiente() {
		when(usuario2.getCredito()).thenReturn(500d);
		
		assertTrue(sem.calcularSaldoSuficiente(usuario2));
	}

	@Test
	void testElSemIndicaQueUnUsuarioNoTieneCreditoSuficiente() {
		when(usuario2.getCredito()).thenReturn(-500d);
		
		assertFalse(sem.calcularSaldoSuficiente(usuario2));
	}

	@Test
	void testElSemRegistraUnaNuevaInfraccion() {
		sem.registrarInfraccion(infraccion);
		assertEquals(sem.cantidadDeInfraccionesRegistradas(), 1);
	}

	@Test
	void testErrorNoSeEncuentraRegistradoUnEstacionamientoParaPreguntarVigenciaSobreElMismo() throws Exception {
		assertThrows(Exception.class, () -> {
			sem.estaVigenteElEstacionamientoConPatente("AF200FA");
		}, "No esta registrado el estacionamiento.");
	}

	@Test
	void testElSemIndicaQueUnEstacionamientoEstaVigente() throws Exception {
		
		when(usuario.getPatente()).thenReturn("AD213GU");
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD213GU");
		when(estacionamiento.getHoraInicio()).thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(14, 0)));
		when(estacionamiento.getHoraFin()).thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(15, 0)));
		when(estacionamiento.estaVigente()).thenReturn(true);
		
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario, zonaEstacionamiento);
		
		assertTrue(sem.estaVigenteElEstacionamientoConPatente("AD213GU"));
	}

	@Test
	void testElSemIndicaQueUnEstacionamientoNoEstaVigente() throws Exception {
		//EstacionamientoNoVigente noVigente = mock(EstacionamientoNoVigente.class);

		when(usuario.getPatente()).thenReturn("AD213GU");
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD213GU");
		when(estacionamiento.getHoraInicio()).thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(14, 0)));
		when(estacionamiento.getHoraFin()).thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(15, 0)));
		when(estacionamiento.estaVigente()).thenReturn(false);
		
		sem.agregarEstacionmiento(estacionamiento);
		
		assertFalse(sem.estaVigenteElEstacionamientoConPatente("AD213GU"));
	}

	@Test
	void testElSemFinalizaUnEstacionamientoDeUnCelular() throws Exception {
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		estacionamientos.add(estacionamiento);

		when(estacionamiento.getHoraInicio()).thenReturn(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		when(usuario2.getCelular()).thenReturn("11223345");
		when(usuario2.getPatente()).thenReturn("AD012TF");
		when(zonaEstacionamiento.estaRegistradoElEstacionamiento(estacionamiento)).thenReturn(true);
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD012TF");
		when(zonaEstacionamiento.getEstacionamientosRegistrados()).thenReturn(estacionamientos);

		sem.registrarAlUsuario(usuario2);
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario2, zonaEstacionamiento);

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
	void testErrorAlFinalizarUnEstacionamientoConUnEstacionamientoNoRegistrado() {
		when(usuario.getCelular()).thenReturn("115952323");
		sem.registrarAlUsuario(usuario);
		assertThrows(Exception.class, () -> {
			sem.finalizarEstacionamiento("115952323");
		}, "No hay estacionamiento para el usuario");
	}

	@Test
	void testErrorAlFinalizarUnEstacionamientoConUnUsuarioNoRegistrado() {
		assertThrows(Exception.class, () -> {
			sem.finalizarEstacionamiento("9999999");
		}, "Usuario no registrado");
	}

	@Test
	void testErrorCargaDeCreditoAUnUsuarioNoRegistrado() {
		assertThrows(Exception.class, () -> {
			sem.cargarCredito(40d, "9999999");
		}, "Usuario no registrado");
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
	void testCuandoElSemRegistraUnaZonaDeEstacionamientoRegistraAlInspectorQueContieneLaZona() {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		assertTrue(sem.tieneRegistradosInspectores());
		verify(zonaEstacionamiento, atLeastOnce()).inspector();
	}

	@Test
	void testCuandoElSemEsCreadoNoTieneInspectoresRegistrados() {
		assertFalse(sem.tieneRegistradosInspectores());
	}

	@Test
	void testElSemRegistraUnNuevoEstacionamiento() throws Exception {
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario, zonaEstacionamiento);

		// verify(zonaEstacionamiento,
		// atLeastOnce()).registrarEstacionamiento(estacionamiento);
		assertEquals(sem.cantidadDeEstacionamientosRegistrados(), 1);
		// assertTrue(sem.tieneRegistradoElEstacionamiento(estacionamiento));
	}

	@Test
	void testElSemRegistraUnTicketDeEstacionamiento() {
		TicketDeEstacionamiento ticketDeEstacionamiento = mock(TicketDeEstacionamiento.class);
		sem.registrarTicket(ticketDeEstacionamiento);
		assertEquals(sem.cantidadDeTickets(), 1);
	}

	@Test
	void testElSemRegistraUnTicketDeCargaDeCredito() {
		TicketDeRecargaCredito ticketDeRecarga = mock(TicketDeRecargaCredito.class);
		sem.registrarTicket(ticketDeRecarga);
		assertEquals(sem.cantidadDeTickets(), 1);
	}

	@Test
	void testElSemFinalizaTodosLosEstacionamientosAlFinDeLaFranjaHoraria() throws Exception{
		when(usuario.getEstado()).thenReturn(vigente);
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario, zonaEstacionamiento);
		sem.finDeFranjaHoraria();
	}

	@Test
	void testElSemNotificaALosSuscriptoresPorInicioDeEstacionamiento() throws Exception {
		when(suscriptor.getInteres()).thenReturn(EventoEstacionamiento.InicioEstacionamiento);
		
		sem.registrarZonaDeEstacionamiento(zonaEstacionamiento);
		sem.suscribir(suscriptor);
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario, zonaEstacionamiento);
		
		verify(suscriptor, atLeastOnce()).update(EventoEstacionamiento.InicioEstacionamiento);
	}

	@Test
	void testElSemNotificaALosSuscriptoresPorFinDeEstacionamiento() throws Exception {
		// AppDeUsuario usuario2 = mock(AppDeUsuario.class);
		Set<Estacionamiento> estacionamientos = new HashSet<Estacionamiento>();
		estacionamientos.add(estacionamiento);

		when(suscriptor.getInteres()).thenReturn(EventoEstacionamiento.FinEstacionamiento);
		when(estacionamiento.getHoraInicio())
				.thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(14, 0)));
		when(estacionamiento.getHoraFin())
				.thenReturn(LocalDateTime.of(LocalDate.of(2024, 10, 20), LocalTime.of(15, 0)));
		when(usuario2.getCelular()).thenReturn("11223456");
		when(usuario2.getPatente()).thenReturn("AD012TF");
		when(usuario2.getCredito()).thenReturn(1000d);
		when(estacionamiento.getPatenteDeUsuario()).thenReturn("AD012TF");
		when(zonaEstacionamiento.getEstacionamientosRegistrados()).thenReturn(estacionamientos);

		sem.registrarAlUsuario(usuario2);
		sem.suscribir(suscriptor);
		sem.registrarUnNuevoEstacionamientoEnLaZona(usuario2, zonaEstacionamiento);

		sem.finalizarEstacionamiento("11223456");

		verify(suscriptor, atLeastOnce()).update(EventoEstacionamiento.FinEstacionamiento);
	}

	@Test
	void testElSemNotificaALosSuscriptoresPorRecargaDeCredito() throws Exception {
		//AppDeUsuario usuario2 = mock(AppDeUsuario.class);
		when(suscriptor.getInteres()).thenReturn(EventoEstacionamiento.CargaDeSaldo);
		when(usuario2.getCelular()).thenReturn("11224456");
		
		sem.registrarAlUsuario(usuario2);
		sem.suscribir(suscriptor);
		sem.cargarCredito(20d, "11224456");
				
		verify(suscriptor, atLeastOnce()).update(EventoEstacionamiento.CargaDeSaldo);
	}

	@Test
	void testElSemSuscribeAUnNotificable() {
		sem.suscribir(suscriptor);

		assertEquals(sem.cantidadDeSuscriptores(), 1);
	}

	@Test
	void testElSemDesuscribeAUnNotificable() {
		sem.suscribir(suscriptor);
		sem.desuscribir(suscriptor);

		assertTrue(sem.getSuscriptores().isEmpty());
	}
}
