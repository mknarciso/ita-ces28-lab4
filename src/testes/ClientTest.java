package testes;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import nota_fiscal.NFBuilder;
import nota_fiscal.NotaFiscal;
import ps.DbConnectPS;

public class ClientTest {
	static DbConnectPS psDB;
	
	@BeforeClass
	public static void setup() throws Exception {
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
	public void testNFBuilder() {
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
	public void testgenerateNF() {
		NFBuilder nFBuilder = new NFBuilder("banana", 40);
		nFBuilder.addItemDeVenda("laranja", 200);
		NotaFiscal notaFiscal = nFBuilder.saveNF();
		assertEquals (996.64,nFBuilder.getImposto(), 0.1);
		assertTrue (notaFiscal instanceof NotaFiscal);
		assertEquals (201600001,notaFiscal.getId());
		System.out.println(notaFiscal.printNF());
		NFBuilder nFBuilderTwo = new NFBuilder("bola", 10);
		NotaFiscal notaFiscalTwo = nFBuilderTwo.saveNF();
		assertEquals(201600002,notaFiscalTwo.getId());
		System.out.println(notaFiscalTwo.printNF());
	}
}
