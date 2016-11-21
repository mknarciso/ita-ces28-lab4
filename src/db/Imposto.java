package db;

import ps.PS;
import ps.psVisitor;

public abstract class Imposto implements psVisitor {
		
		protected double total= 0;
		protected double last = 0;
		protected double persist = 0; // This value can be used to persist the value between NFs
		
		public double getTotal() {
			return total;
		}
		public double getLast(){
			return last;
		}

		@Override
		public void visitar(PS ps) {
			//Visitor pattern 
			//System.out.println("CalculaImposto:"+DbTax.itemQtde+","+ps.getPrice()+","+ps.getCategoriaTributaria());
			calculaImposto(DbTax.itemQtde,ps.getPrice(),ps.getCategoriaTributaria());
			//ps.addImposto(last);
			
		}
		protected void reset(){
			total = 0;
			last = 0;
		}
		
		//Strategy Pattern to calculate taxes
		public  abstract void calculaImposto(int itemQtde, float price, int categoriaTributaria);

	}
