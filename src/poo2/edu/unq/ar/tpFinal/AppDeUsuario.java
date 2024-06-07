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
		this.sem.indicarFinEstacionamiento(this.patente);
	}

	public void indicarInicioDeEstaciomiento() {
		// pasaria la app entera pq con la patente no le podes preg el saldo para
		// validar
		this.sem.indicarInicioEstacionamiento(this.celular);
	}

	@Override
	public void driving() {
		this.estado.alertaFinEstacionamiento(this);
	}

	@Override
	public void walking() {
		this.estado.alertaInicioEstacionamiento(this);
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

}