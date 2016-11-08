package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import nota_fiscal.NFBuilder;
import nota_fiscal.NotaFiscal;

public class ClientTests {

	@Before
	public void setup() {
		//populate ps db's;
	}
	@Test
	public void testNFBuilder() {
		NFBuilder nFBuilder = new NFBuilder("banana", 40);
		String result = "NF em elaboração\n"
				+ "IV List:\n" + "banana, 40 unidades\n";
		assertEquals(nFBuilder.printNF(), result);
		assertEquals (nFBuilder.getValue(), 100.0, 0.1 );
		
		nFBuilder.addItemDeVenda("laranja", 200);
		
		String resultTwo = result + "laranja, 200 unidades\n";
		assertEquals(nFBuilder.printNF(), resultTwo);
		assertEquals(nFBuilder.getValue(), 300.0, 0.1);
		
		nFBuilder.removeItemDeVenda("laranja");
		assertEquals (nFBuilder.printNF(), result);
		assertEquals(nFBuilder.getValue(), 100.0, 0.1);
		
		//TODO: Nested PS
		
	}
	
	@Test 
	public void testgenerateNF() {
		NFBuilder nFBuilder = new NFBuilder("banana", 40);
		nFBuilder.addItemDeVenda("batata", 100);
		NotaFiscal notaFiscal = nFBuilder.saveNF();
		assertEquals (nFBuilder.getImposto(), 50.0, 0.1);
		assertTrue (notaFiscal instanceof NotaFiscal);
		assertEquals (notaFiscal.getId(), 1);
		NFBuilder nFBuilderTwo = new NFBuilder("bola", 10);
		NotaFiscal notaFiscalTwo = nFBuilderTwo.saveNF();
		assertEquals(notaFiscalTwo.getId(), 2);
	}

}
