package AST;

public interface Visitor {
	public void visitListaDeclaracao(listaDeclaracao ld);
	public void visitListaExpressao(listaExpressao le);
	public void visitAtribComando(nodeAtribComando a);
	public void visitComando(nodeComando c);
	public void visitComposto(nodeComposto c);
	public void visitCorpo(nodeCorpo c);
	public void visitDecFuncao(nodeDecFuncao df);
	public void visitDeclaracao(nodeDeclaracao d);
	public void visitDecProcedimento(nodeDecProcedimento dp);
	public void visitDecVariavel(nodeDecVariavel dv);
	public void visitEn(nodeEn e);
	public void visitEo(nodeEo e);
	public void visitSeletor(nodeSeletor s);
	public void visitExpParenteses(nodeExpParenteses e);
	public void visitExpressao(nodeExpressao e);
	public void visitExpressaoSimples(nodeExpressaoSimples e);
	public void visitFator(nodeFator f);
	public void visitFatorFunc(nodeFatorFunc f);
	public void visitFatorVar(nodeFatorVar f);
	public void visitIdentificador(nodeIdentificador i);
	public void visitIfComando(nodeIfComando c);
	public void visitLiteral(nodeLiteral l);
	public void visitOperator(nodeOperator o);
	public void visitParametro(nodeParametro p);
	public void visitPComando(nodePComando p);
	public void visitPrograma(nodePrograma p);
	public void visitTermo(nodeTermo t);
	public void visitTipo(nodeTipo t);
	public void visitTipoAgregado(nodeTipoAgregado t);
	public void visitTipoSimples(nodeTipoSimples t);
	public void visitVariavel(nodeVariavel v);
	public void visitWhileComando(nodeWhileComando w);
	public void visitSequencialComando(sequencialComando sc);
	public void visitSequencialDeclaration(sequencialDeclaration sd);
	public void visitSequencialExpressao(sequencialExpressao se);
	public void visitSequencialIdentificador(sequencialIdentificador si);
	public void visitSequencialParametro(sequencialParametro sp);
	
}
