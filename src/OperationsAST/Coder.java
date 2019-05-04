package OperationsAST;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import AST.*;
import Runtime.*;
import analizadores.Token;
import analizadores.View;


public class Coder implements Visitor{
	
	FileWriter arquivo;
	File codigo;
	Integer gs = 0;
	Frame currentFrame;
	Integer global = 0;
	Integer amount = 0;
	int currentLevel = -1;
	int label;
	Integer gsAux1 = 0;
	boolean running = false;
	View view;
	
	public Coder(View v) {
		view = v;
		this.codigo = new File(view.tO.getText());
		try {
		arquivo = new FileWriter(this.codigo);
		}catch(IOException e) {
			if(view.Of == 0) {
					view.Of = 1;
				}
		}
	}
	
	public void emit(byte op, int i, int n, String d) {
		try {
		arquivo.write(Instruction.getInstruction(op, i, n, d));
		}catch(IOException e) {
			e.getMessage();
		}
	}
	
	private void labelGenerator() {
		try {
			arquivo.write("L_00" + label + ": \r\n\r\n");
			label++;
			}catch(IOException e) {
				e.getMessage();
			}
	}
	private void labelGenerator(String str) {
		try {
			arquivo.write(str + ": \r\n\r\n");
			}catch(IOException e) {
				e.getMessage();
			}
	}
	
	private void encodeAssign(nodeIdentificador v, int s) {
		if(v.decl.entity != null) {
		RuntimeEntity entity = v.decl.entity;
		EntityAdress e = ((KnowAdress)entity).adress;
		emit(Instruction.STORE, displayRegister(currentLevel, e.level), s , e.displacement.toString());
		}
	}
	
	private void encodeFetch(nodeIdentificador v, int s) {
		RuntimeEntity entity = null;
		if(v.decl != null) entity = v.decl.entity;
		
		EntityAdress e = null;
		if(entity != null)
		e = ((KnowAdress)entity).adress;
		
		if(entity instanceof KnowAdress) {
		emit(Instruction.LOAD, displayRegister(currentLevel, e.level), s , e.displacement.toString());
		}else {
			emit(Instruction.LOADL, Instruction.EMPTY, 0 , v.spelling);
		}
	}
	
	private byte displayRegister(int cl, int l) {
		if(l == 0) {
			return Instruction.SB;
		}
		else if(l > 0 && cl == l) {
			return Instruction.LB;
		}
		else if(l > 0 && cl == l + 1) {
			return Instruction.L1;
		}else if(l > 0 && cl == l + 2) {
			return Instruction.L2;
		}else if(l > 0 && cl == l + 3) {
			return Instruction.L3;
		}else
			System.out.println("Limite de blocos atingidos!");
		return 99;
	}
	
	
	public void code (nodePrograma p) {
		if(p != null) {
		//emit(Instruction.LOAD, Instruction.SB, 0, 1);
		 p.visit (this);
		 try {
			arquivo.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void visitListaDeclaracao(listaDeclaracao ld) {
		//if(ld.exp != null) ld.exp.visit(this);
		if(ld.exp != null) ld.exp.visit(this);
		if(ld.next != null) {ld.next.visit(this);
	}
}

@Override
public void visitListaExpressao(listaExpressao le) {
	if(le != null) {
		if(le.exp != null) le.exp.visit(this);
		if(le.next != null) {le.next.visit(this);}
	}
}

@Override
public void visitAtribComando(nodeAtribComando a) {
	if(a != null) {
		if(a.expressao != null) a.expressao.visit(this);
		
		encodeAssign(a.var.id, a.var.id.decl.size);
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
public void visitComposto(nodeComposto c) {
	if(c != null) {
		if(c.lista != null) {
			currentLevel++;
			c.lista.visit(this);
			currentLevel--;
		}
	}
}

@Override
public void visitCorpo(nodeCorpo c) {
	if(c != null) {
		Integer gsAux2 = gs;
		gs = 0;
		
		if(currentLevel > 0) {
			gs+=3;
		}
		
		if(c.declarations != null) {
			
			c.declarations.visit(this);
		}
		gs = gsAux2;
		
		if(currentLevel == 0) {
			labelGenerator("main");
		}
		if(c.comandos != null) c.comandos.visit(this);
	}
}

@Override
public void visitDecFuncao(nodeDecFuncao df) {
	if(df != null) {
		
		label++;
		emit(Instruction.JUMP, Instruction.EMPTY, 0, "L_00" + label);
		
		Integer gsAux = gs;
		
		if(df.id != null) df.id.visit(this);
		labelGenerator(df.id.spelling);
		currentLevel++;
		
		gs +=3;
		
		if(df.lista != null) df.lista.visit(this);
		amount = 0;
		
		if(df.tipo != null) df.tipo.visit(this);
		
		//this.pilha.pushFrame(); //insere os links e atualiza os ponteiros
		if(df.corpo != null) df.corpo.visit(this);
		emit(Instruction.RETURN, Instruction.EMPTY, df.size, gs.toString());
		
		gs = gsAux;
		
		labelGenerator("L_00" + label);
		currentLevel--;	
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
		
		label++;
		emit(Instruction.JUMP, Instruction.EMPTY, 0, "L_00" + label);
		
		Integer gsAux = gs;
		if(dp.id != null) dp.id.visit(this);
		labelGenerator(dp.id.spelling);
		currentLevel++;
		gs += 3;
		if(dp.lista != null) dp.lista.visit(this);
		amount = 0;
		if(dp.corpo != null) dp.corpo.visit(this);
		emit(Instruction.RETURN, Instruction.EMPTY, 0 , gs.toString());
		gs = gsAux;
		labelGenerator("L_00" + label);
		currentLevel--;
	}


}

@Override
public void visitDecVariavel(nodeDecVariavel dv) {
	if(dv != null) {
		
		if(dv.id != null) dv.id.visit(this);
		if(dv.tipo != null) dv.tipo.visit(this);
		Frame frame = new Frame(dv.level, dv.size);
		
		EntityAdress e = new EntityAdress();
		e.level = frame.level;
		e.displacement = gs;
		
		Integer s = dv.size;
		emit(Instruction.PUSH, displayRegister(currentLevel, dv.level) , dv.size , e.displacement.toString());
		dv.entity = new KnowAdress(1,e.level, (int)gs);
		
		if(currentLevel == 0) {
			global += s;
		}
		gs += s;

		if(dv.next != null) dv.next.visit(this);
	}

}

@Override
public void visitEn(nodeEn e) {
	encodeFetch(e.name, e.size);
}

@Override
public void visitEo(nodeEo e) {//verificar possibilidade aqui
	if(e != null) {
	if(e.op != null) e.op.visit(this);
	if(e.left != null) e.left.visit(this);
	if(e.right != null) e.right.visit(this);
	
	switch(e.op.op.spelling) {
	case "+":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "ADD");
		break;
	case "-":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "SUB");
		break;
	case "OR":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "OR");
		break;
	case "AND":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "AND");
		break;
	case "<":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "LT");
		break;
	case ">":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "GT");
		break;
	case ">=":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "GTOE");
		break;
	case "<=":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "LTOE");
		break;
	case "=":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "EQ");
		break;
	case "<>":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "NEQ");
		break;
	case "/":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "DIV");
		break;
	case "*":
		emit(Instruction.CALL, Instruction.EMPTY, 0, "MULT");
		break;
	}
	
	
		}
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
		else if(e instanceof nodeEn) {
			((nodeEn)e).visit(this);
		}
		else if(e instanceof nodeEo) {
			((nodeEo)e).visit(this);
		}
		else if (e instanceof nodeTermo)
			((nodeTermo)e).visit(this);
		else if(e instanceof nodeFator)
			((nodeFator)e).visit(this);
		else if(e instanceof nodeSeletor) {
			((nodeSeletor)e).visit(this);
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
public void visitFatorFunc(nodeFatorFunc f) { //VERIFICAR QUANTIDADE DE PARAMETROS
	if(f != null) {
		if(f.id != null) 
		
		if(f.lista != null) 
			f.lista.visit(this);	
	}
}

@Override
public void visitFatorVar(nodeFatorVar f) {
	if(f != null) {
		if(f.var != null) {
			f.var.visit(this);
		}
	}
}

@Override
public void visitIdentificador(nodeIdentificador i) {
	//
}

@Override
public void visitIfComando(nodeIfComando c) {
	if(c != null) {
		int labelAux = label + 1;
		
		if(c.expressao != null) c.expressao.visit(this);
	
		emit(Instruction.JUMPIF, Instruction.EMPTY, 0, "L_00" + labelAux + "");
		
		if(c.comandoIf != null) c.comandoIf.visit(this);
		
		
		emit(Instruction.JUMP, Instruction.EMPTY, 0, "L_00" + labelAux);
		
		labelGenerator();
		
		if(c.comandoElse != null)c.comandoElse.visit(this);
		
		labelGenerator();
		
	} 
}

@Override
public void visitLiteral(nodeLiteral l) {
	//
}

@Override
public void visitOperator(nodeOperator o) {
	//
}

@Override
public void visitParametro(nodeParametro p) {
	if(p != null) {
		
		 if(p.id != null) p.id.visit(this);
		 if(p.tipo != null) p.tipo.visit(this);
		
		 if(p.next != null) p.next.visit(this);	
		 
		 Frame frame = new Frame(p.level, p.size);
		 
			EntityAdress e = new EntityAdress();
			e.level = frame.level;
			e.displacement = gs;
			
			Integer s = p.size;
			amount -= s;
		// emit(Instruction.PUSH, displayRegister(currentLevel, e.level) , s , e.displacement.toString());
			p.id.decl.entity = new KnowAdress(1 , e.level, amount);
			gs += s;
			
			
	 }

}

@Override
public void visitPComando(nodePComando p) {
	if(p != null) {
		
		if(p.id != null) p.id.visit(this);
		
		if(p.lista != null) p.lista.visit(this);
		
		emit(Instruction.CALL, Instruction.EMPTY , 0 , p.id.spelling);
		
	}
}

@Override
public void visitPrograma(nodePrograma p) {
	if(p != null) {
		if(p.corpo != null) {
			
			currentLevel++;
			p.corpo.visit(this);
			
			if(global > 0 && currentLevel == 0)
				emit(Instruction.POP, Instruction.EMPTY, 0 , global.toString());
			
			emit(Instruction.HALT, Instruction.EMPTY, 0, "0");
			currentLevel--;
			
		}
	}
}

@Override
public void visitTermo(nodeTermo t) {
	if(t != null) {
		t.op.visit(this);
		if(t.fator != null) t.fator.visit(this);
		if(t.next != null) t.next.visit(this);

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
		t.tipo.visit(this);
	}
}

@Override
public void visitTipoSimples(nodeTipoSimples t) {
	if(t != null) {
		//System.out.println(Token.SPELLINGS[t.tipo]);
	}

}

@Override
public void visitVariavel(nodeVariavel v) {
	if(v != null) {
		
		encodeFetch(v.id, v.id.decl.size);
		if(v.exp != null) v.exp.visit(this);
	}

}

@Override
public void visitWhileComando(nodeWhileComando w) {
	if(w != null) {
	int labelAux = label;
	labelGenerator();
	
	if(w.expressao != null) w.expressao.visit(this);
	
	emit(Instruction.JUMPIF, Instruction.EMPTY, 0, "L_00" + label);
	
	if(w.comando != null) w.comando.visit(this);
	
	emit(Instruction.JUMP, Instruction.EMPTY, 0, "L_00" + labelAux);
	
	labelGenerator();
	}
}

@Override
public void visitSequencialComando(sequencialComando sc) {
	if(sc != null) {
		if(sc.C1 != null) {sc.C1.visit(this);}
		if(sc.C2 != null) {
			sc.C2.visit(this);
		}
	}

}

@Override
public void visitSequencialDeclaration(sequencialDeclaration sd) {
	if(sd != null) {
		if(sd.D1 != null) sd.D1.visit(this);
		if(sd.D2 != null) {
			sd.D2.visit(this);
		}
	}

}

@Override
public void visitSequencialExpressao(sequencialExpressao se) {
	if(se != null) {
		if(se.exp != null) se.exp.visit(this);
		if(se.next != null) {
			se.next.visit(this);
		}
	}
}

@Override
public void visitSequencialIdentificador(sequencialIdentificador si) {
	if(si != null) {

		if(si.I1 != null) si.I1.visit(this);			
		if(si.I2 != null) {
			si.I2.visit(this);
		}
	}
}
@Override
public void visitSequencialParametro(sequencialParametro sp) {
	if(sp != null) {
		if(sp.P1 != null) sp.P1.visit(this);
		if(sp.P2 != null) {
			sp.P2.visit(this);
		}
	}
}

@Override
public void visitSeletor(nodeSeletor s) {
	if(s != null) {
		if(s.lista != null) s.lista.visit(this);
		sequencialExpressao e = (sequencialExpressao)s.lista;
		while(e != null) {
			e = (sequencialExpressao)e.next;
		}
	}
}
}

