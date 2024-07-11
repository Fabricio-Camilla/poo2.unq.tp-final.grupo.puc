package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstacionamientoViaApp extends Estacionamiento {

	private LocalDateTime horaFin;

	public EstacionamientoViaApp() {
		
	}
	
	public EstacionamientoViaApp(AppDeUsuario appUsuario, LocalDateTime horaInicio, LocalDateTime horaFin,
			String patente) {
		super(patente, appUsuario, horaInicio);
		this.horaFin = horaFin;
	}

	public LocalDateTime getHoraFin() {
		return this.horaFin;

	}

}