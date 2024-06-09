package poo2.edu.unq.ar.tpFinal;

import java.time.LocalTime;

public abstract class Estacionamiento {

    protected LocalTime horaInicio;
    protected LocalTime horaFin;

    protected Estacionamiento(LocalTime horaInicio, LocalTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    protected abstract String getPatenteDeUsuario();

    protected  LocalTime getHoraInicio() {
        return this.horaFin;
    }
}