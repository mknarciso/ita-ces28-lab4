package nota_fiscal;

import java.util.ArrayList;

public class NotaFiscal {
	private int _id;
	private float _valor;
	private float _impostos;
	private String _outros;
	private ArrayList<ItemDeVenda> _itensLista;
	
	NotaFiscal() {};
	
	public int getId(){
		return _id;
	}
}
