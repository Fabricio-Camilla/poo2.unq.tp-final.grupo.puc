package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoViaApp extends Estacionamiento {

	public EstacionamientoViaApp(AppDeUsuario appUsuario, LocalTime horaInicio, LocalTime horaFin, String patente) {
		super(patente, appUsuario, horaInicio, horaFin);
	}

	public String getPatenteDeUsuario() {
		return this.patente;
	}
}