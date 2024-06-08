package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class ZonaDeEstacionamiento {

	private AppInspector inspector;
	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Double precioHora;
	private Point localizacion;
	private Set<PuntoDeVenta> puntosDeVenta;
	private Set<Estacionamiento> estacionamientos;

	public ZonaDeEstacionamiento(SEM sem, Point unaLocalizacion, LocalTime horaInicio, LocalTime horaFin,
			Double precioHora) {
		/*
		 * Se toma la decisión de inicializar un inspector cuando se crea la Zona de
		 * Estacionamiento para no tener inconsistencias en los colaboradores internos u
		 * objetos incompletos. Ya que la zona de estacionamiento tiene un inspector y
		 * un inspector tiene una zona de estacionamiento, entonces existe una
		 * dependencia entre ambos. Al considerarse instancia a la ZonaDeEstacionamiento
		 * dentro de este constructor aseguramos la correcta inicialización de ambos
		 * objetos
		 */
		this.inspector = new AppInspector(this, sem);
		this.puntosDeVenta = new HashSet<PuntoDeVenta>();
		this.estacionamientos = new HashSet<Estacionamiento>();
		this.sem = sem;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precioHora = precioHora;
		this.localizacion = unaLocalizacion;
	}

	public boolean seEncuentraUbicadaEnLaLocalizacion(Point localizacion) {
		return this.localizacion.equals(localizacion);
	}

	public boolean tieneAsignadoUnInspector() {
		return this.inspector().estaAsignadoALaZonaDeEstacionamiento(this);
	}

	public void agregarPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		this.puntosDeVenta.add(puntoDeVenta);
	}

	public int cantidadDePuntosDeVenta() {
		return this.puntosDeVenta.size();
	}

	public AppInspector inspector() {
		return this.inspector;
	}

	public boolean estaRegistradoElEstacionamiento(Estacionamiento estacionamiento) {
		return this.estacionamientos.contains(estacionamiento);
	}

	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
	}

}
