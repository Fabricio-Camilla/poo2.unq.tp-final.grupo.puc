package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {
	
	private int cantidadDeHoras;

	public EstacionamientoCompraPuntual(AppDeUsuario appUsuario, LocalTime horaInicio, String patente,
			int cantidadDeHoras) {
		super(patente, appUsuario, horaInicio, null);
		this.cantidadDeHoras = cantidadDeHoras;
	}

}