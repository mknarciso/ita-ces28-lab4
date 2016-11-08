package ps;

import java.util.ArrayList;

public abstract class PS {
	protected String _nome;
	protected float _preco;
	protected String _setor;
	protected int _categoriaTributaria;
	protected String _outros;
	protected ArrayList<PS> _listaPS;
	
	public PS(String nome, float preco, String setor, int categoriaTributaria, String outros){
		_nome=nome;
		_preco=preco;
		_setor=setor;
		_categoriaTributaria=categoriaTributaria;
		_outros=outros;
		_listaPS = new ArrayList<PS>();
	}
	
	protected void addPS(PS ps) throws Exception{
		throw new Exception("N�o pode inserir Produto/Servi�o em: "
                + this._nome + " - J� � o Produto/Servi�o final");
	}
	
	protected void removePS(PS ps) throws Exception{
		throw new Exception("N�o pode remover Produto/Servi�o em: "
                + this._nome + " - J� � o Produto/Servi�o final");
	}
	
	protected PS getPS(int index) throws Exception{
		throw new Exception("N�o pode obter Produto/Servi�o em: "
                + this._nome + " - � o Produto/Servi�o final");

	}
	
	public void aceitarVisitante(psVisitor visitor) {
		for (int i = 0; i < _listaPS.size(); i++) {
			_listaPS.get(i).aceitarVisitante(visitor);
		}
        visitor.visitar(this);
    }
	
	public String getName() {
		return _nome;
	}

	public float getPrice() {
		return _preco;
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
	
}
	
	

