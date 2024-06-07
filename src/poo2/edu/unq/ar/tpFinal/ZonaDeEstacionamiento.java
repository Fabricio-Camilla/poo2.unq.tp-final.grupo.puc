package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalTime;

public class ZonaDeEstacionamiento {

	private AppInspector inspector;
	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Double precioHora;
	private Point localizacion;

	public ZonaDeEstacionamiento(SEM sem, Point unaLocalizacion, AppInspector inspector, LocalTime horaInicio,
			LocalTime horaFin, Double precioHora) {
		this.inspector = inspector;
		this.sem = sem;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precioHora = precioHora;
		this.localizacion = unaLocalizacion;
	}

	public boolean seEncuentraUbicadaEnLaLocalizacion(Point localizacion) {
		return this.localizacion.equals(localizacion);
	}

}
