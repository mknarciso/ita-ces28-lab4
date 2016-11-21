package db;

public class IPI extends Imposto{
	@Override
	public void calculaImposto(int quantity, float valor, int category) {
		//Calculate IPI Strategy 
		last = 0;
		if(category == 1)
			last = 0.5*quantity+valor*0.16;
		
		total = total + last;
		// Exemplo de acesso a dado persistente:
		// +DbConnectTax.getInstance().persistValue;
	}

}
