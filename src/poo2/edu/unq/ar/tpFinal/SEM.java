package poo2.edu.unq.ar.tpFinal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SEM {
	private Set<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private Set<AppDeUsuario> usuarios;

	public SEM() {
		this.usuarios = new HashSet<AppDeUsuario>();
		this.zonasDeEstacionamiento = new HashSet<ZonaDeEstacionamiento>();
	}

	public void registrarAlUsuario(AppDeUsuario usuario) {
		this.usuarios.add(usuario);
	}

	public boolean tieneRegistradoAlUsuario(AppDeUsuario usuario) {
		return this.usuarios.contains(usuario);
	}

	public void registrarZonaDeEstacionamiento(ZonaDeEstacionamiento zonaEstacionamiento) {
		this.zonasDeEstacionamiento.add(zonaEstacionamiento);
	}

	public int cantidadDeUsuarioRegistrados() {
		return this.usuarios.size();
	}

	public boolean tieneRegistradaLaZonaDeEstacionamiento(ZonaDeEstacionamiento zonaEstacionamiento) {
		return this.zonasDeEstacionamiento.contains(zonaEstacionamiento);
	}

	public int cantidadDeZonasDeEstacionamiento() {
		return this.zonasDeEstacionamiento.size();
	}

	// registrar una zona de estacionamiento con una fraja horaria, pasando precio
	// por hora
	// cada zona de estacionamiento tiene puntos de venta, deberían de agregarse
	// aparte

	// un usuario puede estacionar por app o por compra puntual
	// estacionamiento por compra puntual:
	// usuario estaciona el auto
	// usuario va a un punto de venta del estacionamiento
	// usuario pasa patente, cantidad de horas.
	// comerciante(punto de venta) ingresa datos al SEM en un ticket, es colaborador
	// interno el SEM (todos los conocen)
	// En esta modalidad el fin de estacionamiento queda clavado de una, si no tiene
	// credito lanza exception o no registra
}
