package OperationsAST;
import AST.*;
import analizadores.Token;
import analizadores.View;

/* Classe responsável por imprimir a AST
 * Implementa a interface Visitor
 * 
 * */


public class Printer implements Visitor{
	
	View view;
	private int coluna = 0;
	
	public Printer(View v){
		view = v;
	}
	
	public void print (nodePrograma p) {
		if(p != null && view.Ef == 0) {
		view.tA.append ("*********[ Starting the AST Printing Process ]**********" + "\n");
		 p.visit (this);
		 view.tA.append("\n"+"[End of AST]");
		}else
			view.tA.append("Error during Parser or Scanner");		 }
	
	private void indent() {
	      for (int j=0; j<coluna; j++) 
	    	  view.tA.append ("|");
	   }


	@Override
	public void visitListaDeclaracao(listaDeclaracao ld) {
		if(ld != null) {
			if(ld.exp != null) ld.exp.visit(this);
			if(ld.next != null) {
				coluna++;
				indent();
				ld.next.visit(this);
				coluna--;
			}
		}
	}

	@Override
	public void visitListaExpressao(listaExpressao le) {
		if(le != null) {
			if(le.exp != null) le.exp.visit(this);
			if(le.next != null) {
				coluna++;
				indent();
				le.next.visit(this);
				coluna--;
			}
		}
	}

	@Override
	public void visitAtribComando(nodeAtribComando a) {
		if(a != null) {
			//indent();
			view.tA.append(":=" + "\n");
			if(a.var.id != null)
				indent();
				view.tA.append("$"+a.var.id.spelling + "\n");
			if(a.var.exp != null) {
				a.var.exp.visit(this);
			}
			
			if(a.expressao != null) {
				a.expressao.visit(this);
			}
		}
		
	}

	@Override
	public void visitComando(nodeComando c) {
		if(c != null) {
			
			if(c instanceof nodeIfComando)
				((nodeIfComando)c).visit(this);
			else if(c instanceof nodeWhileComando)
				((nodeWhileComando)c).visit(this);
			else if(c instanceof nodeAtribComando)
				((nodeAtribComando)c).visit(this);
			else if(c instanceof nodeComposto)
				((nodeComposto)c).visit(this);
			else if(c instanceof nodePComando)
				((nodePComando)c).visit(this);
			else
				((sequencialComando)c).visit(this);
		}
			
		
	}

	@Override
	public void visitComposto(nodeComposto c) { // metodo inutilizavel aparentemente
		if(c != null) {
			if(c.lista != null) {
				c.lista.visit(this);
			}
			
		}
	}

	@Override
	public void visitCorpo(nodeCorpo c) {
		if(c != null) {
			if(c.declarations != null) {
				//System.out.println("/#");
					c.declarations.visit(this);
					//System.out.println("#/");
			}
			
			if(c.comandos != null) 
				c.comandos.visit(this);
			
		}
	}

	@Override
	public void visitDecFuncao(nodeDecFuncao df) {
		if(df != null) {
			if(df.id != null) df.id.visit(this);
			
			if(df.lista != null) {
				indent();
				df.lista.visit(this);
			}
			if(df.tipo != null) {
				
				df.tipo.visit(this);
			}
			
			if(df.corpo != null) {
				indent();
				df.corpo.visit(this);
			}
		}
		 
		
	}

	@Override
	public void visitDeclaracao(nodeDeclaracao d) {
		if(d != null) {
			if(d instanceof nodeDecVariavel) {
				((nodeDecVariavel)d).visit(this);
			}
			else if( d instanceof nodeDecProcedimento)
				((nodeDecProcedimento)d).visit(this);
			else if(d instanceof nodeDecFuncao)
				((nodeDecFuncao)d).visit(this);
			else if(d instanceof sequencialDeclaration)
				((sequencialDeclaration)d).visit(this);
		}	
	}

	@Override
	public void visitDecProcedimento(nodeDecProcedimento dp) {
		if(dp != null) {
			if(dp.id != null) {
				dp.id.visit(this);
			}
			
			if(dp.lista != null) {
				indent();
				dp.lista.visit(this);
			}
			
			if(dp.corpo != null) 
				{
				indent();
				dp.corpo.visit(this);
				}
		}
		 
		
	}

	@Override
	public void visitDecVariavel(nodeDecVariavel dv) {
		 if(dv != null) {
			 if(dv.id != null) dv.id.visit(this);
			 if(dv.next != null) {
				 coluna++;
				 indent();
				 dv.next.visit(this);
				 coluna--;
			 }
			 if(dv.tipo != null) dv.tipo.visit(this);
		 }
		
	}

	@Override
	public void visitEn(nodeEn e) {
		 if(e != null) {
			if(e.name != null) {
				 //coluna++;
				 indent();
				//System.out.println(e.name.spelling);
				 e.name.visit(this);
				//coluna--;
			}
		 }
		
	}

	@Override
	public void visitEo(nodeEo e) {
		 coluna++;
		 indent();//talvex
		 if(e.op != null) e.op.visit(this);
		 if(e.left != null) e.left.visit(this);
		 if(e.right != null) e.right.visit(this);
		 coluna--;
		
	}

	@Override
	public void visitExpParenteses(nodeExpParenteses e) {
		 if(e != null) {
			 e.expressao.visit(this);
		 }
		
	}

	@Override
	public void visitExpressao(nodeExpressao e) {
		 if(e != null) {
			 if (e instanceof nodeExpressaoSimples)
				 ((nodeExpressaoSimples)e).visit(this);
			 else if(e instanceof nodeExpParenteses)
				 ((nodeExpParenteses)e).visit(this);
			 else if( e instanceof sequencialExpressao)
				 ((sequencialExpressao)e).visit(this);
			 else if(e instanceof nodeEn)
				 ((nodeEn)e).visit(this);
			 else if(e instanceof nodeEo)
				 ((nodeEo)e).visit(this);
			 else if (e instanceof nodeTermo)
				 ((nodeTermo)e).visit(this);
			 else if(e instanceof nodeFator)
				 ((nodeFator)e).visit(this);
			 else if(e instanceof nodeSeletor) {
				 view.tA.append("[" + "\n");
				 ((nodeSeletor)e).visit(this);
				 view.tA.append("]" + "\n");
			 }
		 }
		
	}

	@Override
	public void visitExpressaoSimples(nodeExpressaoSimples e) {
		if(e != null) {
			if(e.op != null) e.op.visit(this);
			if(e.termo != null) e.termo.visit(this);
			if(e.next != null) e.next.visit(this);
		}
	}

	@Override
	public void visitFator(nodeFator f) {
		 if(f != null) {
			 if(f instanceof nodeFatorFunc)
				 ((nodeFatorFunc)f).visit(this);
			 else if(f instanceof nodeFatorVar)
				 ((nodeFatorVar)f).visit(this);
		 }
	}

	@Override
	public void visitFatorFunc(nodeFatorFunc f) {
		 if(f != null) {
			if(f.id != null) {
				indent();
				view.tA.append(f.id.spelling + "\n");
				}
			
			 if(f.lista != null) {
				 //indent();
				 f.lista.visit(this);
			 }
		 }
		
	}

	@Override
	public void visitFatorVar(nodeFatorVar f) {
		if(f != null) {
			if(f.var != null) f.var.visit(this);
		}
	}

	@Override
	public void visitIdentificador(nodeIdentificador i) {
		//indent();
		view.tA.append(i.spelling + "\n");
	}

	@Override
	public void visitIfComando(nodeIfComando c) {
		if(c != null) {
			
			if(c.comandoIf != null) {
				indent();
				view.tA.append("if" + "\n");
				
				c.expressao.visit(this);
				indent();
				view.tA.append("then" + "\n");
				indent();
				c.comandoIf.visit(this);
				if(c.comandoElse != null) {
					indent();
					view.tA.append("else" + "\n");
					c.comandoElse.visit(this);
				}
			}
		} 

	}

	@Override
	public void visitLiteral(nodeLiteral l) {
		view.tA.append(l.spelling + "\n");
		
	}

	@Override
	public void visitOperator(nodeOperator o) {
		view.tA.append(o.op.spelling + "\n");
	}

	@Override
	public void visitParametro(nodeParametro p) {
		 if(p != null) {
			 if(p.id != null) p.id.visit(this);
			 if(p.tipo != null) p.tipo.visit(this);
			 if(p.next != null) p.next.visit(this);	
		 }
	}

	@Override
	public void visitPComando(nodePComando p) {
		if(p != null) {
			if(p.id != null) p.id.visit(this);
			
			if(p.lista != null) {
				indent();
				p.lista.visit(this);
			}
		}
	}

	@Override
	public void visitPrograma(nodePrograma p) {
		if(p != null) {
			view.tA.append(p.id.spelling + "\n");
			if(p.corpo != null)
				p.corpo.visit(this);
		}
	}

	@Override
	public void visitTermo(nodeTermo t) {
		 if(t != null) {
			 coluna++;
			 indent();
			 t.op.visit(this);
			 if(t.fator != null) t.fator.visit(this);
			 if(t.next != null) t.next.visit(this);
			 coluna--;
		 }
	}

	@Override
	public void visitTipo(nodeTipo t) {
		 if(t != null) {
			 if(t instanceof nodeTipoSimples)
				 ((nodeTipoSimples)t).visit(this);
			 else 
				 ((nodeTipoAgregado)t).visit(this);
		 }
		
	}

	@Override
	public void visitTipoAgregado(nodeTipoAgregado t) {
		 if(t != null) {
			 coluna++;
			 indent();
			 view.tA.append("array" + "\n");
			 indent();
			 view.tA.append(t.intLeft.spelling + "\n");
			 indent();
			 view.tA.append(t.intRight.spelling + "\n");
			 t.tipo.visit(this);
		 }
	}

	@Override
	public void visitTipoSimples(nodeTipoSimples t) {
		 if(t != null) {
			 coluna++;
			 indent();
			 view.tA.append(Token.SPELLINGS[t.tipo] + "\n");
			 coluna--;
		 }
		
	}

	@Override
	public void visitVariavel(nodeVariavel v) {
		 if(v != null) {
			 coluna++;
			 indent();
			 view.tA.append(v.id.spelling + "\n");
			 coluna--;
			 if(v.exp != null) v.exp.visit(this);
		 }
		
	}

	@Override
	public void visitWhileComando(nodeWhileComando w) {
		if(w != null) {
			view.tA.append("while" + "\n");
		 w.expressao.visit(this);
		 indent();
		 view.tA.append("do" + "\n");
		 //indent();
		 w.comando.visit(this);
		}
	}

	@Override
	public void visitSequencialComando(sequencialComando sc) {
		 if(sc != null) {
			 if(sc.C1 != null) {
				 sc.C1.visit(this);
			 }
			 if(sc.C2 != null) {
				 coluna++;
				 indent();
				 sc.C2.visit(this);
				 coluna--;
			 }
		 }
		
	}

	@Override
	public void visitSequencialDeclaration(sequencialDeclaration sd) {
		if(sd != null) {
			 if(sd.D1 != null) sd.D1.visit(this);
			 if(sd.D2 != null) {
				 coluna++;
				 indent();
				 sd.D2.visit(this);
				 coluna--;
			 }
		 }
		
	}

	@Override
	public void visitSequencialExpressao(sequencialExpressao se) {
		if(se != null) {
			if(se.exp != null) se.exp.visit(this);
			 if(se.next != null) {
				 coluna++;
				// indent();
				 se.next.visit(this);
				 coluna--;
			 }
		 }
	}

	@Override
	public void visitSequencialIdentificador(sequencialIdentificador si) {
		if(si != null) {
			
			if(si.I1 != null) si.I1.visit(this);			
			 if(si.I2 != null) {
				 	coluna++;
					indent();
					si.I2.visit(this);
					coluna--;
			}
		 }
	}
	@Override
	public void visitSequencialParametro(sequencialParametro sp) {
		if(sp != null) {
			if(sp.P1 != null) sp.P1.visit(this);
			 if(sp.P2 != null) {
				coluna++;
				indent();
				sp.P2.visit(this);
				coluna--;
			 }
		 }
	}

	@Override
	public void visitSeletor(nodeSeletor s) {
		if(s != null) {
			if(s.lista != null) s.lista.visit(this);
		}
	}
}
