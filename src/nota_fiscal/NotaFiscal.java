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
		_itensLista = NF.getItemsList();
		
	}

}
