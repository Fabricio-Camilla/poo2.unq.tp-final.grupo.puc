package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class PuntoDeVenta {
	
	private SEM sistema;
	private ZonaDeEstacionamiento zona;
	
	public PuntoDeVenta(SEM sistema, ZonaDeEstacionamiento zona) {
		this.sistema = sistema;
		this.zona = zona;
	}

	public void cargarCredito(Double montoACargar, String celular) {
		TicketDeRecargaCredito ticket = new TicketDeRecargaCredito(125, this, LocalTime.now(), montoACargar, celular);
		this.getSem().cargarCredito(montoACargar, celular);
		this.getSem().registrarTicket(ticket);
	}

	public EstacionamientoCompraPuntual registarEstacionamiento(String patente, int cantidadDeHoras) throws Exception {
		EstacionamientoCompraPuntual estacionamientoARegistrar = new EstacionamientoCompraPuntual(LocalTime.now(), patente, cantidadDeHoras);
		TicketDeEstacionamiento ticket = new TicketDeEstacionamiento(1205, this, LocalTime.now(), cantidadDeHoras);
		this.getSem().registrarUnNuevoEstacionamientoEnLaZona(estacionamientoARegistrar, this.getZona());
		this.getSem().registrarTicket(ticket);
		return estacionamientoARegistrar;
	}

	private ZonaDeEstacionamiento getZona() {
		return this.zona;
	}

	private SEM getSem() {
		return this.sistema;
	}
}
