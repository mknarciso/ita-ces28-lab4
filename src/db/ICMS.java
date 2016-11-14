package db;

public class ICMS extends Imposto {
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		last = valor*0.06*qtde;
		total = total + last;
	}
}
