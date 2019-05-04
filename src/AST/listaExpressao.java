package AST;

public class listaExpressao extends nodeExpressao{
public nodeExpressao exp, next;

public void visit (Visitor v){
	 v.visitListaExpressao(this);
	 }

}
