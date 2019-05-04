package AST;

public class sequencialParametro extends nodeParametro{
	public nodeParametro P1, P2;
	public sequencialParametro(nodeParametro p1, nodeParametro p2) {
		this.P1 = p1;
		this.P2 = p2;
	}
	
	public void visit (Visitor v){
		 v.visitSequencialParametro(this);
		 }

}
