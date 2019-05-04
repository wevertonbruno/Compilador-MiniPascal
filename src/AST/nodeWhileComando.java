package AST;

public class nodeWhileComando extends nodeComando{
public nodeExpressao expressao;
public nodeComando comando;

public void visit (Visitor v){
	 v.visitWhileComando(this);
	 }

}
