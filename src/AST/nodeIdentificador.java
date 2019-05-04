package AST;
import analizadores.*;

public class nodeIdentificador extends Token {
	public nodeDeclaracao decl;
	
	public nodeIdentificador(String spell) {
		this.spelling = spell;
	}
	public nodeIdentificador() {
		
	}
	
	public void visit (Visitor v){
		 v.visitIdentificador(this);
		 }

}
