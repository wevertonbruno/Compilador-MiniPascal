package AST;

public class nodeTipoSimples extends nodeTipo{
public byte tipo;
public void visit (Visitor v){
	 v.visitTipoSimples(this);
	 }

}
