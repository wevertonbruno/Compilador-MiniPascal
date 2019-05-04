package analizadores;
import OperationsAST.*;

import java.awt.EventQueue;

import AST.*;

public class Main {

	/*public static void main(String[] args){
		try{
		String path = "C:\\Users\\jramo\\Desktop\\Compiladores\\src\\analizadores\\programa.txt";	
		
		Parser parser = new Parser(path);
		nodePrograma p;
		//Printer printer = new Printer();
		//Checker checker = new Checker();
		//Coder coder = new Coder();
		p = parser.parse();
		//printer.print(p);
		//checker.check(p);
		//coder.code(p);
		System.out.println("End.");
	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.exit(0);
	}*/
	 public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					new View();
			}
		});
	}
	
}