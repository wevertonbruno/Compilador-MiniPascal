package AST;

public class sequencialDeclaration extends nodeDeclaracao {
 public nodeDeclaracao D1,D2;
 public sequencialDeclaration(nodeDeclaracao d1, nodeDeclaracao d2) {
	 this.D1 = d1;
	 this.D2 = d2;
 }
 
 public void visit (Visitor v){
	 v.visitSequencialDeclaration(this);
	 }

}
