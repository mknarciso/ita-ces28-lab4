package testes;

import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Test;

import db.NFAlreadyValidatedException;
import nota_fiscal.NFBuilder;
import nota_fiscal.NotValidNFException;
import nota_fiscal.NotaFiscal;
import ps.DbConnectPS;

public class ClientTest {
	static DbConnectPS psDB;
	
	@BeforeClass
	public static void setup() throws Exception {
		//In setup, in build up the PS trees hardcoded
		psDB = DbConnectPS.getInstance();
		psDB.newPS("Produto","Caixa", 2, "Embalagem", 1 , "obs");
		psDB.newPS("Serviço","Transporte", 23, "Logistica", 3 , "Serviço principal");
		psDB.newPS("Produto","banana", 15, "Frutas", 2 , "Produto principal");
		psDB.addToPSSubPS("banana","Caixa");
		psDB.addToPSSubPS("banana","Transporte");
		psDB.newPS("Produto","laranja", 10, "Frutas", 2 , "Produto principal");
		psDB.addToPSSubPS("laranja","Caixa");
		psDB.addToPSSubPS("laranja","Transporte");
		psDB.newPS("Produto","batata", 5, "Frutas", 2 , "Produto principal");
		psDB.addToPSSubPS("batata","Caixa");
		psDB.addToPSSubPS("batata","Transporte");
		psDB.newPS("Produto","bola", 25, "Equipamento", 1, "");
		psDB.addToPSSubPS("bola","Transporte");
		
	}
	
	@Test
	public void testNFBuilder() throws Exception {
		//In this NF Builder test, we construct a Builder
		//and simulate adding and removing items
		NFBuilder nFBuilder = new NFBuilder("banana", 40);
		String result = "NF em elabora��o\n"
				+ "IV List:\n" + "banana, 40 unidades\n";
		assertEquals(nFBuilder.printNF(), result);
		assertEquals (1600,nFBuilder.getValue(), 0.1 );
		
		nFBuilder.addItemDeVenda("laranja", 200);
		
		String resultTwo = result + "laranja, 200 unidades\n";
		assertEquals(nFBuilder.printNF(), resultTwo);
		assertEquals(8600,nFBuilder.getValue(), 0.1);
		
		nFBuilder.removeItemDeVenda("laranja");
		assertEquals (nFBuilder.printNF(), result);
		assertEquals(1600,nFBuilder.getValue(), 0.1);
		
	}
	
	@Test 
	public void testgenerateNF() throws NFAlreadyValidatedException, NotValidNFException {
		//In this test, we create a NFBuilder, validates (calculating taxes) and
		// persists it, generating a immutable NotaFiscal
		NFBuilder nFBuilder = new NFBuilder("banana", 40);
		nFBuilder.addItemDeVenda("laranja", 200);
		NotaFiscal notaFiscal = nFBuilder.saveNF();
		assertEquals (996.64,nFBuilder.getImposto(), 0.1);
		assertTrue (notaFiscal instanceof NotaFiscal);
		assertEquals (201600001,notaFiscal.getId());
		String result = "NF completa - ID 201600001\n" + 
				"IV List:\n" + 
					"==[ 40 unidades de banana ]==\n" + 
					"====>      banana   Preço:      40,00\n"+//   Imposto:      96,00\n" + 
					"====>       Caixa   Preço:       2,00\n"+//   Imposto:      25,12\n" +
					"====>  Transporte   Preço:      23,00\n"+//   Imposto:      55,20\n" +
					"==[ 200 unidades de laranja ]==\n" + 
					"====>     laranja   Preço:      35,00\n"+//   Imposto:     420,00\n" +
					"====>       Caixa   Preço:       2,00\n"+//   Imposto:     124,32\n" +
					"====>  Transporte   Preço:      23,00\n"+//   Imposto:     276,00\n" +
					"Valor final: 8600.0\n" +
					"Impostos calculados: 996.64\n";
		
		assertEquals(notaFiscal.printNF(), result);
		
		NFBuilder nFBuilderTwo = new NFBuilder("bola", 10);
		
		NotaFiscal notaFiscalTwo = nFBuilderTwo.saveNF();
		assertEquals(201600002,notaFiscalTwo.getId());
		String resultTwo = "NF completa - ID 201600002\n" + 
				"IV List:\n" + 
				"==[ 10 unidades de bola ]==\n" + 
				"====>        bola   Preço:      48,00\n"+//   Imposto:      41,48\n" +
				"====>  Transporte   Preço:      23,00\n"+//   Imposto:      13,80\n" +
				"Valor final: 480.0\n" + 
				"Impostos calculados: 55.279999999999994\n";


		assertEquals (resultTwo, notaFiscalTwo.printNF());
		// NotaFiscal têm ids diferentes
		assertFalse(notaFiscal.getId()==notaFiscalTwo.getId());
	}
	
	@Test(expected = NFAlreadyValidatedException.class)
	public void testAlreadyValidatedException() throws NFAlreadyValidatedException, NotValidNFException {
		//This tests tries to save the same NF twice
		//So the system throws NFAlreadyValidatedException
		NFBuilder nFBuilder = new NFBuilder("bola", 10);
		nFBuilder.saveNF();
		//Trying to save once more
		nFBuilder.saveNF(); //Exception thrown
	}
	
	
}
