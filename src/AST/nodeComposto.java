package AST;

public class nodeComposto extends nodeComando{
public nodeComando lista;

public void visit (Visitor v){
	 v.visitComposto(this);
	 }

}
