package nota_fiscal;

import java.util.List;

public final class NotaFiscal {
	//immutable object: Nota Fiscal
	//This is a final format of a validated and persisted NF
	private final int _id;
	private final double _valor;
	private final double _impostos;
	private final String _outros;
	private final List<ItemDeVenda> _itensLista;
	
	public int getId(){
		return _id;
	}
	protected NotaFiscal(NFBuilder NF, int id) {
		_outros = "";
		_id = id;
		_valor = NF.getValue();
		_impostos = NF.getImposto();
		_itensLista = NF.getItemsList();
		
	}
	
	public String printNF() {
		//print final NF
		String finalNF;
		finalNF = "NF completa - ID " + this.getId() + "\n"
				+ "IV List:\n";
		for (int i = 0; i < _itensLista.size(); i++) {
			finalNF = finalNF + _itensLista.get(i).printIV();
		};
		finalNF = finalNF + "Valor final: " + this.getValor() + "\n";
		finalNF = finalNF + "Impostos calculados: " + this.getImposto() + "\n";
		
		return finalNF;
	}
	private double getValor() {
		return _valor;
	}
	
	private double getImposto() {
		return _impostos;
	}
	public String getOutros() {
		return _outros;
	}

}
