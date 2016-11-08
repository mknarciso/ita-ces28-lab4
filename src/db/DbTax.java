package db;

import java.util.ArrayList;

import nota_fiscal.ItemDeVenda;
import ps.PS;

public class DbTax {
	IPI imposto1;
	ICMS imposto2;
	public static int itemQtde;
	public double calculateTax(ArrayList<ItemDeVenda> idv) {
		imposto1 = new IPI(); 
		imposto2 = new ICMS(); 
		for(int i=0;i<idv.size();i++){
			itemQtde = idv.get(i).getQuantity();
			allTaxes(idv.get(i).getPS());
		}
		return sumTaxes();
	}

	private double sumTaxes() {
		return imposto1.getTotal()+imposto2.getTotal();
	}

	private void allTaxes(PS raiz){
		raiz.aceitarVisitante(imposto1);
		raiz.aceitarVisitante(imposto2);
	}
}
