package poo2.edu.unq.ar.tpFinal;

public abstract class Notificable {

	private String interes;

	public String getInteres() {
		return this.interes;
	}

	protected abstract void update(String eventoInteres);
}
