package db;

import ps.PS;
import ps.psVisitor;

public abstract class Imposto implements psVisitor {

		protected double total=0;
		protected double last =0;
		public double getTotal() {
			return total;
		}
		public double getLast(){
			return last;
		}

		@Override
		public void visitar(PS ps) {
			//System.out.println("CalculaImposto:"+DbTax.itemQtde+","+ps.getPrice()+","+ps.getCategoriaTributaria());
			calculaImposto(DbTax.itemQtde,ps.getPrice(),ps.getCategoriaTributaria());
			ps.setImposto(last);
			
		}

		public  abstract void calculaImposto(int itemQtde, float price, int categoriaTributaria);

	}
