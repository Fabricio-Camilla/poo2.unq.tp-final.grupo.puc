package poo2.edu.unq.ar.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class AppDeUsuario implements MovementSensor {

	private String celular;
	private String patente;
	private Double credito;
	private ModoDeUso modo;
	private List<String> notificaciones;
	private IEstadoDeEstacionamiento estado;
	private SEM sem;
	private MovementSensor sensor;

	public AppDeUsuario(String celular, String patente) {
		this.setCelular(celular);
		this.setPatente(patente);
		this.modo = new ModoManual();
		this.credito = 0d;
		this.estado = new EstacionamientoNoVigente();
		this.sem = new SEM();
		this.notificaciones = new ArrayList<String>();
	}

	public void indicarFinDeEstacionamiento() {
		this.modo.finDeEstacionamiento(this);
	} 

	public void indicarInicioDeEstaciomiento() {
		this.modo.inicioDeEstacionamiento(this);
	}

	@Override
	public void driving() {
		this.estado.alertaFinEstacionamiento(this);
	}

	@Override
	public void walking() {
		this.estado.alertaInicioEstacionamiento(this);
	}
	
	public String getPatente() {
		return this.patente;
	}

	private void setCelular(String celular) {
		this.celular = celular;

	}

	private void setPatente(String patente) {
		this.patente = patente;

	}

	public double getCredito() {
		return this.credito;
	}

	public void cargarCredito(Double creditoACargar) {
		this.credito += creditoACargar;
	}

	public void cambiarModo(ModoDeUso modoDeUso) {
		this.modo = modoDeUso;
	}

	public ModoDeUso getModo() {
		return this.modo;
	}

	public IEstadoDeEstacionamiento getEstado() {
		return this.estado;
	}

	public void notificarFinEstacionamiento(String mensaje) {
		this.notificaciones.add(mensaje);
	}

	public void notificarInicioEstacionamiento(String mensaje) {
		this.notificaciones.add(mensaje);
	}

	public void setEstado(IEstadoDeEstacionamiento estado) {
		this.estado = estado;
	}
	public SEM getSEM() {
		return this.sem;
	}

	public void setSEM(SEM sem) {
		this.sem = sem;
	}

	public void cambiarAEstadoVigente(IEstadoDeEstacionamiento estacionamiento) {
		this.estado = estacionamiento;
	}

	public void cambiarAEstadoNoVigente(IEstadoDeEstacionamiento estacionamiento) {
		this.estado = estacionamiento;
	}

}