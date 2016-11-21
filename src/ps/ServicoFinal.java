package ps;

import java.util.ArrayList;
import java.util.List;

public class ServicoFinal extends PS {	
//Service Final is a immutable object
	protected ServicoFinal(String nome, float preco, String setor, int categoriaTributaria, String outros, List<PS> arvore){

		super(nome, preco, setor, categoriaTributaria, outros);
		_listaPS = (ArrayList<PS>) arvore;
	}
	
}
