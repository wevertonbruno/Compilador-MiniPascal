package AST;
import analizadores.*;

public class nodeParametro extends nodeDeclaracao{
	public nodeIdentificador id;
	public nodeTipo tipo;
	public nodeParametro next;

public void visit (Visitor v){
	 v.visitParametro(this);
	 }

}
