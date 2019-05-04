package AST;

public class nodeIfComando extends nodeComando{
public nodeExpressao expressao;
public nodeComando comandoIf, comandoElse;

public void visit (Visitor v){
	 v.visitIfComando(this);
	 }

}
