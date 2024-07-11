package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {

	private int cantidadDeHoras;

	public EstacionamientoCompraPuntual(AppDeUsuario appUsuario, LocalDateTime horaInicio, String patente,
			int cantidadDeHoras) {
		super(patente, appUsuario, horaInicio);
		this.cantidadDeHoras = cantidadDeHoras;
	}

	public LocalDateTime getHoraFin() {
		return this.getHoraInicio().plusHours(this.getCantidadDeHoras());
	}

	public int getCantidadDeHoras() {

		return this.cantidadDeHoras;
	}

}