package poo2.edu.unq.ar.tpFinal;

import java.util.ArrayList;
import java.util.List;

public class SEM {

	private List<Usuario> usuarios;

	public SEM() {
		this.usuarios = new ArrayList<Usuario>();
	}

	public void registrarAlUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public boolean tieneRegistradoAlUsuario(Usuario usuario) {
		return this.usuarios.contains(usuario);
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
