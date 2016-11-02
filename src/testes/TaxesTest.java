package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import db.*;

public class TaxesTest {

	@Test
	public void IPI() {
		ITax tax = new IPI();
		assertEquals(0.66,tax.calculaImposto(1, 1, 1),0.01);
		assertEquals(0,tax.calculaImposto(1, 1, 0),0.01);
	}

}
