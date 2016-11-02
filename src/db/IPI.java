package db;

public class IPI implements ITax{
	@Override
	public double calculaImposto(int qtde, double valor, int cat) {
		if(cat==1)
			return 0.5*qtde+valor*0.16;
		return 0;
	}

}
