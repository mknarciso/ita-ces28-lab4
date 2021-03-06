package ps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbPS { //Product and Service database "Mock"
	private List<PS> dados = new ArrayList<PS>();
	protected List<PS> getAll(){
		return null;
	}

   protected void newPS(String type,String nome, float preco, String setor, int categoriaTributaria, String outros){
	   PS newProduct;
	   if(type.equals("Produto"))
		   newProduct = new Produto(nome, preco, setor, categoriaTributaria, outros);
	   else
		   newProduct = new Servico(nome, preco, setor, categoriaTributaria, outros);
	   dados.add(newProduct);
   }
	
	protected PS getPS(String name){
		//System.out.println(dados.size()+" PS no banco de dados.");
		for(int i=0;i<dados.size();i++){
			if (dados.get(i)._nome.equals(name)){
				PS result = dados.get(i);
				if(result instanceof Produto)
					return new ProdutoFinal(result._nome, result._preco, result._setor, result._categoriaTributaria, result._outros, Collections.unmodifiableList(result._listaPS));
				else
					return new ServicoFinal(result._nome, result._preco, result._setor, result._categoriaTributaria, result._outros, Collections.unmodifiableList(result._listaPS));

			}
		}
		return null;
	}
	
	private PS getPSNonFinal(String name){
		for(int i=0;i<dados.size();i++){
			if (dados.get(i)._nome.equals(name)){
				return dados.get(i);
			}
		}
		return null;
	}

	public void addToPSSubPS(String main, String sub) throws Exception {
		PS editing;
		editing = this.getPSNonFinal(main);
		editing.addPS(sub);
	}

}

