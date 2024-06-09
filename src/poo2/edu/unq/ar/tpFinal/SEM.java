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
	private Double montoxHora;
	private LocalTime horaInicio;
	private LocalTime horaFin;

	public SEM(Double montoxHora, LocalTime horaInicio, LocalTime horaFin) {
		this.estacionamientosRegistrados = new HashSet<Estacionamiento>();
		this.usuarios = new HashSet<AppDeUsuario>();
		this.zonasDeEstacionamiento = new HashSet<ZonaDeEstacionamiento>();
		this.puntosDeVenta = new HashSet<PuntoDeVenta>();
		this.inspectores = new HashSet<AppInspector>();
		this.tickets = new HashSet<Ticket>();
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
		this.montoxHora = montoxHora;
	}
	
	public Set<ZonaDeEstacionamiento> getZonasDeEstacionamiento(){
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
		return this.usuarios.size();
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

	public void indicarFinEstacionamiento(AppDeUsuario appDeUsuario) {
		// en base al celular asociado a la app se finaliza el estacionamiento
	}

	public void indicarInicioEstacionamiento(AppDeUsuario appDeUsuario) {
		// hay que chekiar que la app tenga credito para iniciar el estacionamiento

	}

	public void registrarUnNuevoEstacionamientoEnLaZona(Estacionamiento estacionamiento,
			ZonaDeEstacionamiento zonaEstacionamiento) throws Exception {

		ZonaDeEstacionamiento zona = this.zonasDeEstacionamiento.stream().filter(ze -> ze.equals(zonaEstacionamiento))
				.findFirst().orElseThrow(() -> new Exception("No existe una zona de estacionamiento registrada"));
		this.estacionamientosRegistrados.add(estacionamiento);
		zona.registrarEstacionamiento(estacionamiento);
	}

	public boolean tieneRegistradoElEstacionamiento(Estacionamiento estacionamiento) {
		return this.estacionamientosRegistrados.contains(estacionamiento);
	}

	public ZonaDeEstacionamiento encontrarZonaEstacionamientoEn(Point localizacion) throws Exception{
		return this.zonasDeEstacionamiento.stream()
				.filter(z -> z.getLocalizacion().equals(localizacion))
				.findFirst().orElseThrow(() -> new Exception("No hay zonas de estacionamiento registradas"));
	}

	public boolean calcularSaldoSuficiente(AppDeUsuario usuario) {
		return this.montoACobrarPor(this.getMontoxHora(), LocalTime.now(),  this.getHoraFin()) <= usuario.getCredito();
	}

	private Double montoACobrarPor(Double montoxHora2, LocalTime now, LocalTime horaFin2) {
		return montoxHora2 *( now.getHour() - horaFin2.getHour());
	}

	public Double getMontoxHora() {
		return this.montoxHora;
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public void finalizarEstacionamiento(String celular) throws Exception {
		AppDeUsuario usuario = this.usuarios.stream().filter(u -> u.getCelular().equals(celular)).findFirst().orElseThrow(() -> new Exception("Usuario no registrado"));
		Estacionamiento estacionamiento = this.estacionamientosRegistrados.stream().filter(e -> e.getPatenteDeUsuario().equals(usuario.getPatente())).findFirst().orElseThrow(() -> new Exception("No hay estacionamiento para el usuario"));
		ZonaDeEstacionamiento zona = this.zonasDeEstacionamiento.stream().filter(z -> z.getEstacionamientosRegistrados().equals(estacionamiento)).toList().get(0);
		

		usuario.cobrarEstacionamiento(this.montoACobrarPor(getMontoxHora(), estacionamiento.getHoraInicio(), LocalTime.now()));
		this.estacionamientosRegistrados.remove(estacionamiento);
		zona.getEstacionamientosRegistrados().remove(estacionamiento);
	
	}

	public Set<Ticket> getTickets() {
		return this.tickets;
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