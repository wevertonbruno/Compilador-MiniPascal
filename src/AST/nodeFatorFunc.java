package AST;
import analizadores.*;
import AST.nodeDeclaracao;

public class nodeFatorFunc extends nodeFator{
public nodeIdentificador id;
public nodeExpressao lista;
public nodeDeclaracao decl;

public void visit (Visitor v){
	 v.visitFatorFunc(this);
	 }

}
