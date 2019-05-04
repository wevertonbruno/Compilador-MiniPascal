package AST;

public class listaDeclaracao extends nodeDeclaracao{
	public nodeExpressao exp;
	public nodeDeclaracao next;
	
	public void visit (Visitor v){
		 v.visitListaDeclaracao(this);
		 }

}
