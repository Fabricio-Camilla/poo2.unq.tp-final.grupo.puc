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

	protected boolean estaVigente() {
		return this.estaVigenteParaElDia() && this.estaVigenteParaHorario(LocalDateTime.now())
				|| this.estaVigenteParaHorario(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)));
	}

	public LocalDateTime getHoraFin() {
		return this.getHoraInicio().plusHours(this.getCantidadDeHoras());
	}

	private int getCantidadDeHoras() {

		return this.cantidadDeHoras;
	}

}