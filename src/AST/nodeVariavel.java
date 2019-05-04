package AST;
import analizadores.*;

public class nodeVariavel {
public nodeIdentificador id;
public nodeExpressao exp;

public void visit (Visitor v){
	 v.visitVariavel(this);
	 }

}
