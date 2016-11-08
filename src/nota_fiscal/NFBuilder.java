package nota_fiscal;

import db.DbConnectNF;
import db.NFAlreadyValidatedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NFBuilder {
	//nota fiscal em elaboração
	
	private int _valor;
	private float _impostos;
	private String _outros;
	private ArrayList<ItemDeVenda> _itensLista;
	private boolean _validate = false;
	
	NFBuilder(String productServ, int quantity) {
		addItemDeVenda(productServ, quantity);
		
	}
	public NotaFiscal saveNF() {
		try {
			validateNF();
			try { 
				int id = DbConnectNF.getInstance().generateID(this);
				NotaFiscal notaFiscal = new NotaFiscal(this, id);
				DbConnectNF.getInstance().persistNF(notaFiscal);
				_validate = true;
				return notaFiscal;
			} catch (NFAlreadyValidatedException e) {
				System.out.println("NF already validated");
				e.printStackTrace();
			}			
		} catch (NotValidNFException e) {
			System.out.println("NF not validated");
		}
		return null;
		
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
	public void validateNF() throws NotValidNFException {
		try {
			_impostos = DbConnectTax.getInstance().calculateTax(_itensLista);
		} catch (Exception e) {
			throw new NotValidNFException();			
		}
		
	}
	
	public String printNF() {
		String elaborationNF;
		elaborationNF = "NF em elaboração\n"
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
	public List<ItemDeVenda> getItemsList() {
		return Collections.unmodifiableList(_itensLista);
	}
	public boolean isValidated() {
		return _validate;
	}

	

}
