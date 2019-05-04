package AST;

public class sequencialExpressao extends nodeExpressao {
public nodeExpressao exp, next;

public sequencialExpressao(nodeExpressao e1, nodeExpressao e2) {
	this.exp = e1;
	this.next = e2;
}

public void visit (Visitor v){
	 v.visitSequencialExpressao(this);
	 }

}
