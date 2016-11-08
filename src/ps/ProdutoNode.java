package ps;

import java.util.ArrayList;

public class ProdutoNode extends PS {
	private ArrayList<PS> _listaPS;
	
	
	ProdutoNode(String nome, float preco, String setor, int categoriaTributaria, String outros){
		super(nome, preco, setor, categoriaTributaria, outros);
	}
	
	@Override
	protected void addPS(PS ps){
		_listaPS.add(ps);		
	}
	
	@Override
	protected void removePS(PS ps){
		_listaPS.remove(ps);
	}
	
	@Override
	protected PS getPS(int index){
		if (index<=_listaPS.size() && index>0){
			return _listaPS.get(index);
		}
		return null;
	}
	
	public void aceitarVisitante(psVisitor visitor) {
        visitor.visitar(_listaPS,this);
    }
}