package db;

public class IPI extends Imposto{
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		last = 0;
		if(cat==1)
			last = 0.5*qtde+valor*0.16;
		
		total = total + last;
	}

}
