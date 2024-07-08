package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoCompraPuntual extends Estacionamiento {

	private int cantidadDeHoras;

	public EstacionamientoCompraPuntual(AppDeUsuario appUsuario, LocalTime horaInicio, String patente,
			int cantidadDeHoras) {
		super(patente, appUsuario, horaInicio);
		this.cantidadDeHoras = cantidadDeHoras;
	}

	protected boolean estaVigente() {
		return this.estaVigenteParaHorario(LocalTime.now()) || this.estaVigenteParaHorario(LocalTime.of(20, 0));
	}

	public LocalTime getHoraFin() {
		return this.getHoraInicio().plusHours(this.getCantidadDeHoras());
	}

	private int getCantidadDeHoras() {

		return this.cantidadDeHoras;
	}

}