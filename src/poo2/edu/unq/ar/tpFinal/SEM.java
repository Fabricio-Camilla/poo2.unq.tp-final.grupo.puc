package poo2.edu.unq.ar.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class SEM {
	private List<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private List<AppDeUsuario> usuarios;
	private List<PuntoDeVenta> puntosDeVenta;
	private List<AppInspector> inspectores;
	private List<Estacionamiento> estacionamientosRegistrados;

	public SEM() {
		this.estacionamientosRegistrados = new ArrayList<Estacionamiento>();
		this.usuarios = new ArrayList<AppDeUsuario>();
		this.zonasDeEstacionamiento = new ArrayList<ZonaDeEstacionamiento>();
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
		this.inspectores = new ArrayList<AppInspector>();
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

		ZonaDeEstacionamiento zona = this.zonasDeEstacionamiento.stream().filter(ze -> ze == zonaDeEstacionamiento)
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
