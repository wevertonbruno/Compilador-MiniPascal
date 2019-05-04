package AST;
import analizadores.*;

public class nodeTipoAgregado extends nodeTipo{
public Token intLeft, intRight;
public nodeTipo tipo;

public void visit (Visitor v){
	 v.visitTipoAgregado(this);
	 }

}
