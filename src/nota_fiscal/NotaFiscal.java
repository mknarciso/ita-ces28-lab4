package nota_fiscal;

import java.util.ArrayList;

public final class NotaFiscal {
	//immutable object
	private final int _id;
	private final float _valor;
	private final float _impostos;
	private final String _outros;
	private final ArrayList<ItemDeVenda> _itensLista;
	
	public int getId(){
		return _id;
	}
	public NotaFiscal(NFBuilder NF, int id) {
		_outros = "";
		_id = id;
		_valor = NF.getValue();
		_impostos = NF.getImposto();
		_itensLista = (ArrayList<ItemDeVenda>) NF.getItemsList();
		
	}
	
	public String printNF() {
		String finalNF;
		finalNF = "NF completa - ID " + this.getId() + "\n"
				+ "IV List:\n";
		for (int i = 0; i < _itensLista.size(); i++) {
			finalNF = finalNF + _itensLista.get(i).getName() + ", " + 
					_itensLista.get(i).getQuantity() + " unidades\n";
		};
		finalNF = finalNF + "Valor final: " + this.getValor() + "\n";
		finalNF = finalNF + "Impostos calculados: " + this.getImposto() + "\n";
		
		return finalNF;
	}
	private float getValor() {
		return _valor;
	}
	
	private float getImposto() {
		return _impostos;
	}

}
