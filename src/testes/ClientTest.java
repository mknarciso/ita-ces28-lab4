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
		f1.addPS("Caixa");
		f1.addPS("Transporte");
		psDB.addPS(f1);
		f2 = new Produto("laranja", 10, "Frutas", 2 , "Produto principal");
		f2.addPS("Caixa");
		f2.addPS("Transporte");
		psDB.addPS(f2);
		f3 = new Produto("batata", 5, "Frutas", 2 , "Produto principal");
		f3.addPS("Caixa");
		f3.addPS("Transporte");
		psDB.addPS(f3);
		p1 = new Produto("bola", 25, "Equipamento", 1, "");
		p1.addPS("Transporte");
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
		String result = "NF completa - ID 201600001\n" + 
				"IV List:\n" + 
					"==[ 40 unidades de banana ]==\n" + 
					"====>      banana   Preço:      40,00   Imposto:      96,00\n" + 
					"====>       Caixa   Preço:       2,00   Imposto:      25,12\n" +
					"====>  Transporte   Preço:      23,00   Imposto:      55,20\n" +
					"==[ 200 unidades de laranja ]==\n" + 
					"====>     laranja   Preço:      35,00   Imposto:     420,00\n" +
					"====>       Caixa   Preço:       2,00   Imposto:     124,32\n" +
					"====>  Transporte   Preço:      23,00   Imposto:     276,00\n" +
					"Valor final: 8600.0\n" +
					"Impostos calculados: 996.64\n";
		
		assertEquals(notaFiscal.printNF(), result);
		
		NFBuilder nFBuilderTwo = new NFBuilder("bola", 10);
		
		NotaFiscal notaFiscalTwo = nFBuilderTwo.saveNF();
		assertEquals(201600002,notaFiscalTwo.getId());
		String resultTwo = "NF completa - ID 201600002\n" + 
				"IV List:\n" + 
				"==[ 10 unidades de bola ]==\n" + 
				"====>        bola   Preço:      48,00   Imposto:      41,48\n" +
				"====>  Transporte   Preço:      23,00   Imposto:      13,80\n" +
				"Valor final: 480.0\n" + 
				"Impostos calculados: 55.279999999999994\n";


		assertEquals (resultTwo, notaFiscalTwo.printNF());
	}
}
