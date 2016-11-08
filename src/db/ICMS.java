package db;

public class ICMS extends Imposto {
	@SuppressWarnings("unused")
	private void calculaImposto(int qtde, float valor, int cat) {
		total = total + valor*0.06*qtde;
	}
}
