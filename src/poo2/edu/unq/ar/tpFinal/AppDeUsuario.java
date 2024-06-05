package poo2.edu.unq.ar.tpFinal;

public class AppDeUsuario {
	
	private String celular;
	private String patente;
	private double credito;
	private ModoDeUso modo;
	
	public AppDeUsuario(String celular, String patente) {
		this.setCelular(celular);
		this.setPatente(patente);
		this.modo = new ModoManual();
		this.credito = 0; 
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

	public void cargarCredito(double creditoACargar) {
		this.credito += creditoACargar;
	}

	public void cambiarModo(ModoDeUso modoDeUso) {
		this.modo = modoDeUso;
		
	}

	public ModoDeUso getModo() {
		return this.modo;
	}
}