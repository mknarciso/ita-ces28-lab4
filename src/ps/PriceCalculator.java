package ps;

public class PriceCalculator implements psVisitor {
	//PriceCalculator: Uses Visitor patterns to
	//travel throught the PS tree and calculate price/taxes.
	protected double total_ = 0;
	
	public void resetPrice(){
		total_ = 0;
	}
	public double getTotal() {
		return total_;
	}
	@Override
	public void visitar(PS ps) {
		total_ = total_ + ps._preco;
	}

}
