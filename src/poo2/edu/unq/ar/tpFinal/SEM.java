package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class SEM {
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private Set<AppDeUsuario> usuarios;
	private Set<PuntoDeVenta> puntosDeVenta;
	private Set<AppInspector> inspectores;
	private Set<Estacionamiento> estacionamientosRegistrados;
	private Set<Ticket> tickets;
	private Double montoPorHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Set<Infraccion> infraccionesRegistradas;

	public SEM() {

	}

	public SEM(Double montoPorHora, LocalTime horaInicio, LocalTime horaFin) {
		this.estacionamientosRegistrados = new HashSet<Estacionamiento>();
		this.usuarios = new HashSet<AppDeUsuario>();
		this.infraccionesRegistradas = new HashSet<Infraccion>();
		this.zonasDeEstacionamiento = new HashSet<ZonaDeEstacionamiento>();
		this.puntosDeVenta = new HashSet<PuntoDeVenta>();
		this.inspectores = new HashSet<AppInspector>();
		this.tickets = new HashSet<Ticket>();
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
		this.montoPorHora = montoPorHora;
	}

	public Set<ZonaDeEstacionamiento> getZonasDeEstacionamiento() {
		return this.zonasDeEstacionamiento;
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

		ZonaDeEstacionamiento zona = this.zonasDeEstacionamiento.stream().filter(ze -> ze.equals(zonaDeEstacionamiento))
				.findFirst().orElseThrow(() -> new Exception("No existe una zona de estacionamiento registrada"));
		zona.agregarPuntoDeVenta(puntoDeVenta);
		this.puntosDeVenta.add(puntoDeVenta);
	}

	public boolean tieneRegistradoElPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		return this.puntosDeVenta.contains(puntoDeVenta);
	}

	public boolean tieneRegistradosInspectores() {
		return !this.inspectores.isEmpty();
	}

	public void registrarUnNuevoEstacionamientoEnLaZona(Estacionamiento estacionamiento,
			ZonaDeEstacionamiento zonaEstacionamiento) throws Exception {

		ZonaDeEstacionamiento zona = this.getZonasDeEstacionamiento().stream()
				.filter(ze -> ze.equals(zonaEstacionamiento)).findFirst()
				.orElseThrow(() -> new Exception("No existe una zona de estacionamiento registrada"));
		this.getEstacionamientosRegistrados().add(estacionamiento);
		zona.registrarEstacionamiento(estacionamiento);
	}

	public boolean tieneRegistradoElEstacionamiento(Estacionamiento estacionamiento) {
		return this.estacionamientosRegistrados.contains(estacionamiento);
	}

	public ZonaDeEstacionamiento encontrarZonaEstacionamientoEn(Point localizacion) throws Exception {
		return this.getZonasDeEstacionamiento().stream().filter(z -> z.getLocalizacion().equals(localizacion))
				.findFirst().orElseThrow(() -> new Exception("No existe una zona de estacionamiento registrada"));
	}

	public boolean calcularSaldoSuficiente(AppDeUsuario usuario) {
		return this.montoACobrarPor(this.getMontoPorHora(), LocalTime.now(), this.getHoraFin()) <= usuario.getCredito();
	}

	public Double montoACobrarPor(Double montoPorHora, LocalTime now, LocalTime horaFin) {
		return montoPorHora * (horaFin.getHour() - now.getHour());
	}

	public Double getMontoPorHora() {
		return this.montoPorHora;
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public Set<AppDeUsuario> getUsuariosRegistrados() {
		return this.usuarios;
	}

	public void finalizarEstacionamiento(String celular) throws Exception {
		AppDeUsuario usuario = this.getUsuariosRegistrados().stream().filter(u -> u.getCelular().equals(celular))
				.findFirst().orElseThrow(() -> new Exception("Usuario no registrado"));
		Estacionamiento estacionamiento = this.getEstacionamientosRegistrados().stream()
				.filter(e -> e.getPatenteDeUsuario().equals(usuario.getPatente())).findFirst()
				.orElseThrow(() -> new Exception("No hay estacionamiento para el usuario"));
		ZonaDeEstacionamiento zona = this.getZonasDeEstacionamiento().stream()
				.filter(z -> z.estaRegistradoElEstacionamiento(estacionamiento)).toList().get(0);

		usuario.cobrarEstacionamiento(
				this.montoACobrarPor(this.getMontoPorHora(), estacionamiento.getHoraInicio(), LocalTime.now()));
		this.getEstacionamientosRegistrados().remove(estacionamiento);
		zona.getEstacionamientosRegistrados().remove(estacionamiento);

	}

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void cargarCredito(Double montoACargar, String celular) throws Exception {
		AppDeUsuario usuarioARecargar = this.getUsuariosRegistrados().stream()
				.filter(u -> u.getCelular().equals(celular)).findFirst()
				.orElseThrow(() -> new Exception("Usuario no registrado"));
		usuarioARecargar.cargarCredito(montoACargar);
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