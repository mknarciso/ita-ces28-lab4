package ps;

import java.util.ArrayList;
import java.util.List;

public class DbPS {
	List<PS> dados = new ArrayList<PS>();
	List<PS> getAll(){
		return null;
	}

	public void addPS(PS newProduct) {
		dados.add(newProduct);
		
	}
}
