package AST;
import Runtime.RuntimeEntity;

public abstract class nodeDeclaracao {
	public abstract void visit (Visitor v);
	public RuntimeEntity entity;
	public int level;
	public int posicao;
	public int size;

}