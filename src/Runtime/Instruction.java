package Runtime;

public class Instruction {
	
public static String getInstruction(byte m, int i, int n, String d) {
		String instrucao = "";
		switch(m) {
		case Instruction.LOAD:
			instrucao = "LOAD ";
			break;
		case Instruction.LOADL:
			instrucao = "LOADL ";
			break;
		case Instruction.LOADA:
			instrucao = "LOADA ";
			break;
		case Instruction.STORE:
			instrucao = "STORE ";
			break;
		case Instruction.CALL:
			instrucao = "CALL(" + n + ") ";
			break;
		case Instruction.RETURN:
			instrucao = "RETURN(" + n + ") ";
			break;
		case Instruction.PUSH:
			instrucao = "PUSH(" + n + ") ";
			break;
		case Instruction.POP:
			instrucao = "POP ";
			break;
		case Instruction.JUMP:
			instrucao = "JUMP ";
			break;
		case Instruction.JUMPIF:
			instrucao = "JUMPIF(" + n + ") ";
			break;
		case Instruction.HALT:
			instrucao = "HALT";
			break;
		case Instruction.EMPTY:
			
		default:
			System.out.println("Instrucao nao existe");
			break;
		}
		
		switch(i) {
		case Instruction.SB:
			instrucao = instrucao + d + "[SB] \r\n";
			break;
		case Instruction.LB:
			instrucao = instrucao + d + "[LB] \r\n";
			break;
		case Instruction.ST:
			break;
		case Instruction.L1:
				instrucao = instrucao + d + "[L1] \r\n";
			break;
		case Instruction.L2:
			instrucao = instrucao + d + "[L2]\r\n";
			break;
		case Instruction.L3:
			instrucao = instrucao + d + "[L3]\r\n";
			break;
		case Instruction.EMPTY:
			instrucao = instrucao + d + "\r\n";
			break;
		default:
			System.out.println("Instrucao nao existe");
			break;
		}
		
		return instrucao;
	}
	
	public final static byte
	LOAD = 0,
	LOADL = 1,
	LOADA = 2,
	STORE = 3,
	CALL = 4,
	RETURN =5,
	PUSH = 6,
	POP = 7,
	JUMP = 8,
	JUMPIF = 9,
	HALT = 10,
	EMPTY = 11;
	
	public final static byte 
	SB = 0,
	ST = 1,
	LB = 2,
	L1 = 3,
	L2 = 4,
	L3 = 5;
}
