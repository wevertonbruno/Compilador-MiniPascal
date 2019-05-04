package AST;

public class nodePComando extends nodeComando{ //chamada de procedimento
public nodeIdentificador id;
public nodeExpressao lista;

public void visit (Visitor v){
	 v.visitPComando(this);
	 }

}
