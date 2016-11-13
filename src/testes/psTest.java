package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import ps.DbConnectPS;
import ps.PS;
import ps.Produto;
import ps.Servico;

public class PsTest {
	PS produtoT1 = new Produto("T1", 15, "Frutas", 1 , "Produto principal");
	PS produtoT2 = new Produto("T2", 10, "Frutas", 1 , "Produto principal");
	PS servicoS1 = new Servico("S1", 23, "Frutas", 1 , "Servi�o principal");
	
	
	@Test
	public void testAddNewProduct() {
		DbConnectPS dbconn = DbConnectPS.getInstance();
		dbconn.addPS(produtoT1);
		dbconn.addPS(servicoS1);
		assertEquals("Correto", "T1", dbconn.getPS("T1").getName());
		assertEquals("Correto", "S1", dbconn.getPS("S1").getName());
	}
	
	@Test
	public void testAddProductTree() throws Exception{
		DbConnectPS dbconn = DbConnectPS.getInstance();
		dbconn.addPS(produtoT1);
		dbconn.addPS(servicoS1);
		produtoT2.addPS(dbconn.getPS("T1"));
		produtoT2.addPS(dbconn.getPS("S1"));
		dbconn.addPS(produtoT2);
		assertEquals("Correto", "S1", dbconn.getPS("T2").getPS(1).getName());
	}
	@Test
	public void checkPrice() throws Exception{
		assertEquals(15,produtoT1.getPrice(),0.01);
		assertEquals(10,produtoT2.getPrice(),0.01);
		DbConnectPS dbconn = DbConnectPS.getInstance();
		dbconn.addPS(produtoT1);
		dbconn.addPS(servicoS1);
		produtoT2.addPS(dbconn.getPS("T1"));
		assertEquals(25,produtoT2.getPrice(),0.01);
		produtoT2.addPS(dbconn.getPS("S1"));
		assertEquals(48,produtoT2.getPrice(),0.01);
		dbconn.addPS(produtoT2);
		assertEquals(48, dbconn.getPS("T2").getPrice(),0.01);	
	}

}
