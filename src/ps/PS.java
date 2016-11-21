package ps;

import java.util.ArrayList;

public abstract class PS {
	//PS is the abstraction of product and service (Abstract Coupling Pattern)
	//PS is a composite in a tree structure
	protected String _nome;
	protected float _preco;
	protected double _imposto;
	protected String _setor;
	protected int _categoriaTributaria;
	protected String _outros;
	protected ArrayList<PS> _listaPS;
	protected boolean impostoFinal;
	
	protected PS(String nome, float preco, String setor, int categoriaTributaria, String outros){
		_nome=nome;
		_preco=preco;
		_setor=setor;
		_categoriaTributaria=categoriaTributaria;
		_outros=outros;
		_listaPS = new ArrayList<PS>();
		impostoFinal = false;
	}
	

	//Add new PS to composite
	protected void addPS(String name) throws Exception{
		throw new Exception("Não pode inserir Produto/Serviço em: "
                + this._nome + " - Já é o Produto/Serviço final");
	}
	//Remove PS from composite
	protected void removePS(PS ps) throws Exception{
		throw new Exception("Não pode remover Produto/Serviço em: "
                + this._nome + " - Já é o Produto/Serviço final");
	}
	//get PS from composite
	public PS getPS(int index){
		if (index<=_listaPS.size() && index>0){
			return _listaPS.get(index);
		}
		return null;

	}
	//Accepts Visitor (Visitor Pattern)
	public void aceitarVisitante(psVisitor visitor) {
		for (int i = 0; i < _listaPS.size(); i++) {
			_listaPS.get(i).aceitarVisitante(visitor);
		}
        visitor.visitar(this);
        addImposto(visitor.getLast());
    }
	public void preAceitarVisitante(psVisitor visitor) {
        visitor.visitar(this);
		for (int i = 0; i < _listaPS.size(); i++) {
			_listaPS.get(i).preAceitarVisitante(visitor);
		}
    }
	public String getName() {
		return _nome;
	}

	public float getPrice() {
		PriceCalculator pc = new PriceCalculator();
		this.aceitarVisitante(pc);
		return (float)pc.getTotal();
	}
	public String printPS(){
		PsPrinter prt = new PsPrinter();
		this.preAceitarVisitante(prt);
		return prt.getString();
	}
	
	public String getSetor() {
		return _setor;
	}
	
	public int getCategoriaTributaria() {
		return _categoriaTributaria;
	}
	
	public String getOutros() {
		return _outros;
	}
	
	public ArrayList<PS> getLista(){
		return _listaPS;
	}

	private void addImposto(double last) {
		_imposto = _imposto + last;
		
	}
	
}
	
	

