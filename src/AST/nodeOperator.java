package AST;
import analizadores.*;

public class nodeOperator extends Token{
 public Token op;
 public nodeOperator(Token ct) {
	 this.op = ct;
 }
 
 public void visit (Visitor v){
	 v.visitOperator(this);
	 }

}
