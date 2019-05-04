package AST;

public class nodeExpressaoSimples extends nodeExpressao {
public nodeTermo termo,next;
public nodeOperator op;

public void visit (Visitor v){
	 v.visitExpressaoSimples(this);
	 }

}
