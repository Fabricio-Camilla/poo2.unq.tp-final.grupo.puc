package poo2.edu.unq.ar.tpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEstacionamientoVigente {

	private IEstadoDeEstacionamiento vigente;
	private AppDeUsuario usuario;
	private SEM sem;
	
	@BeforeEach
	void setUp() {
		usuario = mock(AppDeUsuario.class);
		sem = mock(SEM.class);
		vigente = new EstacionamientoVigente();
		
	}
	
	@Test
	void testEstadoNoVigenteRealizaAlertaInicioEstacionamiento() throws Exception {
		vigente.alertaInicioEstacionamiento(usuario);
	}
	
	@Test
	void testEstadoNoVigenteRealizaAlertaFinEstacionamiento() throws Exception {
		when(usuario.getSEM()).thenReturn(sem);
		when(usuario.getLocalizacion()).thenReturn(new Point(1,1));
		when(usuario.getPatente()).thenReturn("AF245AD");
		
		vigente.alertaFinEstacionamiento(usuario);

		verify(usuario, atLeastOnce()).cambiarAEstadoNoVigente();
	}

}
