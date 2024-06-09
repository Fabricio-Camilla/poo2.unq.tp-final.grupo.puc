package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppDeUsuario implements MovementSensor {

	private String celular;
	private String patente;
	private Double credito;
	private ModoDeUso modo;
	private List<String> notificaciones;
	private IEstadoDeEstacionamiento estado;
	private Point localizacion;
	private SEM sem;
	private MovementSensor sensor;

	public AppDeUsuario(String celular, String patente) {
		this.setCelular(celular);
		this.setPatente(patente);
		this.modo = new ModoManual();
		this.credito = 0d;
		this.estado = new EstacionamientoNoVigente();
		this.sem = new SEM(20d, LocalTime.of(20, 0),LocalTime.of(7, 0));
		this.notificaciones = new ArrayList<String>();
		this.localizacion = new Point(1,1); 
	}

	public void indicarFinDeEstacionamiento() throws Exception {
		this.modo.finDeEstacionamiento(this);
	} 

	public void indicarInicioDeEstaciomiento() throws Exception {
		this.modo.inicioDeEstacionamiento(this);
	}

	@Override
	public void driving() throws Exception {
		this.estado.alertaFinEstacionamiento(this);
	}

	@Override
	public void walking() throws Exception {
		this.estado.alertaInicioEstacionamiento(this);
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public String getCelular() {
		return this.celular;
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

	public Point getLocalizacion() {
		return this.localizacion;
	}

	public void cobrarEstacionamiento(Double montoACobrarPor) {
		this.credito -= montoACobrarPor;
		
	}

}