package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class Estacionamiento {

	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Double precio;
	
	public Estacionamiento(LocalTime horaInicio, LocalTime horaFin, Double precio) {
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precio = precio;
	}

}
