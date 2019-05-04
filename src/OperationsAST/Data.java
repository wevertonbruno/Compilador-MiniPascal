package OperationsAST;
import AST.nodeDecVariavel;
import AST.nodeDeclaracao;
import AST.nodeTipoAgregado;
import AST.nodeTipoSimples;
import analizadores.Token;
import analizadores.View;
import AST.*;

public class Data {
	public String id;
	public nodeDeclaracao decl;
	public int local;
	public byte type;
	View view;
	
	public Data(int local, String i, nodeDeclaracao decl, View v) {
		view = v;
		this.id = i;
		this.local = local;
		this.decl = decl;
		type = returnTipo(decl);
		
	}
	
	private  byte returnTipo(nodeDeclaracao decl) {
		if(decl instanceof nodeDecVariavel) {
			nodeTipo t0 = ((nodeDecVariavel)decl).tipo;
			
			if(t0 instanceof nodeTipoSimples) {
				if(((nodeTipoSimples)t0).tipo == Token.INTEGER)
				((nodeDecVariavel)decl).size = 2;
				
				else if(((nodeTipoSimples)t0).tipo == Token.REAL)
					((nodeDecVariavel)decl).size = 4;
				
				else if(((nodeTipoSimples)t0).tipo == Token.BOOLEAN)
					((nodeDecVariavel)decl).size = 1;
				
				return ((nodeTipoSimples)t0).tipo;
			}
			else if(t0 instanceof nodeTipoAgregado) {
				nodeTipo t1 = ((nodeTipoAgregado)t0).tipo;
				
				int i1 = Integer.parseInt(((nodeTipoAgregado)t0).intLeft.spelling);
				int i2 = Integer.parseInt(((nodeTipoAgregado)t0).intRight.spelling);
				
				if(t1 instanceof nodeTipoSimples) {
					
					if(((nodeTipoSimples)t1).tipo == Token.INTEGER)
						((nodeDecVariavel)decl).size = (i2 - i1 + 1) * 2;
						
						else if(((nodeTipoSimples)t1).tipo == Token.REAL)
							((nodeDecVariavel)decl).size = (i2 - i1 + 1) * 4;
						
						else if(((nodeTipoSimples)t1).tipo == Token.BOOLEAN)
							((nodeDecVariavel)decl).size = (i2 - i1 + 1) * 1;
					
					return ((nodeTipoSimples)t1).tipo;
				}
				else if(t1 instanceof nodeTipoAgregado) {
					nodeTipo t2 = ((nodeTipoAgregado)t1).tipo;
					
					int j1 = Integer.parseInt(((nodeTipoAgregado)t1).intLeft.spelling);
					int j2 = Integer.parseInt(((nodeTipoAgregado)t1).intRight.spelling);
					
					if(t2 instanceof nodeTipoSimples) {
						
						if(((nodeTipoSimples)t2).tipo == Token.INTEGER)
							((nodeDecVariavel)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 2;
							
							else if(((nodeTipoSimples)t2).tipo == Token.REAL)
								((nodeDecVariavel)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 4;
							
							else if(((nodeTipoSimples)t2).tipo == Token.BOOLEAN)
								((nodeDecVariavel)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 1;
						
						return ((nodeTipoSimples)t2).tipo;
					}
					else {
						if(view.Ef == 0) {
							view.tC.append("Exceded Array Dimension in line " + ((nodeDecVariavel)decl).id.linha);
								view.Ef = 1;
							}
					}
				}
			}
		} else if(decl instanceof nodeDecFuncao) {
			nodeTipo t0 = ((nodeDecFuncao)decl).tipo;
			
			if(t0 instanceof nodeTipoSimples) {
				
				if(((nodeTipoSimples)t0).tipo == Token.INTEGER)
					((nodeDecFuncao)decl).size = 2;
					
					else if(((nodeTipoSimples)t0).tipo == Token.REAL)
						((nodeDecFuncao)decl).size = 4;
					
					else if(((nodeTipoSimples)t0).tipo == Token.BOOLEAN)
						((nodeDecFuncao)decl).size = 1;
				
				return ((nodeTipoSimples)t0).tipo;
			}
			else if(t0 instanceof nodeTipoAgregado) {
				
				nodeTipo t1 = ((nodeTipoAgregado)t0).tipo;
				
				int i1 = Integer.parseInt(((nodeTipoAgregado)t0).intLeft.spelling);
				int i2 = Integer.parseInt(((nodeTipoAgregado)t0).intRight.spelling);
				
				if(t1 instanceof nodeTipoSimples) {
					
					if(((nodeTipoSimples)t1).tipo == Token.INTEGER)
						((nodeDecFuncao)decl).size = (i2 - i1 + 1) * 2;
						
						else if(((nodeTipoSimples)t1).tipo == Token.REAL)
							((nodeDecFuncao)decl).size = (i2 - i1 + 1) * 4;
						
						else if(((nodeTipoSimples)t1).tipo == Token.BOOLEAN)
							((nodeDecFuncao)decl).size = (i2 - i1 + 1) * 1;
					
					return ((nodeTipoSimples)t1).tipo;
				}
				else if(t1 instanceof nodeTipoAgregado) {
					nodeTipo t2 = ((nodeTipoAgregado)t1).tipo;
					
					int j1 = Integer.parseInt(((nodeTipoAgregado)t1).intLeft.spelling);
					int j2 = Integer.parseInt(((nodeTipoAgregado)t1).intRight.spelling);
					
					if(t2 instanceof nodeTipoSimples) {
						
						if(((nodeTipoSimples)t2).tipo == Token.INTEGER)
							((nodeDecFuncao)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 2;
							
							else if(((nodeTipoSimples)t2).tipo == Token.REAL)
								((nodeDecFuncao)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 4;
							
							else if(((nodeTipoSimples)t2).tipo == Token.BOOLEAN)
								((nodeDecFuncao)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 1;
						
						return ((nodeTipoSimples)t2).tipo;
					}
					else {
						if(view.Ef == 0) {
							view.tC.append("Exceded Array Dimension!");
								view.Ef = 1;
							}
					}
				}
			}
			
		}else if(decl instanceof nodeParametro) {
			nodeTipo t0 = ((nodeParametro)decl).tipo;
			
			if(t0 instanceof nodeTipoSimples) {
				if(((nodeTipoSimples)t0).tipo == Token.INTEGER)
				((nodeParametro)decl).size = 2;
				
				else if(((nodeTipoSimples)t0).tipo == Token.REAL)
					((nodeParametro)decl).size = 4;
				
				else if(((nodeTipoSimples)t0).tipo == Token.BOOLEAN)
					((nodeParametro)decl).size = 1;
				
				return ((nodeTipoSimples)t0).tipo;
			}
			else if(t0 instanceof nodeTipoAgregado) {
				nodeTipo t1 = ((nodeTipoAgregado)t0).tipo;
				
				int i1 = Integer.parseInt(((nodeTipoAgregado)t0).intLeft.spelling);
				int i2 = Integer.parseInt(((nodeTipoAgregado)t0).intRight.spelling);
				
				if(t1 instanceof nodeTipoSimples) {
					
					if(((nodeTipoSimples)t1).tipo == Token.INTEGER)
						((nodeParametro)decl).size = (i2 - i1 + 1) * 2;
						
						else if(((nodeTipoSimples)t1).tipo == Token.REAL)
							((nodeParametro)decl).size = (i2 - i1 + 1) * 4;
						
						else if(((nodeTipoSimples)t1).tipo == Token.BOOLEAN)
							((nodeParametro)decl).size = (i2 - i1 + 1) * 1;
					
					return ((nodeTipoSimples)t1).tipo;
				}
				else if(t1 instanceof nodeTipoAgregado) {
					nodeTipo t2 = ((nodeTipoAgregado)t1).tipo;
					
					int j1 = Integer.parseInt(((nodeTipoAgregado)t1).intLeft.spelling);
					int j2 = Integer.parseInt(((nodeTipoAgregado)t1).intRight.spelling);
					
					if(t2 instanceof nodeTipoSimples) {
						
						if(((nodeTipoSimples)t2).tipo == Token.INTEGER)
							((nodeParametro)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 2;
							
							else if(((nodeTipoSimples)t2).tipo == Token.REAL)
								((nodeParametro)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 4;
							
							else if(((nodeTipoSimples)t2).tipo == Token.BOOLEAN)
								((nodeParametro)decl).size = (i2 - i1 + 1) * (j2 - j1 + 1) * 1;
						
						return ((nodeTipoSimples)t2).tipo;
					}
					else {
						if(view.Ef == 0) {
							view.tC.append("Exceded Array Dimension in line " + ((nodeParametro)decl).id.linha);
								view.Ef = 1;
							}
					}
				}
			}
		}
		return 0;
	}
	}
