package AST;
import analizadores.*;

public class nodeEn extends nodeExpressao {
public nodeIdentificador name;
public int level;
public int posicao;
public int size;
//public byte tipo;

	public nodeEn(nodeIdentificador n) {
		this.name = n;
	}
	public void visit (Visitor v){
		 v.visitEn(this);
		 }

}
