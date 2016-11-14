package nota_fiscal;


import ps.PS;
import ps.DbConnectPS;

public final class ItemDeVenda {
	//Item de venda has a unique PS 
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
		//System.out.println(_PS);
		return _PS.getPrice()*_quantidade;
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
	public String getOutros() {
		return _outros;
	}
	public String printIV() {
		return "==[ "+ _quantidade + " unidades de " + _PS.getName() + " ]==\n"+ _PS.printPS();
	}

}
