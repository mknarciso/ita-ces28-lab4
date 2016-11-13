package db;

public class IPI extends Imposto{
	@Override
	public void calculaImposto(int qtde, float valor, int cat) {
		//System.out.println("IPI("+qtde+","+valor+"+"+cat+")");
		//System.out.print(total+"=>");
		if(cat==1)
			total = total + 0.5*qtde+valor*0.16;
		//System.out.println(total);
		
		
	}

}
