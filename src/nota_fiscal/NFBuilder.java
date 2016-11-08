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
	private NotaFiscal saveNF() {
		try {
			validateNF();
			int id = DbConnectNF.getInstance().generateID(this);
			NotaFiscal notaFiscal = new NotaFiscal(this, id);
			DbConnectNF.getInstance().persistNF(notaFiscal);
			return notaFiscal;
		} catch (NotValidNFException e) {
			System.out.println("NF not validated");
		}
		
		
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
	public void validateNF() {
		_impostos = DbConnectTax.getInstance().calculateTax(_itensLista);
		if (_impostos < 0)
			throw new NotValidNFException();
	}
	
	public String printNF() {
		String elaborationNF;
		elaborationNF = "NF in elaboration\n"
				+ "IV List:\n";
		for (int i = 0; i < _itensLista.size(); i++) {
			elaborationNF = elaborationNF + _itensLista.get(i).getName() + ", " + 
					_itensLista.get(i).getQuantity() + " unidades\n";
		};
		return elaborationNF;
	}
	
	public float getValue() {
		return _valor;
	}
	
	public float getImposto() {
		return _impostos;
	}
	public ArrayList<ItemDeVenda> getItemsList() {
		return _itensLista;
	}

	

}
