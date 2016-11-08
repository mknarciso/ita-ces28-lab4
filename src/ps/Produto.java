package ps;

public class Produto extends PS {	
	
	public Produto(String nome, float preco, String setor, int categoriaTributaria, String outros){
		super(nome, preco, setor, categoriaTributaria, outros);
	}
	
	@Override
	public void addPS(PS ps){
		_listaPS.add(ps);		
	}
	
	@Override
	public void removePS(PS ps){
		_listaPS.remove(ps);
	}
	
	@Override
	public PS getPS(int index){
		if (index<=_listaPS.size() && index>0){
			return _listaPS.get(index);
		}
		return null;
	}
}
