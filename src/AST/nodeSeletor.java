package AST;

public class nodeSeletor extends nodeExpressao{
public nodeExpressao lista;

public nodeSeletor(nodeExpressao s) {
	this.lista = s;
}

public void visit(Visitor v) {
	v.visitSeletor(this);
}
}
