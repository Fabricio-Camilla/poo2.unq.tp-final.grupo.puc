package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;
		
public class TicketDeRecargaCredito extends Ticket{

	private Double montoARecargar;
	private String celular;

	protected TicketDeRecargaCredito(int codigo, PuntoDeVenta puntoDeEmision, LocalTime fechaYHora, Double montoARecargar, String celular) {
		super(codigo, puntoDeEmision, fechaYHora);
		this.montoARecargar = montoARecargar;
		this.celular = celular;
	}
	
}
