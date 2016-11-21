package ps;

import java.util.List;

public class ProdutoFinal extends PS {	
	//Produto Final is a immutable object
	protected ProdutoFinal(String nome, float preco, String setor, int categoriaTributaria, String outros, List<PS> arvore){

		super(nome, preco, setor, categoriaTributaria, outros);
		_listaPS = arvore;
	}
	
}
