package db;

public class ICMS extends Imposto {
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		//System.out.println("ICMS("+qtde+","+valor+"+"+cat+")");
		//System.out.print(total+"+");
		last = valor*0.06*qtde;
		total = total + last;
		//System.out.println(valor*0.06*qtde+"="+total);
	}
}
