package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

import db.*;
import nota_fiscal.ItemDeVenda;
import ps.DbConnectPS;
import ps.PS;
import ps.Produto;
import ps.Servico;

public class TaxesTest {
	static PS T1;
	static PS T2;
	static PS S1;
	static ArrayList<ItemDeVenda> lista;
	static ItemDeVenda iv;
	static DbConnectPS PsDb;
	static DbConnectTax dbTax;
	@BeforeClass
	public static void prepare() throws Exception{
		PsDb = DbConnectPS.getInstance();
		S1 = new Servico("S1", 10, "Serviços", 3 , "obs 3");
		PsDb.addPS(S1);
		T1 = new Produto("T1", 15, "Produtos", 1 , "obs");
		T2 = new Produto("T2", 25, "Produtos", 2 , "obs 2");
		lista = new ArrayList<ItemDeVenda>();
		T2.addPS(PsDb.getPS("S1"));
		PsDb.addPS(T2);
		T1.addPS(PsDb.getPS("S1"));
		T1.addPS(PsDb.getPS("T2"));
		PsDb.addPS(T1);
		//System.out.println(iv);
		dbTax = DbConnectTax.getInstance();
	}
	@Test
	public void checkPsReturn(){
		assertEquals(60,iv.getPS().getPrice(),0.1);
		assertEquals("T1",iv.getName());
	}
	
	@Test
	public void taxesTest1() {
		lista = new ArrayList<ItemDeVenda>();
		iv = new ItemDeVenda("T1",2);
		lista.add(iv);
		assertEquals(24.4,dbTax.calculateTax(lista),0.01);
	}
	@Test
	public void taxesTestWithTwoIV() {
		lista = new ArrayList<ItemDeVenda>();
		iv = new ItemDeVenda("T1",2);
		lista.add(iv);
		ItemDeVenda iv2 = new ItemDeVenda("S1",5);
		lista.add(iv2);
		assertEquals(27.4,dbTax.calculateTax(lista),0.01);
	}
}
