package testes;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import db.NFAlreadyValidatedException;
import nota_fiscal.NFBuilder;
import nota_fiscal.NotValidNFException;
import nota_fiscal.NotaFiscal;
import ps.DbConnectPS;

public class NfTest {
	static DbConnectPS psDB;
	@BeforeClass
	public static void setup() throws Exception {
		//In setup, in build up the PS trees hardcoded
		psDB = DbConnectPS.getInstance();
		psDB.newPS("Produto","Caixote", 2, "Embalagem", 1 , "obs");
		psDB.newPS("Serviço","Delivery", 23, "Logistica", 3 , "Serviço principal");
		psDB.newPS("Produto","pessego", 15, "Frutas", 2 , "Produto principal");
		psDB.addToPSSubPS("pessego","Caixote");
		
	}
	@Test
	public void NoIVNF() {
		NFBuilder nfBuilder = new NFBuilder("pessego", 40);
		assertFalse(nfBuilder.getItemsList().size()==0);
	}
	
	@Test
	public void NFemElaboracao(){
		// NFBuilder é uma nota fiscal em elaboração
		NFBuilder nFBuilder = new NFBuilder("pessego", 40);
		String result = "NF em elabora��o\n"
				+ "IV List:\n" + "pessego, 40 unidades\n";
		assertEquals(nFBuilder.printNF(), result);
		//Verifica se há acesso publico aos atributos
		int i = nFBuilder.getClass().getFields().length;
		for(int j=0; j<i;j++){
			assertFalse(nFBuilder.getClass().getFields()[j].isAccessible());
		}
	}	
	
	/* Estes testes podem causar mudança na numeracao das NF usadas em ClientTest.java, 
	 * uma vez que o taxDB é único e a ordem dos testes, que é aleatória interfere na 
	 * numeração, por este motivo estão comentados. Caso os testes sejam executados isoladamente 
	 * (por arquivo) não haverá problemas.
	 * 
	@Test
	public void NFisFinal() throws NFAlreadyValidatedException, NotValidNFException{
		// NotaFiscal é uma nota fiscal final, e não possui setters nem construtor público
		NFBuilder nFBuilder = new NFBuilder("pessego", 40);
		NotaFiscal nf = nFBuilder.saveNF();
		assertTrue (nf instanceof NotaFiscal);
		assertEquals(0,NotaFiscal.class.getConstructors().length);
	}

	@Test
	public void differentIds() throws NFAlreadyValidatedException, NotValidNFException{
		// NotaFiscal mesmo que com os mesmos itens, gera uma nf, e um id, diferente
		NFBuilder nFBuilder = new NFBuilder("pessego", 40);
		NFBuilder nFBuilder2 = new NFBuilder("pessego", 40);
		NotaFiscal nf = nFBuilder.saveNF();
		NotaFiscal nf2 = nFBuilder2.saveNF();
		assertFalse(nf.getId()==nf2.getId());
	}
	 */
}
