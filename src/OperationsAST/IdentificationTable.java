package OperationsAST;

import java.util.ArrayList;
import AST.nodeDeclaracao;
import analizadores.View;

public class IdentificationTable {
	public ArrayList<Data> table;
	public int scope;
	View view;
	
	public IdentificationTable(View v) {
		view = v;
		this.table = new ArrayList<Data>();
		this.scope = -1;
	}
	
	public void enter(String identificador, nodeDeclaracao decl) {
		Data data = new Data(scope, identificador, decl, view);
		for(int i=0; i<table.size();i++) {
			if((table.get(i).id.compareTo(identificador) == 0 ) && table.get(i).local == scope) {
				if(view.Ef == 0) {
					view.tC.append("Variable #" + table.get(i).id + " already declared in this scope!");
						view.Ef = 1;
					}
			}
		}
		decl.level = scope;
		this.table.add(data);
	}
	
	public byte retrieve(String id) {
		Data temp = null;
		
		for(int i=0; i<table.size();i++) { 
			
			if(table.get(i).id.compareTo(id) == 0) { 
				
				int localTemp = table.get(i).local;
				
					for(int j=0; j<table.size(); j++) {
						if(table.get(i).id.compareTo(table.get(j).id) == 0 && table.get(j).local >= localTemp) {
							
							temp = table.get(j);
							localTemp = temp.local;
							
						}
					}
					
				return temp.type; //Função deve retornar o ponteiro da declaração da variavel
								//Se houver mais de uma declaração, retornar aquela de maior Scopo
			}
		}
		
			if(view.Ef == 0) {
			view.tC.append("Variable " + id + " was not declared");
				view.Ef = 1;
			}
			return 0;
	}
	
	public nodeDeclaracao retrieveDeclaration(String id) {
		Data temp = null;
		
		for(int i=0; i<table.size();i++) { 
			
			if(table.get(i).id.compareTo(id) == 0) { 
				
				int localTemp = table.get(i).local;
				
					for(int j=0; j<table.size(); j++) {
						if(table.get(i).id.compareTo(table.get(j).id) == 0 && table.get(j).local >= localTemp) {
							
							temp = table.get(j);
							localTemp = temp.local;
							
						}
					}
					
				return temp.decl; //Função deve retornar o ponteiro da declaração da variavel
								//Se houver mais de uma declaração, retornar aquela de maior Scopo
			}
		}
			if(view.Ef == 0) {
			view.tC.append("Variable " + id + " was not declared");
				view.Ef = 1;
			}
			return null;
	}
	
	public void openScope() {
		this.scope++;
	}
	
	public void closeScope() {
		for(int i=table.size() - 1; i>=0; i--){
			if(this.table.get(i).local == this.scope) {
				this.table.remove(i);
			}
		}
		this.scope--;
	}

}
