package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class SEM implements Observers {
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private Set<AppDeUsuario> usuarios;
	private Set<PuntoDeVenta> puntosDeVenta;
	private Set<AppInspector> inspectores;
	private Set<Estacionamiento> estacionamientosRegistrados;
	private Set<Ticket> tickets;
	private Double montoPorHora;
	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	private Set<Infraccion> infraccionesRegistradas;
	private Set<Notificable> suscriptores;

	public SEM() {

	}

	// quedarnos con el historial de los estacionamientos
	public SEM(Double montoPorHora, LocalDateTime horaInicio, LocalDateTime horaFin) {
		this.estacionamientosRegistrados = new HashSet<Estacionamiento>();
		this.usuarios = new HashSet<AppDeUsuario>();
		this.infraccionesRegistradas = new HashSet<Infraccion>();
		this.zonasDeEstacionamiento = new HashSet<ZonaDeEstacionamiento>();
		this.puntosDeVenta = new HashSet<PuntoDeVenta>();
		this.inspectores = new HashSet<AppInspector>();
		this.tickets = new HashSet<Ticket>();
		this.suscriptores = new HashSet<Notificable>();
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
		this.montoPorHora = montoPorHora;
	}

	public void registrarAlUsuario(AppDeUsuario usuario) {
		this.usuarios.add(usuario);
	}

	public boolean tieneRegistradoAlUsuario(AppDeUsuario usuario) {
		return this.usuarios.contains(usuario);
	}

	public void registrarZonaDeEstacionamiento(ZonaDeEstacionamiento zonaEstacionamiento) {
		this.zonasDeEstacionamiento.add(zonaEstacionamiento);
		this.inspectores.add(zonaEstacionamiento.inspector());
	}

	public int cantidadDeUsuarioRegistrados() {
		return this.getUsuariosRegistrados().size();
	}

	public int cantidadDeZonasDeEstacionamiento() {
		return this.zonasDeEstacionamiento.size();
	}

	public void agregarPuntoDeVentaEnLaZonaDeEstacionamiento(PuntoDeVenta puntoDeVenta,
			ZonaDeEstacionamiento zonaDeEstacionamiento) throws Exception {

		zonaDeEstacionamiento.agregarPuntoDeVenta(puntoDeVenta);
		this.getPuntosDeVenta().add(puntoDeVenta);
	}

	private Set<PuntoDeVenta> getPuntosDeVenta() {
		return this.puntosDeVenta;
	}

	public boolean tieneRegistradoElPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		return this.puntosDeVenta.contains(puntoDeVenta);
	}

	public boolean tieneRegistradosInspectores() {
		return !this.inspectores.isEmpty();
	}

	public void registrarUnNuevoEstacionamientoEnLaZona(AppDeUsuario usuario, ZonaDeEstacionamiento zonaEstacionamiento)
			throws Exception {
		Estacionamiento estacionamiento = new EstacionamientoViaApp(usuario, LocalDateTime.now(),
				this.getHoraCierre(), usuario.getPatente()); 
		
		this.agregarEstacionmiento(estacionamiento);
		zonaEstacionamiento.registrarEstacionamiento(estacionamiento);
		this.notificiar(EventoEstacionamiento.InicioEstacionamiento);
	}

	public void agregarEstacionmiento(Estacionamiento estacionamiento) {
		this.getEstacionamientosRegistrados().add(estacionamiento);		
	}

	public boolean tieneRegistradoElEstacionamiento(Estacionamiento estacionamiento) {
		return this.estacionamientosRegistrados.contains(estacionamiento);
	}

	public ZonaDeEstacionamiento encontrarZonaEstacionamientoEn(Point localizacion) throws Exception{ 
		//this.getZonasDeEstacionamiento().stream().filter(z -> z.getLocalizacion().equals(localizacion))
		//.findFirst().orElseThrow(() -> new Exception("No existe una zona de estacionamiento registrada"));
		
		return new ZonaDeEstacionamiento(this, localizacion);
	}

	public boolean validarLocalizacionParaEstacionamiento(Point localizacion) {
		return true;
	}

	public boolean calcularSaldoSuficiente(AppDeUsuario usuario) {
		return this.montoACobrarPor(this.getMontoPorHora(), LocalDateTime.now(), this.getHoraFin()) <= usuario
				.getCredito();
	}

	public Double montoACobrarPor(Double montoPorHora, LocalDateTime now, LocalDateTime horaFin) {
		return montoPorHora * (horaFin.getHour() - now.getHour());
	}

	public Double getMontoPorHora() {
		return this.montoPorHora;
	}

	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}

	public LocalDateTime getHoraFin() {
		return this.horaFin;
	}

	public Set<AppDeUsuario> getUsuariosRegistrados() {
		return this.usuarios;
	}

	public void finalizarEstacionamiento(String celular) throws Exception {
		// encapsular filtros
		AppDeUsuario usuario = this.obtenerUsuarioPor(celular);
		Estacionamiento estacionamiento = this.obtenerEstacionamientoPor(usuario.getPatente());

		usuario.cobrarEstacionamiento(
				this.montoACobrarPor(this.getMontoPorHora(), estacionamiento.getHoraInicio(), LocalDateTime.now()));
		this.notificiar(EventoEstacionamiento.FinEstacionamiento);
	}

	public AppDeUsuario obtenerUsuarioPor(String celular) throws Exception {
		return this.getUsuariosRegistrados().stream().filter(u -> u.getCelular().equals(celular))
				.findFirst().orElseThrow(() -> new Exception("Usuario no registrado"));
	}

	public Estacionamiento obtenerEstacionamientoPor(String patente) throws Exception{
		return  this.getEstacionamientosRegistrados().stream()
				.filter(e -> e.getPatenteDeUsuario().equals(patente)).findFirst()
				.orElseThrow(() -> new Exception("No hay estacionamiento para el usuario"));
	}

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void cargarCredito(Double montoACargar, String celular) throws Exception {
		AppDeUsuario usuarioARecargar = this.getUsuariosRegistrados().stream()
				.filter(u -> u.getCelular().equals(celular)).findFirst()
				.orElseThrow(() -> new Exception("Usuario no registrado"));
		
		usuarioARecargar.cargarCredito(montoACargar);
		this.notificiar(EventoEstacionamiento.CargaDeSaldo);
	}

	public Set<Estacionamiento> getEstacionamientosRegistrados() {
		return this.estacionamientosRegistrados;
	}

	public void registrarTicket(Ticket ticket) {
		this.getTickets().add(ticket);
	}

	public int cantidadDeTickets() {
		return this.getTickets().size();
	}

	public boolean estaVigenteElEstacionamientoConPatente(String patente) throws Exception {
		Estacionamiento estacionamiento = this.getEstacionamientosRegistrados().stream()
				.filter(e -> e.getPatenteDeUsuario().equals(patente)).findFirst()
				.orElseThrow(() -> new Exception("No esta registrado el estacionamiento."));
		return estacionamiento.estaVigente();
	}

	public void registrarInfraccion(Infraccion infraccion) {
		this.getInfraccionesRegistradas().add(infraccion);
	}

	public int cantidadDeInfraccionesRegistradas() {
		return this.getInfraccionesRegistradas().size();
	}

	public Set<Infraccion> getInfraccionesRegistradas() {
		return this.infraccionesRegistradas;
	}

	public void finDeFranjaHoraria() {
		this.getUsuariosRegistrados().stream().forEach(usuario -> {
			try {
				usuario.indicarFinDeEstacionamiento();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override 
	public void suscribir(Notificable suscriptor) {
		this.getSuscriptores().add(suscriptor);
	}

	@Override
	public void desuscribir(Notificable suscriptor) {
		this.getSuscriptores().remove(suscriptor);
	}

	@Override
	public void notificiar(EventoEstacionamiento eventoInteres) {
		Stream<Notificable> interesados = this.getSuscriptores().stream()
				.filter(s -> s.getInteres().equals(eventoInteres));
		interesados.forEach(i -> i.update(eventoInteres));
	}

	public Set<Notificable> getSuscriptores() {
		return this.suscriptores;
	}

	public int cantidadDeSuscriptores() {
		return this.getSuscriptores().size();
	}

	public int cantidadDeEstacionamientosRegistrados() {
		return this.getEstacionamientosRegistrados().size();
	}
	
	
	public LocalDateTime getHoraCierre() {
		return LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 59));
	}

	// cada zona de estacionamiento tiene puntos de venta, deberían de agregarse
	// aparte

	// un usuario puede estacionar por app o por compra puntual
	// estacionamiento por compra puntual:
	// usuario estaciona el auto
	// usuario va a un punto de venta del estacionamiento
	// usuario pasa patente, cantidad de horas.
	// comerciante(punto de venta) ingresa datos al SEM en un ticket, es colaborador
	// interno el SEM (todos los conocen)
	// En esta modalidad el fin de estacionamiento queda clavado de una, si no tiene
	// credito lanza exception o no registra
}