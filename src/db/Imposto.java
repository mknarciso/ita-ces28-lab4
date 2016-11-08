package db;

import ps.PS;
import ps.psVisitor;

public abstract class Imposto implements psVisitor {

		protected double total=0;

		public double getTotal() {
			return total;
		}

		@Override
		public void visitar(PS ps) {
			calculaImposto(DbTax.itemQtde,ps.getPrice(),ps.getCategoriaTributaria());		
			
		}

		private void calculaImposto(int itemQtde, float price, int categoriaTributaria) {
			// TODO Auto-generated method stub
		}

	}
