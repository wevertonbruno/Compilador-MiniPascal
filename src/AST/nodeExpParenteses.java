package AST;

public class nodeExpParenteses extends nodeFator {
public nodeExpressao expressao;

public void visit (Visitor v){
	 v.visitExpParenteses(this);
	 }

}
