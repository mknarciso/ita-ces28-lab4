package nota_fiscal;


import ps.PS;
import ps.DbConnectPS;

public class ItemDeVenda {
	private int _quantidade;
	private String _outros;
	private PS _PS;
	
	public ItemDeVenda (String productServ, int quantity) {
		_quantidade = quantity;
		DbConnectPS dbConnectPS = DbConnectPS.getInstance();
		_PS = dbConnectPS.getPS(productServ);
	}
	
	public String getName() {
		return _PS.getName();
	}

}
