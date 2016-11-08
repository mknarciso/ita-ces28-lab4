package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import ps.DbConnectPS;
import ps.PS;
import ps.Produto;
import ps.Servico;

public class psTest {
	PS produtoT1 = new Produto("T1", 15, "Frutas", 1 , "Produto principal");
	PS produtoT2 = new Produto("T2", 15, "Frutas", 1 , "Produto principal");
	PS servicoS1 = new Servico("S1", 15, "Frutas", 1 , "Serviço principal");
	
	
	@Test
	public void testAddNewProduct() {
		DbConnectPS dbconn = DbConnectPS.getInstance();
		dbconn.addPS(produtoT1);
		dbconn.addPS(servicoS1);
		
		assertEquals("Correto", "T1", dbconn.getPS("T1").getName());
		assertEquals("Correto", "S1", dbconn.getPS("S1").getName());
	}
	
	@Test
	public void testAddProductTree(){
		DbConnectPS dbconn = DbConnectPS.getInstance();
		dbconn.addPS(produtoT1);
		dbconn.addPS(servicoS1);
		PS pd1;
		PS sv1;
		
		pd1 = dbconn.getPS("T1");
		sv1 = dbconn.getPS("S1");
		
		try {
			produtoT2.addPS(pd1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			produtoT2.addPS(sv1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		dbconn.addPS(produtoT2);
		
		try {
			assertEquals("Correto", "S1", dbconn.getPS("T2").getPS(1).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
