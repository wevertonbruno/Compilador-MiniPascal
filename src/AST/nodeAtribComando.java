package AST;

public class nodeAtribComando extends nodeComando{
public nodeVariavel var;
public nodeExpressao expressao;

public void visit (Visitor v){
	 v.visitAtribComando(this);
	 }

}
