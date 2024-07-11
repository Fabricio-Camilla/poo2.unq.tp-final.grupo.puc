package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Estacionamiento {

	protected LocalDateTime horaInicio;
	protected AppDeUsuario appUsuario;
	protected String patente;

	public Estacionamiento() {

	}

	protected Estacionamiento(String patente, AppDeUsuario appUsuario, LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
		this.appUsuario = appUsuario;
		this.patente = patente;
	}
	
	protected boolean estaVigenteParaElDia() {
		return this.getHoraInicio().getDayOfMonth() == LocalDateTime.now().getDayOfMonth();
	}

	protected String getPatenteDeUsuario() {
		return this.patente;
	};

	protected LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}

	public boolean estaVigente() {
		return this.estaVigenteParaElDia()
				&& (this.getHoraFin().isAfter(LocalDateTime.now()) 
				&& this.estaVigenteParaHorario(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0))));
				
	}
	
	protected abstract LocalDateTime getHoraFin();
	
	public boolean estaVigenteParaHorario(LocalDateTime hora) {
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