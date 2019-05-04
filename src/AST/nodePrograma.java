package AST;

public class nodePrograma {
	public nodeIdentificador id;
	public nodeCorpo corpo;
	
	public void visit (Visitor v){
		 v.visitPrograma(this);
		 }


}
