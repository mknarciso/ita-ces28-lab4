package ps;

public class PriceCalculator implements psVisitor {
	protected double total=0;
	public void resetPrice(){
		total=0;
	}
	public double getTotal() {
		return total;
	}
	@Override
	public void visitar(PS ps) {
		total = total + ps._preco;
	}

}
