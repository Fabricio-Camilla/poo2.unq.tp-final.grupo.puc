package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public abstract class Estacionamiento {

	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	protected AppDeUsuario appUsuario;
	protected String patente;

	public Estacionamiento () {
		
	}
	protected Estacionamiento(String patente, AppDeUsuario appUsuario, LocalTime horaInicio, LocalTime horaFin) {
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.appUsuario = appUsuario;
		this.patente = patente;
	}

	protected abstract String getPatenteDeUsuario();

	protected LocalTime getHoraInicio() {
		return this.horaFin;
	}

	protected boolean estaVigente() {
		return this.getAppUsuario().estaVigente();
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