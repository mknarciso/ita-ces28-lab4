package ps;

public class Servico extends PS {
	//Service Model
	public Servico(String nome, float preco, String setor, int categoriaTributaria, String outros){
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
		if (index<=_listaPS.size() && index>0){
			return _listaPS.get(index);
		}
		return null;
	}
}
