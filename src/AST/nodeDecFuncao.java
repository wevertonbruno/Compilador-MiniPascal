package AST;

public class nodeDecFuncao extends nodeDeclaracao{
	public nodeIdentificador id;
	public nodeParametro lista;
	public nodeTipo tipo;
	public nodeCorpo corpo;
	
	public void visit (Visitor v){
		 v.visitDecFuncao(this);
		 }


}
