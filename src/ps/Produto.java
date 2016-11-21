package ps;

public class Produto extends PS {	
	
	protected Produto(String nome, float preco, String setor, int categoriaTributaria, String outros){
		super(nome, preco, setor, categoriaTributaria, outros);
	}
	
	@Override
	protected void addPS(String name){
		_listaPS.add(DbConnectPS.getInstance().getPS(name));		
	}
	
	@Override
	protected void removePS(PS ps){
		_listaPS.remove(ps);
	}
	
}
