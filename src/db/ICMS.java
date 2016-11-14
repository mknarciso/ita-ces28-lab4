package db;

public class ICMS extends Imposto {
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		//Calculate the tax based on quantity, value and category
		last = valor*0.06*qtde;
		total = total + last;
	}
}
