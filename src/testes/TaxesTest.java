package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import db.*;
import nota_fiscal.ItemDeVenda;
import ps.DbConnectPS;

public class TaxesTest {
	static ArrayList<ItemDeVenda> lista;
	static ItemDeVenda iv;
	static DbConnectPS psDB;
	static DbConnectTax dbTax;
	@BeforeClass
	public static void prepare() throws Exception{
		psDB = DbConnectPS.getInstance();
		psDB.newPS("Serviço","sS1", 10, "Serviços", 3 , "obs 3");
		psDB.newPS("Produto","tT1", 15, "Produtos", 1 , "obs");
		psDB.newPS("Produto","tT2", 25, "Produtos", 2 , "obs 2");
		lista = new ArrayList<ItemDeVenda>();
		psDB.addToPSSubPS("tT2","sS1");
		psDB.addToPSSubPS("tT1","sS1");
		psDB.addToPSSubPS("tT1","tT2");
		//System.out.println(iv);
		dbTax = DbConnectTax.getInstance();
	}
	@Test
	public void checkPsReturn(){
		assertEquals(60,iv.getPS().getPrice(),0.1);
		assertEquals("tT1",iv.getName());
	}
	
	@Test
	public void taxesTest1() {
		lista = new ArrayList<ItemDeVenda>();
		iv = new ItemDeVenda("tT1",2);
		lista.add(iv);
		assertEquals(24.4,dbTax.calculateTax(lista),0.01);
	}
	@Test
	public void taxesTestWithTwoIV() {
		lista = new ArrayList<ItemDeVenda>();
		iv = new ItemDeVenda("tT1",2);
		lista.add(iv);
		ItemDeVenda iv2 = new ItemDeVenda("sS1",5);
		lista.add(iv2);
		assertEquals(27.4,dbTax.calculateTax(lista),0.01);
	}
}
