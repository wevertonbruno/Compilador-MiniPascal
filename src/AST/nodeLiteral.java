package AST;
import analizadores.*;

public class nodeLiteral extends nodeIdentificador{
	public void visit (Visitor v){
		 v.visitLiteral(this);
		 }

}
