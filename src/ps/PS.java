package ps;

public abstract class PS {
	protected String _nome;
	protected float _preco;
	protected String _setor;
	protected int _categoriaTributaria;
	protected String _outros;
	
	public PS(String nome, float preco, String setor, int categoriaTributaria, String outros){
		_nome=nome;
		_preco=preco;
		_setor=setor;
		_categoriaTributaria=categoriaTributaria;
		_outros=outros;
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
}
