package ps;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFinal extends PS {	
	
	public ProdutoFinal(String nome, float preco, String setor, int categoriaTributaria, String outros, List<PS> arvore){
		super(nome, preco, setor, categoriaTributaria, outros);
		_listaPS = (ArrayList<PS>) arvore;
	}
	
	@Override
	public PS getPS(int index){
		if (index<=_listaPS.size() && index>0){
			return _listaPS.get(index);
		}
		return null;
	}
}
