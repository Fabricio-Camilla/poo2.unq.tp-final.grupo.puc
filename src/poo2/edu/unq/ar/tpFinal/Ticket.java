package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public abstract class Ticket {
	
	protected int codigo;
	protected PuntoDeVenta puntoDeEmision;
	protected LocalTime fechaYHora;
	
	protected Ticket(int codigo, PuntoDeVenta puntoDeEmision, LocalTime fechaYHora) {
		this.codigo = codigo;
		this.puntoDeEmision = puntoDeEmision;
		this.fechaYHora = fechaYHora;
	}
}
