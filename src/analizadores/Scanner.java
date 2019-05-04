package analizadores;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;

public class Scanner {
	private FileInputStream file;
	private InputStreamReader openFile;
	private PushbackReader reader;
	private int line=1;
	private int column=0;	
	View view;
	
	private Character currentChar;
	private byte currentCode;
	private StringBuffer currentSpelling;
	
	public Scanner(String path, View v){
		try {
		view = v;
		this.file = new FileInputStream(path);
		this.openFile = new InputStreamReader(this.file);
		this.reader = new PushbackReader(this.openFile);
		this.currentChar = getChar();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//metodo que retorna o proximo char do buffer
	//se for EOF retorna null
	public Character getChar() throws IOException{
		
		Character c = null;
		int i = (int)this.reader.read();
		
		if(i != -1) 
			c = (char)i;
			column++;
		return c;
	}
	private boolean compare(char c) {
		if(isNull())
			return false;
		else
			return (currentChar == c);
	}
	
	private boolean isNull() {
		
		return currentChar == null;
	}
	
	private boolean lookahead(int c) throws IOException{
	
		int next = this.reader.read();
		if(next == -1)
			return false;
		if(next == c) {
			this.reader.unread(next);
			return true;
		}
		else
			this.reader.unread(next);
		return false;
	}
	
	
	
	//Metodo que retorna um token
	private byte scanToken() throws IOException{
		switch(currentChar){
		
		case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h':
		case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p':
		case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'x': case 'w': 
		case 'y': case 'z': case 'A':case 'B':case 'C':case 'D':case 'E':case 'F':case 'G': case 'H':
		case 'I':case 'J':case 'K':case 'L':case 'M':case 'N':case 'O':case 'P':
		case 'Q':case 'R':case 'S':case 'T':case 'U':case 'V':case 'X':case 'W': 
		case 'Y':case 'Z':

			takeIt();
			while(isLetter(currentChar)||isDigit(currentChar))
				takeIt();
			return Token.IDENTIFIER;
			
		case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
			takeIt();
			while(isDigit(currentChar))
				takeIt();
					if(compare('.')){
						if(lookahead('.')) {
							return Token.INTLITERAL;
						}
						takeIt();
						if(isDigit(currentChar)){
							takeIt();
							while(isDigit(currentChar))
								takeIt();
							return Token.FLOATLIT;
						}
						return Token.FLOATLIT;
					}else	
						return Token.INTLITERAL;
		case '.':
			takeIt();
			if(isDigit(currentChar)){
				takeIt();
				while(isDigit(currentChar))
					takeIt();
				return Token.FLOATLIT;
			}else if(compare('.')) {
				takeIt();
				return Token.DOUBLEDOT;
			}
			else 
				return Token.DOT;
			
		case '+': case '-':
			takeIt();
			return Token.OPAD;
			
		case '*': case '/':
			takeIt();
			return Token.OPMUL;
			
		case '=':
			takeIt();
			return Token.OPREL;
			
		case '<':
			takeIt();
			if(compare('=')){
				takeIt();
				return Token.OPREL;
			}else if(compare('>')){
				takeIt();
				return Token.OPREL;
			}else
			return Token.OPREL;
			
		case '>':
			takeIt();
			if(compare('='))
				takeIt();
			return Token.OPREL;
			
		case ':':
			takeIt();
			if(compare('=')){
				takeIt();
				return Token.BECOMES;
			}else
				return Token.COLON;
			
		case ',':
			takeIt();
			return Token.POINT;
			
		case ';':
			takeIt();
			return Token.SEMICOLON;
			
		case '[':
			takeIt();
			return Token.LCOL;
			
		case ']':
			takeIt();
			return Token.RCOL;
			
		case '(':
			takeIt();
			return Token.LPAREN;
			
		case ')':
			takeIt();
			return Token.RPAREN;
			
		default:
			takeIt();
			return Token.ERRO;
			
		}
	}
	
	//consome os separadores
private void scanSeparator() throws IOException{
	switch(currentChar){
	case ' ': case '\n': case '\r': case (char)9: //char 9 é o tab
		if(compare('\n')){
			line++;
			column=0;
		}
		currentChar = getChar();
		//takeIt();
		break;
	case '!':
		while(isGraphic(currentChar) || compare((char)9))
			currentChar=getChar();
		break;
	}
	//falta implementar o comentario com // ou /*
}
//metodo que retorna um token do buffer
public Token scan() throws IOException{
		
	if(isNull()) {
		if(view.Ef == 0 && line==1) {
			view.tC.append("Error: Source File not found!");
			view.Of = 1;
			view.Ef = 1;}
		
		return new Token(Token.EOF,"EOF",line,column);}
	
	while(compare(' ')|| compare('\n') || compare('\r') || compare('!') || compare((char)9)){
		scanSeparator();
		if(isNull())
			return new Token(Token.EOF,"EOF",line,column);
			
	}
	
	currentSpelling = new StringBuffer("");
	currentCode = scanToken();
	
	if(currentCode == Token.EOF) {
		if(view.Ef == 0) {
		view.tC.append("Error: Source File not found");
		view.Of = 1;
		view.Ef = 1;
		}
	}
	
	if(currentCode == Token.ERRO) {
		if(view.Ef == 0) {
		view.tC.append("Error row " + line + " column "+ column + " Token "+ currentSpelling + " is not acceptable.");
		view.Ef = 1;
		}
	}
	
	return new Token(currentCode,currentSpelling.toString(),line,column-currentSpelling.toString().length());
	
}
	
//metodo que aceita um char condicional
private void take(Character expected) throws IOException{
	if(currentChar == expected){
		currentSpelling.append(expected);
		currentChar = getChar();
	}else{
		if(view.Ef == 0) {
			view.tC.append("Error row " + line + " column "+ column + "Token "+ currentSpelling + " is not acceptable.");
			view.Ef = 1;
			}
	}
}

//método que aceita um char sem condição
private void takeIt() throws IOException{
		this.currentSpelling.append(currentChar);
		this.currentChar = getChar();
}

//metodo que verifica se é digito
private boolean isDigit(Character c){
	
	if(c == null)
		return false;
	int valorAsc2 = (int)c;
	
	if(valorAsc2 >= 48 && valorAsc2 <= 57)
		return true;
	return false;
}

//metodo que verifica se é letra
private boolean isLetter(Character c){
	if(c == null)
		return false;
	int valorAsc2 = (int)c;
	
	if((valorAsc2 >= 'a' && valorAsc2 <= 'z') || (valorAsc2 >= 'A' && valorAsc2 <= 'Z'))
		return true;
	return false;
}

private boolean isGraphic(Character c){
	if(c == null)
		return false;
	int valorAsc2 = (int)c;
	
	if(valorAsc2 >= 32 && valorAsc2 <=126)
		return true;
	return false;
}

}
