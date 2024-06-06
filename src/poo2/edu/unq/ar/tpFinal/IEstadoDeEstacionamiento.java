package poo2.edu.unq.ar.tpFinal;

public interface IEstadoDeEstacionamiento {
	
	public void iniciarEstacionamiento(String patente);
	public void finalizarEstacionamiento(String celular);
	public boolean estaVigente();
}
