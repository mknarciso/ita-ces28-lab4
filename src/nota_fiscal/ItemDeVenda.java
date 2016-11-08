package nota_fiscal;


import ps.PS;
import ps.DbConnectPS;

public final class ItemDeVenda {
	private final int _quantidade;
	private final String _outros;
	private final PS _PS;
	
	public ItemDeVenda (String productServ, int quantity) {
		_quantidade = quantity;
		DbConnectPS dbConnectPS = DbConnectPS.getInstance();
		_PS = dbConnectPS.getPS(productServ);
		_outros = "";
	}
	public float getPrice() {
		return _PS.getPrice();
	}
	
	public String getName() {
		return _PS.getName();
	}

	public int getQuantity() {
		return _quantidade;
	}
	
	public PS getPS(){
		return _PS;
	}

}
