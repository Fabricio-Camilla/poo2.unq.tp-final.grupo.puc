package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDateTime;

public class PuntoDeVenta {

	private SEM sistema;
	private ZonaDeEstacionamiento zona;

	public PuntoDeVenta(SEM sistema, ZonaDeEstacionamiento zona) {
		this.sistema = sistema;
		this.zona = zona;
	}

	public void cargarCredito(Double montoACargar, String celular) throws Exception {
		TicketDeRecargaCredito ticket = new TicketDeRecargaCredito(125, this, LocalDateTime.now(), montoACargar, celular);
		this.solicitarCargaDeCredito(montoACargar, celular);
		this.enviarTicket(ticket);
	}


	public Estacionamiento registarEstacionamiento(AppDeUsuario appUsuario, String patente,
			int cantidadDeHoras) throws Exception {
		EstacionamientoCompraPuntual estacionamientoARegistrar = new EstacionamientoCompraPuntual(appUsuario,
				LocalDateTime.now(), patente, cantidadDeHoras);
		TicketDeEstacionamiento ticket = new TicketDeEstacionamiento(1205, this, LocalDateTime.now(), cantidadDeHoras);
		this.enviarCompraDeEstacionamiento(estacionamientoARegistrar);
		this.enviarTicket(ticket);
		return estacionamientoARegistrar;
	}

	public  void enviarTicket(Ticket ticket) {
		this.getSem().registrarTicket(ticket);		
	}
	
	public void solicitarCargaDeCredito(Double montoACargar, String celular) throws Exception {
		this.getSem().cargarCredito(montoACargar, celular);
	}

	public void enviarCompraDeEstacionamiento(EstacionamientoCompraPuntual estacionamientoARegistrar) {
		this.getSem().agregarEstacionmiento(estacionamientoARegistrar);		
	}

	private SEM getSem() {
		return this.sistema;
	}
}
