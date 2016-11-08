package ps;

public class ServiceNode extends PS {
	
	public ServiceNode(String nome, float preco, String setor, int categoriaTributaria, String outros){
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
}
