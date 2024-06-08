package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {

	private int patente;
	private int cantidadDeHoras;

	public EstacionamientoCompraPuntual(LocalTime horaInicio, LocalTime horaFin, Double precio, int patente,
			int cantidadDeHoras) {
		super(horaInicio, horaFin, precio);
		this.patente = patente;
		this.cantidadDeHoras = cantidadDeHoras;
	}

}
