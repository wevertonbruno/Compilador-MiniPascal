package analizadores;

public class Token {

public byte code;
public String spelling;
public int linha,coluna;

public Token() {
	
}

public Token(byte codigo, String spell, int linha, int coluna){
	this.code = codigo;
	this.spelling = spell;
	this.linha = linha;
	this.coluna = coluna;
	//verifica qual o identificador e atribui o codigo certo a ele
	if(this.code == IDENTIFIER){
		for(byte k = IF; k <= PROCEDURE; k++){
			if(this.spelling.equals(SPELLINGS[k])){
				if(this.spelling.compareTo("true") == 0 || this.spelling.compareTo("false") == 0)
					this.code = BOOLLIT;
				else if(this.spelling.compareTo("and") == 0)
					this.code = OPMUL;
				else if(this.spelling.compareTo("or") == 0)
					this.code = OPAD;
				else
					this.code = k;
			}
		}
	}
}
public final static byte 

IDENTIFIER = 0,
IF = 1,
ELSE = 2,
THEN = 3,
TRUE = 4,
FALSE = 5,
BEGIN = 6,
END = 7,
FUNCTION = 8,
VAR = 9,
WHILE = 10,
DO = 11,
OR = 12,
AND = 13,
PROGRAM = 14,
ARRAY = 15,
OF = 16,
INTEGER = 17,
REAL = 18,
BOOLEAN = 19,
INTLITERAL = 20,
FLOATLIT = 21,
BOOLLIT = 22,
OPAD = 23,
OPMUL = 24,
OPREL = 25,
SEMICOLON = 26,    // ;
COLON = 27,   //:
BECOMES = 28,  //:=
POINT = 29,  //,
DOT = 30,  //.
DOUBLEDOT = 31,  //..
LPAREN = 32, //(
RPAREN = 33,  //)
LCOL = 34,  //[
RCOL = 35, //]
PROCEDURE = 36,
ERRO = 98,
EOF = 99;

public final static String[] SPELLINGS = {"IDENTIFIER","if","else","then","true","false","begin","end","function","var",
		"while","do","or","and","program","array","of","integer","real","boolean","INTLITERAL","FLOATLIT","BOOLLIT","OPAD","OPMUL","OPREL",
		";",":",":=",",",".","..","(", ")","[","]","procedure","ERRO","EOF"};

}
