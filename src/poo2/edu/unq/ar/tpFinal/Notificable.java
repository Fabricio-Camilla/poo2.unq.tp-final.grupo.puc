package poo2.edu.unq.ar.tpFinal;

public abstract class Notificable {

	private EventoEstacionamiento interes;

	public EventoEstacionamiento getInteres() {
		return this.interes;
	}

	protected abstract void update(EventoEstacionamiento eventoInteres);
}
