package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import ps.DbConnectPS;

public class PsTest {
	DbConnectPS psDB = DbConnectPS.getInstance();
	
	@Test
	public void testAddNewProduct() {
		psDB.newPS("Produto","bT1", 15, "Frutas", 1 , "Produto principal");
		psDB.newPS("Servico","bS1", 23, "Frutas", 1 , "Serviço principal");
		assertEquals("Correto", "bT1", psDB.getPS("bT1").getName());
		assertEquals("Correto", "bS1", psDB.getPS("bS1").getName());
	}
	
	@Test
	public void testAddProductTree() throws Exception{
		psDB.newPS("Produto","cT1", 15, "Frutas", 1 , "Produto principal");
		psDB.newPS("Produto","cT2", 10, "Frutas", 1 , "Produto principal");
		psDB.newPS("Servico","cS1", 23, "Frutas", 1 , "Serviço principal");
		psDB.addToPSSubPS("cT2", "cT1");
		psDB.addToPSSubPS("cT2", "cS1");
		assertEquals("Correto", "cS1", psDB.getPS("cT2").getPS(1).getName());
	}
	@Test
	public void checkPrice() throws Exception{
		psDB.newPS("Produto","aT1", 15, "Frutas", 1 , "Produto principal");
		psDB.newPS("Produto","aT2", 10, "Frutas", 1 , "Produto principal");
		psDB.newPS("Servico","aS1", 23, "Frutas", 1 , "Serviço principal");
		assertEquals(15,psDB.getPS("aT1").getPrice(),0.01);
		assertEquals(10,psDB.getPS("aT2").getPrice(),0.01);
		psDB.addToPSSubPS("aT2","aT1");
		assertEquals(25,psDB.getPS("aT2").getPrice(),0.01);
		psDB.addToPSSubPS("aT2","aS1");
		assertEquals(48, psDB.getPS("aT2").getPrice(),0.01);	
	}

}
