package AST;

public class nodeTermo extends nodeExpressao{
public nodeFator fator, next;
public nodeOperator op;

public nodeTermo(nodeFator f1, nodeFator f2, nodeOperator op) {
	this.fator = f1;
	this.next = f2;
	this.op = op;
	
}

public void visit (Visitor v){
	 v.visitTermo(this);
	 }

}
