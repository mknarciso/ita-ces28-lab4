package db;

import java.util.ArrayList;

import nota_fiscal.ItemDeVenda;
import ps.PS;

public class DbTax {
	private IPI imposto1;
	private ICMS imposto2;
	static int itemQtde;
	public double calculateTax(ArrayList<ItemDeVenda> idv) {
		imposto1 = new IPI(); 
		imposto2 = new ICMS(); 
		for(int i=0;i<idv.size();i++){
			itemQtde = idv.get(i).getQuantity();
			//System.out.println(idv.get(0).getPS());
			allTaxes(idv.get(i).getPS());
		}
		return sumTaxes();
	}

	private double sumTaxes() {
		//System.out.println("Sum Taxes:"+imposto1.getTotal()+"+"+imposto2.getTotal());
		return imposto1.getTotal()+imposto2.getTotal();
	}

	private void allTaxes(PS raiz){
		raiz.aceitarVisitante(imposto1);
		raiz.aceitarVisitante(imposto2);
	}
}
