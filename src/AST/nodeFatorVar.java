package AST;

public class nodeFatorVar extends nodeFator{
public nodeVariavel var;

public void visit (Visitor v){
	 v.visitFatorVar(this);
	 }

}
