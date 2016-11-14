package ps;

public class Produto extends PS {	
	//Product Model
	
	public Produto(String nome, float preco, String setor, int categoriaTributaria, String outros){
		super(nome, preco, setor, categoriaTributaria, outros);
	}
	
	@Override
	public void addPS(String name){
		_listaPS.add(DbConnectPS.getInstance().getPS(name));		
	}
	
	@Override
	public void removePS(PS ps){
		_listaPS.remove(ps);
	}
	
	@Override
	public PS getPS(int index){
		if (index <=_listaPS.size() && index > 0){
			return _listaPS.get(index);
		}
		return null;
	}
}
