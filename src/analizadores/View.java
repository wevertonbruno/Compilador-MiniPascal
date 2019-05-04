package analizadores;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import AST.nodePrograma;
import OperationsAST.Checker;
import OperationsAST.Coder;
import OperationsAST.Printer;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class View extends JFrame {

	public JPanel contentPane;
	public JTextArea tC;
	public Parser parser;
	Printer printer;
	Checker check;
	Coder coder;
	public Scanner scan;
	public JTextField tr;
	public JTextField tO;
	public JTextArea tA;
	JScrollPane pane;
	public int Ef;
	public int Of;
	
	public View(){
		
		this.setTitle("COMPILADOR");
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tC = new JTextArea(5, 20);
		tC.setBounds(58, 388, 562, 62);
		tC.setEditable(false);
		contentPane.add(tC);
		tC.setColumns(10);
		
		JButton bS = new JButton("An\u00E1lise Sint\u00E1tica");
		bS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bS.setBounds(224, 159, 150, 23);
		bS.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(bS);
		
		tr = new JTextField();
		tr.setBounds(22, 41, 302, 20);
		contentPane.add(tr);
		tr.setColumns(10);
		
		JLabel lblCaminhoDoPrograma = new JLabel("Caminho do Programa Fonte:");
		lblCaminhoDoPrograma.setBounds(79, 11, 263, 19);
		lblCaminhoDoPrograma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblCaminhoDoPrograma);
		
		JLabel lblCaminhoDoPrograma_1 = new JLabel("Caminho do Programa Objeto:");
		lblCaminhoDoPrograma_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCaminhoDoPrograma_1.setBounds(428, 11, 229, 19);
		contentPane.add(lblCaminhoDoPrograma_1);
		
		tO = new JTextField();
		tO.setBounds(371, 41, 302, 20);
		tO.setColumns(10);
		contentPane.add(tO);
		
		JLabel lblImpressoDarvore = new JLabel("Impress\u00E3o da \u00C1rvore ou Impress\u00E3o da An\u00E1lise L\u00E9xica");
		lblImpressoDarvore.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblImpressoDarvore.setBounds(774, 0, 395, 27);
		contentPane.add(lblImpressoDarvore);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(774, 39, 349, 479);
		contentPane.add(scrollPane);
		
		tA = new JTextArea(25, 20);
		tA.setEditable(false);
		scrollPane.setViewportView(tA);
		tA.setLineWrap(true);
		tA.setLineWrap(true);
		tA.setColumns(50);
		
		
		
		JLabel lblConsole = new JLabel("Console:");
		lblConsole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsole.setBounds(58, 353, 82, 14);
		contentPane.add(lblConsole);
		
		JLabel lblSelecioneAtOnde = new JLabel("Selecione at\u00E9 onde o compilador dever\u00E1 ser executado:");
		lblSelecioneAtOnde.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelecioneAtOnde.setBounds(25, 117, 395, 14);
		contentPane.add(lblSelecioneAtOnde);
		
		JButton bL = new JButton("An\u00E1lise L\u00E9xica");
		bL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bL.setBounds(44, 159, 132, 23);
		bL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bL.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(bL);
		
		JButton bA = new JButton("Impress\u00E3o da \u00C1rvore");
		bA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bA.setBounds(413, 159, 174, 23);
		bA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bA.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(bA);
		
		JButton bC = new JButton("An\u00E1lise de Contexto");
		bC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bC.setBounds(92, 230, 174, 23);
		bC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bC.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(bC);
		
		JButton bG = new JButton("Gera\u00E7\u00E3o de C\u00F3digo");
		bG.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bG.setBounds(341, 230, 174, 23);
		bG.setVerticalAlignment(SwingConstants.BOTTOM);
		contentPane.add(bG);
		
		bL.addActionListener(new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  View.this.tC.setText("");
	        	  View.this.tA.setText("");
	        	  View.this.Ef = 0;
	        	  View.this.Of = 0;
	        	  String path = View.this.tr.getText();
	        	  try{
	        		  	View.this.scan = new Scanner(path, View.this);	
	        			Token t = View.this.scan.scan();
	        			while((t.code != 99 && View.this.Ef == 0)){
	        				View.this.tA.append(" name: " + t.spelling);
	        				View.this.tA.append(" code: " + t.code);
	        				View.this.tA.append(" line: " + t.linha);
	        				View.this.tA.append(" column: " + t.coluna + "\n");
	        				t = View.this.scan.scan();
	        			}
	        		  	
	        			}catch(Exception v){
	        				View.this.tC.append(v.getMessage());
	        			}
	          }
		});
	          
		bS.addActionListener(new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  View.this.tC.setText("");
	        	  View.this.tA.setText("");
	        	  View.this.Ef = 0;
	        	  View.this.Of = 0;
	        	  String path = View.this.tr.getText();
	        	  try{
	        		  	View.this.parser = new Parser(path, View.this);	
	        			}catch(Exception v){
	        				View.this.tC.append(v.getMessage());
	        			}
	        	  
	        	nodePrograma p;
	      		p = View.this.parser.parse();
	      
	        			
	          }
	        }); 
		
		bA.addActionListener(new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  View.this.tC.setText("");
	        	  View.this.tA.setText("");
	        	  View.this.Ef = 0;
	        	  View.this.Of = 0;
	        	  String path = View.this.tr.getText();
	        	  try{
	        		  	View.this.parser = new Parser(path, View.this);	
	        			}catch(Exception v){
	        				View.this.tC.append(v.getMessage());
	        			}
	        	  
	        	nodePrograma p;
	      		p = View.this.parser.parse();
	      		View.this.printer = new Printer(View.this);
	      		View.this.printer.print(p);
	      
	        			
	          }
	        }); 
		
		bC.addActionListener(new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  View.this.tC.setText("");
	        	  View.this.tA.setText("");
	        	  View.this.Ef = 0;
	        	  View.this.Of = 0;
	        	  String path = View.this.tr.getText();
	        	  try{
	        		  	View.this.parser = new Parser(path, View.this);	
	        			}catch(Exception v){
	        				View.this.tC.append(v.getMessage());
	        			}
	        	  
	        	nodePrograma p;
	      		p = View.this.parser.parse();
	      		View.this.printer = new Printer(View.this);
	      		View.this.check = new Checker(View.this);
	      		View.this.printer.print(p);
	      		if(View.this.Of == 0) {
	      		View.this.tC.setText("");
	      		View.this.tC.append("\n");
	      		View.this.check.check(p);
	      		if(View.this.Ef == 0) {
	      			View.this.tC.setText("Checker and Identifier Concluded");
	      		}
	      		}
	        			
	          }
	        });
		
		bG.addActionListener(new ActionListener() { 
	          public void actionPerformed(ActionEvent e) {
	        	  View.this.tC.setText("");
	        	  View.this.tA.setText("");
	        	  View.this.Ef = 0;
	        	  View.this.Of = 0;
	        	  String path = View.this.tr.getText();
	        	  try{
	        		  	View.this.parser = new Parser(path, View.this);	
	        			}catch(Exception v){
	        				View.this.tC.append(v.getMessage());
	        			}
	        	 
	        	
	        	nodePrograma p;
	      		p = View.this.parser.parse();
	      		if(View.this.Of == 0) {
	      		View.this.printer = new Printer(View.this);
	      		View.this.check = new Checker(View.this);
	      		View.this.coder = new Coder(View.this);
	      		if(View.this.Of == 1) {
	      			View.this.tC.setText("");
	      			View.this.tC.append("Error: Program Object file was not found");
					}else
	      		if(View.this.Of == 0) {
	      			
	      		View.this.printer.print(p);
	      		View.this.tC.setText("");
	      		View.this.tC.append("\n");
	      		View.this.check.check(p);
	      		if(View.this.Ef == 0) {
	      			View.this.tC.setText("Checker and Identifier Concluded");
	      		
	      		}
	      		View.this.coder.code(p); 
	      	
		      	View.this.tC.append('\n' + "Code has been generated");
				
	          }
	          }
	          }
	        });
		
		
	}
}
