package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDateTime;

public abstract class Ticket {
	
	protected int codigo;
	protected PuntoDeVenta puntoDeEmision;
	protected LocalDateTime fechaYHora;
	
	protected Ticket(int codigo, PuntoDeVenta puntoDeEmision, LocalDateTime fechaYHora) {
		this.codigo = codigo;
		this.puntoDeEmision = puntoDeEmision;
		this.fechaYHora = fechaYHora;
	}
}
