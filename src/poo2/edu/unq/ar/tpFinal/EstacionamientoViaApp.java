package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoViaApp extends Estacionamiento {
	
	
	private String celular;
	
	public EstacionamientoViaApp(LocalTime horaInicio, LocalTime horaFin, Double precio, String celular) {
		super(horaInicio, horaFin, precio);
		this.celular = celular;
	}
	
}
