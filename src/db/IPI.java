package db;

public class IPI extends Imposto{
	@SuppressWarnings("unused")
	private void calculaImposto(int qtde, float valor, int cat) {
		if(cat==1)
			total = total + 0.5*qtde+valor*0.16;
	}

}
