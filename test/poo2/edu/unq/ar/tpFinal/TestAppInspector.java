package poo2.edu.unq.ar.tpFinal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAppInspector {

	private AppInspector inspector;
	private SEM sem;

	@BeforeEach

	void setUp() {
		sem = mock(SEM.class);
		inspector = new AppInspector(sem);
	}
	
	@Test
	
	void unInspectorAlSerCreadoPerteneceAUnSEM() {
		assertTrue(inspector.perteneceAlSem(sem));
	}

}
