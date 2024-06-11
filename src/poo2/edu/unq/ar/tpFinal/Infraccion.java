package poo2.edu.unq.ar.tpFinal;

import java.time.LocalDate;
import java.time.LocalTime;

public class Infraccion {

	private String patente;
	private LocalDate fechaEmision;
	private LocalTime horaEmision;
	private AppInspector emisor;
	private ZonaDeEstacionamiento zonaEmision;

	public Infraccion(String patente, LocalDate fechaEmision, LocalTime horaEmision, AppInspector emisor,
			ZonaDeEstacionamiento zonaEmision) {
		this.patente = patente;
		this.fechaEmision = fechaEmision;
		this.horaEmision = horaEmision;
		this.emisor = emisor;
		this.zonaEmision = zonaEmision;
	}

}
