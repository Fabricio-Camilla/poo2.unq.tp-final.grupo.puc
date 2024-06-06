package poo2.edu.unq.ar.tpFinal;

public class AppDeUsuario {
	
	private String celular;
	private String patente;
	private Double credito;
	private ModoDeUso modo;
	private IEstadoDeEstacionamiento estado;
	//private MovementSensor sensor;
	
	public AppDeUsuario(String celular, String patente) {
		this.setCelular(celular);
		this.setPatente(patente);
		this.modo = new ModoManual();
		this.credito = 0d; 
		this.estado = new EstacionamientoNoVigente();
	}
	
	/*public void indicarFinDeEstacionamiento() {
			this.estado.finalizarEstacionamiento(this.patente, this.sem);
	}
	
	public void indicarInicioDeEstaciomiento(){
	* pasaria la app entera pq con la patente no le podes preg el saldo para validar
			this.estado.iniciarEstacionamiento(this.celular, this.sem);
	}
	@override
	public void driving(){
		if(this.estado.estaVigente()){
		*con alerta
			this.estado.alertaFinInicioEstacionamiento(this.celular,this.sem);
			}
	}
	
	@override
	public void walking(){
		if(this.estado.estaVigente(){
			this.driving()
			}
		}
	
	*/
	
	
	
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
}