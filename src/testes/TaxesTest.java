package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
	static ArrayList lista;
	static ItemDeVenda iv;
	static DbConnectPS PsDb;
	@BeforeClass
	public static void prepare() throws Exception{
		PsDb = DbConnectPS.getInstance();
		T1 = new Produto("T1", 15, "Produtos", 1 , "obs");
		PsDb.addPS(T1);
		T2 = new Produto("T2", 25, "Produtos", 2 , "obs 2");
		PsDb.addPS(T2);
		S1 = new Servico("S1", 10, "Serviços", 3 , "obs 3");
		PsDb.addPS(S1);
		lista = new ArrayList<ItemDeVenda>();
		T1.addPS(PsDb.getPS("S1"));
		T2.addPS(PsDb.getPS("S1"));
		T1.addPS(PsDb.getPS("T2"));
		iv = new ItemDeVenda("T1",2);
		lista.add(iv);
		//System.out.println(iv);
	}
	@Test
	public void checkPsReturn(){
		assertEquals(60,iv.getPS().getPrice(),0.1);
		assertEquals("T1",iv.getName());
	}
	
	@Test
	public void taxesTest1() {
		DbConnectTax dbTax = DbConnectTax.getInstance();
		assertEquals(24.4,dbTax.calculateTax(lista),0.01);
	}

}
