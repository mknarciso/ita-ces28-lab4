package ps;

import java.text.DecimalFormat;

public class PsPrinter implements psVisitor {
	//PS Printer uses Visitor Pattern to travel through PS tree structure
	//and prints its nodes.
	
	private DecimalFormat df = new DecimalFormat("######.00");
	private String output;
	public PsPrinter(){
		output ="";
	}
	@Override
	public void visitar(PS ps) {
		output = output +"====>"+String.format("%12s",ps.getName())+"   Preço: "+String.format("%,10.2f",ps.getPrice())+ "   Imposto: "+String.format("%,10.2f",ps._imposto)+"\n";
	}
	public String getString() {
		return output;
	}

}
