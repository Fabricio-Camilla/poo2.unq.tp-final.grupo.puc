package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoViaApp extends Estacionamiento {

	private LocalTime horaFin;
	
	public EstacionamientoViaApp(AppDeUsuario appUsuario, LocalTime horaInicio, LocalTime horaFin, String patente) {
		super(patente, appUsuario, horaInicio);
		this.horaFin = horaFin;
	}

}