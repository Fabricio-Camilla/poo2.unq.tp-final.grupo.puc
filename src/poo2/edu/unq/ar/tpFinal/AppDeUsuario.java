package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
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
	private boolean desplazamiento;

	public AppDeUsuario() {
		
	}
	public AppDeUsuario(String celular, String patente, SEM sem) {
		this.setCelular(celular);
		this.setPatente(patente);
		this.modo = new ModoManual();
		this.credito = 0d;
		this.estado = new EstacionamientoNoVigente();
		this.sem = sem;
		this.notificaciones = new ArrayList<String>();
		this.localizacion = new Point(1, 1);
		this.desplazamiento = false;
	}
	
	public void activarModoDesplazamiento() {
		this.desplazamiento = true;
	}
	
	public void desactivarModoDesplazamiento() {
		this.desplazamiento = false;
	}
	
// si tiene el modo de desplazamiento si esta activado driving y walking deelga en el modo, desactivado manual solo avisa, automatico avisa y inicio estacionamiento
	public void indicarFinDeEstacionamiento() throws Exception {
		if (this.getModoDesplazamiento()) {
			this.modo.finDeEstacionamiento(this);			
		}
	}

	public void indicarInicioDeEstaciomiento() throws Exception {
		if (this.getModoDesplazamiento()) {
			this.modo.inicioDeEstacionamiento(this);
		}
	}
	//asumiendo que siempre se ejectuta en modo automatico
	@Override
	public void driving() throws Exception {
		//dentro del modo delegar en el estado
		//this.estado.alertaFinEstacionamiento(this);
		this.modo.finDeEstacionamiento(this);
	}

	@Override
	public void walking() throws Exception {
		//dentro del modo delegar en el estado 
		this.estado.alertaInicioEstacionamiento(this);
		//this.modo.inicioDeEstacionamiento(this);
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

	public List<String> getNotificaciones() {
		return this.notificaciones;
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

	public Point getLocalizacion() {
		return this.localizacion;
	}

	public void cobrarEstacionamiento(Double montoACobrarPor) {
		this.credito -= montoACobrarPor;
	}

	public boolean estaVigente() {
		return this.getEstado().estaVigente();
	}
	public void cambiarAEstadoVigente() {
		this.setEstado(new EstacionamientoVigente());
	}
	
	public void cambiarAEstadoNoVigente() {
		this.setEstado(new EstacionamientoNoVigente());
	}

	public boolean getModoDesplazamiento() {
		return this.desplazamiento;
	}
	
	public void realizarAlertaFinEstacionamiento() throws Exception {
		//Desacoplar del estado y que lo realice el modo automatico
		this.getEstado().alertaFinEstacionamiento(this.getSEM(), this.getCelular());	
	}
	
	public void realizarAlertaInicioEstacionamiento() throws Exception {
		//Desacoplar del estado y que lo realice el modo automatico
		this.getEstado().alertaInicioEstacionamiento(this);	 
	}
	
	public boolean cumpleConSaldoParaPagar() {
		return this.getSEM().calcularSaldoSuficiente(this);
	}
	
	public void iniciarEstacionamiento(Estacionamiento estacionamiento, ZonaDeEstacionamiento zona) throws Exception {
		this.getSEM().registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona); 
	}

}