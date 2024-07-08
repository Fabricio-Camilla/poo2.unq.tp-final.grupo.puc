package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public abstract class Estacionamiento {

	protected LocalTime horaInicio;
	protected AppDeUsuario appUsuario;
	protected String patente;

	public Estacionamiento() {

	}

	protected Estacionamiento(String patente, AppDeUsuario appUsuario, LocalTime horaInicio) {
		this.horaInicio = horaInicio;
		this.appUsuario = appUsuario;
		this.patente = patente;
	}

	protected String getPatenteDeUsuario() {
		return this.patente;
	};

	protected LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	protected abstract boolean estaVigente();
		//no delegar en la app que el estacionamineto via app o compra puntal sepa responder calculando la hora
	
	protected abstract LocalTime getHoraFin();
	
	public boolean estaVigenteParaHorario(LocalTime hora) {
		return this.getHoraFin().isBefore(hora);
	}

	public void revisarVigenciaCon(AppInspector inspector, SEM sem) throws Exception {
		if (!sem.estaVigenteElEstacionamientoConPatente(this.getPatenteDeUsuario())) {
			inspector.notificarAlSemPorEstacionamientoNoVigente(this.getPatenteDeUsuario());
		}
	}

	protected AppDeUsuario getAppUsuario() {
		return this.appUsuario;
	}
}