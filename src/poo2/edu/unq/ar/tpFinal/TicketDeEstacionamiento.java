package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class TicketDeEstacionamiento extends Ticket{
	
	private int cantidadDeHoras;
	
	protected TicketDeEstacionamiento(int codigo, PuntoDeVenta puntoDeEmision, LocalTime fechaYHora, int cantidadDeHoras) {
		super(codigo, puntoDeEmision, fechaYHora);
		this.cantidadDeHoras = cantidadDeHoras;
	}
}
