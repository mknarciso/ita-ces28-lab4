package nota_fiscal;

import db.DbConnectNF;
import db.DbConnectTax;
import db.NFAlreadyValidatedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NFBuilder {
	//nota fiscal em elabora��o
	
	private double _valor = 0;
	private float _impostos;
	private String _outros;
	private ArrayList<ItemDeVenda> _itensLista;
	private boolean _validate = false;
	
	public NFBuilder(String productServ, int quantity) {
		_itensLista = new ArrayList<ItemDeVenda>();
		addItemDeVenda(productServ, quantity);
		
	}
	public NotaFiscal saveNF() {
		try {
			validateNF();
			try { 
				int id = DbConnectNF.getInstance().generateID(this);
				//System.out.println("Id:"+id);
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
		//System.out.println(_itensLista);
		//System.out.println(itemDeVenda.getPrice());
		_itensLista.add(itemDeVenda);
		_valor += itemDeVenda.getPrice();
		
		
	}

	public void removeItemDeVenda(String productServ) {
		for(Iterator<ItemDeVenda> i = _itensLista.iterator(); i.hasNext();){
			ItemDeVenda atual = i.next();
			if(atual.getName().equals(productServ))
				i.remove();
		}
		/*_itensLista.forEach((item) -> {
			if (item.getName() == productServ)
				_itensLista.remove(item);
		});*/
	}
	
	private void validateNF() throws NotValidNFException {
		try {
			_impostos = DbConnectTax.getInstance().calculateTax(_itensLista);
			//System.out.println("Impostos Total:"+_impostos);
		} catch (Exception e) {
			throw new NotValidNFException();			
		}
		
	}
	
	public String printNF() {
		String elaborationNF;
		elaborationNF = "NF em elabora��o\n"
				+ "IV List:\n";
		for (int i = 0; i < _itensLista.size(); i++) {
			elaborationNF = elaborationNF + _itensLista.get(i).getName() + ", " + 
					_itensLista.get(i).getQuantity() + " unidades\n";
			
		};
		return elaborationNF;
	}
	
	public double getValue() {
		_valor = 0;
		_itensLista.forEach((item) -> {
			_valor = _valor + item.getPrice();
		});
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
