package poo2.edu.unq.ar.tpFinal;

public interface Observers {
	
	public void suscribir(EventoEstacionamiento eventoInteres, Notificable suscriptor);
	public void desuscribir (Notificable suscriptor);
	public void notificiar(EventoEstacionamiento eventoInteres);
	

}
