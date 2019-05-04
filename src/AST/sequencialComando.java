package AST;

public class sequencialComando extends nodeComando{
public nodeComando C1,C2;

public sequencialComando(nodeComando c1, nodeComando c2) {
	this.C1 = c1;
	this.C2 = c2;
	}

public void visit (Visitor v){
	 v.visitSequencialComando(this);
	 }

}
