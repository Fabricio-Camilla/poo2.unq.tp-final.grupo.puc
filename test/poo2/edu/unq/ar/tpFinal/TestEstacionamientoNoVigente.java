package poo2.edu.unq.ar.tpFinal;

import static org.mockito.Mockito.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstacionamientoNoVigente {

	
	private IEstadoDeEstacionamiento noVigente;
	private AppDeUsuario usuario;
	private SEM sem;
	
	@BeforeEach
	void setUp() {
		usuario = spy(AppDeUsuario.class);
		sem = mock(SEM.class);
		noVigente = new EstacionamientoNoVigente();
		
	}
	
	@Test
	void testEstadoNoVigenteRealizaAlertaInicioEstacionamiento() throws Exception {
		when(usuario.getSEM()).thenReturn(sem);
		when(sem.calcularSaldoSuficiente(usuario)).thenReturn(true);
		when(usuario.getLocalizacion()).thenReturn(new Point(1,1));
		when(usuario.getPatente()).thenReturn("AF245AD");
		
		noVigente.alertaInicioEstacionamiento(usuario);
		
		verify(usuario, atLeastOnce()).cambiarAEstadoVigente();
	}
	
	@Test
	void testEstadoNoVigenteRealizaAlertaFinEstacionamiento() throws Exception {
		noVigente.alertaFinEstacionamiento(usuario);
	}
}
