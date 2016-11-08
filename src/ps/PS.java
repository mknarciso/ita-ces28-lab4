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
	}
	
	protected void addPS(PS ps) throws Exception{
		throw new Exception("Não pode inserir Produto/Serviço em: "
                + this._nome + " - Já é o Produto/Serviço final");
	}
	
	protected void removePS(PS ps) throws Exception{
		throw new Exception("Não pode remover Produto/Serviço em: "
                + this._nome + " - Já é o Produto/Serviço final");
	}
	
	protected PS getPS(int index) throws Exception{
		throw new Exception("Não pode obter Produto/Serviço em: "
                + this._nome + " - É o Produto/Serviço final");
	}
	
	protected void aceitarVisitante(psVisitor visitor) {
        visitor.visitar(_listaPS,this);
    }
	
	
}
