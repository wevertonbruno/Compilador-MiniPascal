package AST;
import Runtime.RuntimeEntity;

public class nodeDecProcedimento extends nodeDeclaracao {
	public nodeIdentificador id;
	public nodeParametro lista;
	//public nodeTipo tipo;
	public nodeCorpo corpo;
	public RuntimeEntity entity;
	public void visit (Visitor v){
		 v.visitDecProcedimento(this);
		 }


}
