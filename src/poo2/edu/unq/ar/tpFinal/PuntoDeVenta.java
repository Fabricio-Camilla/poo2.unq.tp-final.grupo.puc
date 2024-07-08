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
		this.getSem().cargarCredito(montoACargar, celular);
		this.getSem().registrarTicket(ticket);
	}

	public EstacionamientoCompraPuntual registarEstacionamiento(AppDeUsuario appUsuario, String patente,
			int cantidadDeHoras) throws Exception {
		EstacionamientoCompraPuntual estacionamientoARegistrar = new EstacionamientoCompraPuntual(appUsuario,
				LocalDateTime.now(), patente, cantidadDeHoras);
		TicketDeEstacionamiento ticket = new TicketDeEstacionamiento(1205, this, LocalDateTime.now(), cantidadDeHoras);
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
