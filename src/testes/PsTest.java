package testes;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ps.DbConnectPS;
import ps.PS;
import ps.ProdutoFinal;

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
	@Test
	public void checkAccess() throws Exception{
		assertFalse(PS.class.getDeclaredMethod("addPS", String.class).isAccessible());
		assertEquals(0,PS.class.getConstructors().length);
	}
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	@Test
	public void checkFinal() throws Exception{
		thrown.expect(Exception.class);
		psDB.newPS("Produto","dT1", 15, "Frutas", 1 , "Produto principal");
		psDB.newPS("Produto","dT2", 10, "Frutas", 1 , "Produto principal");
		PS T1 = psDB.getPS("dT1");
		Method privateMethod = PS.class.getDeclaredMethod("addPS", String.class);
		privateMethod.setAccessible(true);
		privateMethod.invoke(T1, "dT2");
	}
}
