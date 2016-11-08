package nota_fiscal;

import java.util.ArrayList;

public class NFBuilder {
	//nota fiscal em elaboração
	
	private int _valor;
	private float _impostos;
	private String _outros;
	private ArrayList<ItemDeVenda> _itensLista;
	
	NFBuilder(String productServ, int quantity) {
		addItemDeVenda(productServ, quantity);
		
	}
	private void saveNF() {
		
	}
	
	public void addItemDeVenda(String productServ, int quantity) {
		ItemDeVenda itemDeVenda = new ItemDeVenda(productServ, quantity);
		_itensLista.add(itemDeVenda);
		
		
	}

	public void removeItemDeVenda(String productServ) {
		_itensLista.forEach((item) -> {
			if (item.getName() == productServ)
				_itensLista.remove(item);
		});
		
	};
	public int validateNF() {
		return 0;
	}

	

}
