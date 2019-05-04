package AST;

public class nodeCorpo {
	public nodeDeclaracao declarations;
	public nodeComando comandos;
	
	public void visit (Visitor v){
		 v.visitCorpo(this);
		 }

}
