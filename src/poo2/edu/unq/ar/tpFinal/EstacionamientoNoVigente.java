package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public class EstacionamientoNoVigente implements IEstadoDeEstacionamiento{

    public void alertaInicioEstacionamiento(AppDeUsuario usuario) throws Exception {
        if(usuario.getSEM().calcularSaldoSuficiente(usuario)) {
            ZonaDeEstacionamiento zona = usuario.getSEM().encontrarZonaEstacionamientoEn(usuario.getLocalizacion());
            Estacionamiento estacionamiento = new EstacionamientoViaApp(LocalTime.now(), usuario.getSEM().getHoraFin(), usuario.getPatente());
            usuario.getSEM().registrarUnNuevoEstacionamientoEnLaZona(estacionamiento, zona); 
            usuario.cambiarAEstadoVigente(new EstacionamientoVigente());
        }else {
            new Exception ("No tiene saldo sufiencte");
        }
    }

    public void alertaFinEstacionamiento(AppDeUsuario usuario){
    }

    public boolean estaVigente() {
        return false;
    }
}