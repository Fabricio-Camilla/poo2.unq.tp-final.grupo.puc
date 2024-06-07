package poo2.edu.unq.ar.tpFinal;

import java.awt.Point;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SEM {
	private ArrayList<ZonaDeEstacionamiento> zonasDeEstacionamiento;
	private Set<AppDeUsuario> usuarios;

	public SEM() {
		this.usuarios = new HashSet<AppDeUsuario>();
		this.zonasDeEstacionamiento = new ArrayList<ZonaDeEstacionamiento>();
	}

	public void registrarAlUsuario(AppDeUsuario usuario) {
		this.usuarios.add(usuario);
	}

	public boolean tieneRegistradoAlUsuario(AppDeUsuario usuario) {
		return this.usuarios.contains(usuario);
	}

	public void registrarZonaDeEstacionamiento(Point localizacion) {
		AppInspector inspector = new AppInspector(this);
		ZonaDeEstacionamiento zonaEstacionamiento = new ZonaDeEstacionamiento(this, localizacion, inspector,
				LocalTime.of(7, 0), LocalTime.of(20, 0), 40d);
		this.zonasDeEstacionamiento.add(zonaEstacionamiento);
	}

	public int cantidadDeUsuarioRegistrados() {
		return this.usuarios.size();
	}

	public int cantidadDeZonasDeEstacionamiento() {
		return this.zonasDeEstacionamiento.size();
	}

	public void indicarFinEstacionamiento(AppDeUsuario appDeUsuario) {
		//en base al celular asociado a la app se finaliza el estacionamiento
	}

	public void indicarInicioEstacionamiento(AppDeUsuario appDeUsuario) {
		// hay que chekiar que la app tenga credito para iniciar el estacionamiento
		
	}

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
