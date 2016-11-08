package ps;

import java.util.ArrayList;
import java.util.List;

public class DbPS {
	List<PS> dados = new ArrayList<PS>();
	List<PS> getAll(){
		return null;
	}

	protected void addPS(PS newProduct) {
		dados.add(newProduct);
	}
	protected PS getPS(String name){
		for(int i=0;i<dados.size();i++){
			if (dados.get(i)._nome.equals(name)){
				PS result = dados.get(i);
				return new ProdutoFinal(result._nome, result._preco, result._setor, result._categoriaTributaria, result._outros, result._listaPS);
			}
		}
		return null;
	}

}

