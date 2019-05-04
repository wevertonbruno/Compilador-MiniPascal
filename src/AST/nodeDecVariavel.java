package AST;

public class nodeDecVariavel extends nodeDeclaracao{
public nodeIdentificador id;
public nodeDeclaracao next;
//public nodeIdentificador lista;
public nodeTipo tipo;

public void visit (Visitor v){
	 v.visitDecVariavel(this);
	 }

}
