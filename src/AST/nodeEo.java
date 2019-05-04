package AST;

public class nodeEo extends nodeExpressao{
	public nodeExpressao left, right;
	public nodeOperator op;
	//public byte tipo;
	
	
	public nodeEo(nodeExpressao l, nodeOperator op, nodeExpressao r) {
		this.left = l;
		this.right = r;
		this.op = op;
	}
	
	public void visit (Visitor v){
		 v.visitEo(this);
		 }


}
