package testes;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import nota_fiscal.NFBuilder;
import nota_fiscal.NotaFiscal;
import ps.DbConnectPS;
import ps.PS;
import ps.Produto;
import ps.Servico;

public class ClientTest {
	static PS f1,f2,f3,p1,c1,s1;
	static DbConnectPS psDB;
	
	@BeforeClass
	public static void setup() throws Exception {
		psDB = DbConnectPS.getInstance();
		c1 = new Produto("Caixa", 2, "Embalagem", 1 , "obs");
		psDB.addPS(c1);
		s1 = new Servico("Transporte", 23, "Logistica", 3 , "Serviço principal");
		psDB.addPS(s1);
		f1 = new Produto("banana", 15, "Frutas", 2 , "Produto principal");
		f1.addPS(psDB.getPS("Caixa"));
		f1.addPS(psDB.getPS("Transporte"));
		psDB.addPS(f1);
		f2 = new Produto("laranja", 10, "Frutas", 2 , "Produto principal");
		f2.addPS(psDB.getPS("Caixa"));
		f2.addPS(psDB.getPS("Transporte"));
		psDB.addPS(f2);
		f3 = new Produto("batata", 5, "Frutas", 2 , "Produto principal");
		f3.addPS(psDB.getPS("Caixa"));
		f3.addPS(psDB.getPS("Transporte"));
		psDB.addPS(f3);
		p1 = new Produto("bola", 25, "Equipamento", 1, "");
		p1.addPS(psDB.getPS("Transporte"));
		psDB.addPS(p1);
		
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
