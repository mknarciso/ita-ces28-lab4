package db;

public class ICMS extends Imposto {
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		//System.out.println("ICMS("+qtde+","+valor+"+"+cat+")");
		//System.out.print(total+"+");
		total = total + valor*0.06*qtde;
		//System.out.println(valor*0.06*qtde+"="+total);
	}
}
