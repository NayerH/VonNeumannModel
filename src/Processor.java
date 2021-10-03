import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Processor {
	int[] mem;
	int memInstruc;
	int memData;
	int[] R;
	int cycles;
	
	//CHANGE IN JEQ RT TO RDVALUE and in MOVM and 
	public Processor(){
		this.mem = new int[2048];
		this.memInstruc = 0;
		this.memData = 1024;
		this.R = new int[33];
		this.R[0] = 0;
		this.cycles = 1;
	}
	public void parse(String programName){
		try {
		      File myObj = new File("src/" + programName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		    	  String line = myReader.nextLine();
		    	  String[] lineSplit= line.split(" ");
		    	  int opCode = 0;
		    	  switch(lineSplit[0]){
			    	  case "ADD":
			    		  opCode += 0;
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[3].substring(1));
			    		  opCode = opCode << 13;
			    		  
			    		  break;
			    	  case "SUB":
			    		  opCode += 1;
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[3].substring(1));
			    		  opCode = opCode << 13;
			    		  
			    		  break;
			    	  case "MUL":
			    		  opCode += 2;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[3].substring(1));
			    		  opCode = opCode << 13;
			    		  break;
			    	  case "MOVI":
			    		  opCode += 3;
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 23;
			    		  
			    		  int moviImm = Integer.parseInt(lineSplit[2]);
			    		  moviImm = moviImm & 0b00000000000000111111111111111111;
			    		  opCode += moviImm;
			    		  
			    		  break;
			    	  case "JEQ":
			    		  opCode += 4;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  int jeqImm = Integer.parseInt(lineSplit[3]);
			    		  jeqImm = jeqImm & 0b00000000000000111111111111111111;
			    		  opCode += jeqImm;
			    		  break;
			    	  case "AND":
			    		  opCode += 5;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[3].substring(1));
			    		  opCode = opCode << 13;
			    		  break;
			    	  case "XORI":
			    		  opCode += 6;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  int xoriImm = Integer.parseInt(lineSplit[3]);
			    		  xoriImm = xoriImm & 0b00000000000000111111111111111111;
			    		  opCode += xoriImm;
			    		  break;
			    	  case "JMP":
			    		  opCode += 7;
			    		  opCode = opCode << 28;
			    		  opCode += Integer.parseInt(lineSplit[1]);
			    		  break;
			    	  case "LSL":
			    		  opCode += 8;
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[3]);
			    		  
			    		  break;
			    	  case "LSR":
			    		  opCode += 9;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  opCode += Integer.parseInt(lineSplit[3]);
			    		  break;
			    	  case "MOVR":
			    		  opCode += 10;
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  
			    		  int movrImm = Integer.parseInt(lineSplit[3]);
			    		  movrImm = movrImm & 0b00000000000000111111111111111111;
			    		  opCode += movrImm;
			    		  
			    		  break;
			    	  case "MOVM":
			    		  opCode += 11;
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[1].substring(1));
			    		  opCode = opCode << 5;
			    		  opCode += Integer.parseInt(lineSplit[2].substring(1));
			    		  opCode = opCode << 18;
			    		  int movmImm = Integer.parseInt(lineSplit[3]);
			    		  movmImm = movmImm & 0b00000000000000111111111111111111;
			    		  opCode += movmImm;
			    		  break;
			    	default : 
			    		break;
		    	  }
		    	  mem[memInstruc++] = opCode;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public void run(){
		Integer instructionFetched = null;
		Integer instructionToBeDecoded = null;
		Integer instructionToBeExecuted = null;
		Integer instructionToMem = null;
		Integer instructionToWB = null;
		Integer opCode = null;
		Integer rs = null;
		Integer rt = null;
		Integer rd = null;
		Integer shamt = null;
		Integer imm = null;
		Integer address = null;
		Integer rsValue = null;
		Integer rtValue = null;
		Integer destinationWB = null;
		Integer res = null;
		Integer opCodeMem = null;
		Integer opCodeWB = null;
		Integer resWB = null;
		int index = ((memInstruc - 1) * 2) + 7;
		Integer destinationMemWb = null;
		Integer resMem = null;
		Integer rdValue = null;
		Integer rdValueHelper = null;
		Integer pcJmp = null;
		while(cycles <= index){
			Integer oldRValue = null;
			Integer addressInMem = null;
			Integer instructionFetchedOld = instructionFetched;
			Integer instructionToBeDecodedOld = instructionToBeDecoded;
			Integer instructionToBeExecutedOld = instructionToBeExecuted;
			Integer instructionToMemOld = instructionToMem;
			Integer instructionToWBOld = instructionToWB;
			Integer opCodeOld = opCode;
			Integer rsOld = rs;
			Integer rtOld = rt; //IMM & ADDRESS & SHIFTAMM
			Integer rdOld = rd;
			Integer shamtOld = shamt;
			Integer immOld = imm;
			Integer addressOld = address;
			Integer rsValueOld = rsValue;
			Integer rtValueOld = rtValue;
			Integer destinationWBOld = destinationWB;
			Integer resOld = res;
			Integer opCodeMemOld = opCodeMem;
			Integer opCodeWBOld = opCodeWB;
			Integer resWBOld = resWB;
			Integer oldMemValue = null;
			Integer destinationMemWbOld = destinationMemWb;
			Integer resMemOld = resMem;
			Integer rdValueOld = rdValue;
			Integer rdValueHelperOld = rdValueHelper;
			Integer pcJmpOld = pcJmp;
			//FETCH
			if(this.cycles % 2 == 1){
				if(R[32] < memInstruc){
					instructionFetched = mem[R[32]];
				} else {
					instructionFetched = null;
				}
			} else {
				instructionToBeDecoded = instructionFetchedOld;
				instructionFetched = null;
			}
			
			//DECODE
			if(this.cycles % 2 == 0) {
				if(R[32] < memInstruc){
					R[32]++;
				}
			} else {
				if(instructionToBeDecodedOld !=  null){
					opCode = instructionToBeDecodedOld & 0b11110000000000000000000000000000;
					opCode = opCode >>> 28;
					rd = instructionToBeDecodedOld & 0b00001111100000000000000000000000;
					rd = rd >>> 23;
					rs = instructionToBeDecodedOld & 0b00000000011111000000000000000000;
					rs = rs >>> 18;
			    	rt = instructionToBeDecodedOld & 0b00000000000000111110000000000000;
			    	rt = rt >>> 13;
			    	shamt = instructionToBeDecodedOld & 0b00000000000000000001111111111111;  
			    	imm = instructionToBeDecodedOld & 0b00000000000000111111111111111111; 
			    	if((imm & 0b00000000000000100000000000000000) == 0b00000000000000100000000000000000){
			    		imm +=0b11111111111111000000000000000000;
			    	}
			    	address = instructionToBeDecodedOld & 0b00001111111111111111111111111111;
			    	rsValue = this.R[rs];
			    	rtValue = this.R[rt];
			    	rdValue = this.R[rd];
			    	pcJmp = R[32];
				} else {
					opCode = null;
					rd = null;
					rs = null;
					rt = null;
					shamt = null;
					imm = null;
					address = null;
					rsValue = null;
					rtValue = null;
					rdValue = null;
					pcJmp = null;
				}
				instructionToBeExecuted = instructionToBeDecodedOld;
			}
			
			
			//EXECUTE
			if(cycles % 2 == 0){
				if(opCodeOld != null){
					switch(opCodeOld){
					case 0:
						res = rsValueOld + rtValueOld;
						break;
					case 1:
						res = rsValueOld - rtValueOld;
						break;
					case 2:
						res = rsValueOld * rtValueOld;
						break;
					case 3:
						res = immOld;
						break;
					case 4:
						if(rsValueOld == rdValueOld){
							R[32] = pcJmpOld + immOld;
							opCode = null;
							rs = null;
							rt = null;
							rd = null;
							shamt = null;
							imm = null;
							rsValue = null;
							rtValue = null;
							address = null;
							instructionToBeDecoded = null;
							instructionFetched = null;
							int i = memInstruc - (R[32]);
							index = ((i - 1) * 2) + 7 + cycles;
						}
						break;
					case 5:
						res = rsValueOld & rtValueOld;
						break;
					case 6:
						res = rsValueOld ^ immOld;
						break;
					case 7:
						R[32] = (R[32] & 0b11110000000000000000000000000000) + addressOld;
						opCode = null;
						rs = null;
						rt = null;
						rd = null;
						shamt = null;
						imm = null;
						rsValue = null;
						rtValue = null;
						address = null;
						instructionToBeDecoded = null;
						instructionFetched = null;
						int j = memInstruc - (R[32]);
						index = ((j - 1) * 2) + 7 + cycles;
						break;
					case 8:
						res = rsValueOld << shamtOld;
						break;
					case 9:
						res = rsValueOld >>> shamtOld;
						break;
					case 10:
						immOld = immOld & 0b00000000000000111111111111111111;
						res = rtValueOld + immOld;
						break;
					case 11:
						immOld = immOld & 0b00000000000000111111111111111111;
						res = rtValueOld + immOld;
						break;
					}
				} else {
					res = null;
				}
			} else {
				if(rdOld != null){
					rdValueHelper = rdValueOld;
					
				}
				opCodeMem = opCodeOld;
				destinationMemWb = rdOld;
				resMem = resOld;
				instructionToMem = instructionToBeExecutedOld;
			}
			
			//MEM
			if(cycles % 2 == 0){
				addressInMem = resMemOld;
				resWB = resMemOld;
				opCodeWB = opCodeMemOld;
				if(opCodeMemOld != null){
					if(opCodeMemOld == 10){
						resWB = mem[resMemOld];
					} else if (opCodeMemOld == 11){
						oldMemValue = mem[resMemOld];
						mem[resMemOld] = rdValueHelperOld;
					}
					if(opCodeMemOld == 11){
						destinationMemWbOld = rdValueHelperOld;
					}
				} else {
					destinationMemWbOld = null;
				}
				destinationWB = destinationMemWbOld;
				instructionToWB = instructionToMemOld;
				instructionToMem = null;
				opCodeMem = null;
			}
			
			//WB
			if(cycles % 2 == 1){
				if(opCodeWBOld != null){
					
					if(opCodeWBOld == 0 || opCodeWBOld == 1|| opCodeWBOld == 2|| opCodeWBOld == 3 || opCodeWBOld == 5 || opCodeWBOld == 6 || opCodeWBOld == 8 || opCodeWBOld == 9 || opCodeWBOld == 10){
						
						if(destinationWBOld != 0){
							oldRValue = R[destinationWBOld];
							R[destinationWBOld] = resWBOld;
						}
					}
				}
				opCodeWB = null;
				instructionToWB = null;
			}
			//PRINTS
			System.out.println("Clock Cycle Number: " + cycles);
			System.out.println("-------------------------");
			System.out.println("Fetch");
			System.out.println("Instruction in Fetching Stage: " + this.changeToString(instructionFetched));
			System.out.println("Inputs to Fetch: PC = " + R[32]);
			System.out.println("-------------------------");
			System.out.println("Decode");
			System.out.println("Instruction in Decoding Stage: " + this.changeToString(instructionToBeDecoded));
			System.out.println("-------------------------");
			System.out.println("Execute");
			System.out.println("Instruction in Execution Stage: " + this.changeToString(instructionToBeExecutedOld));
			System.out.println("Inputs to Execute: ");
			System.out.println("Value of RS = " + rsValueOld);
			System.out.println("Value of RT = " + rtValueOld);
			System.out.println("SHAMT = " + shamtOld);
			System.out.println("IMMEDIATE = " + immOld);
			System.out.println("ADDRESS = " + addressOld);
			System.out.println("-------------------------");
			System.out.println("MEMORY");
			System.out.println("Instruction in MEMORY Stage: " + this.changeToString(instructionToMemOld));
			System.out.println("Inputs to MEMORY: ");
			System.out.println("Address = " + addressInMem);
			System.out.println("Write Data = " + destinationMemWbOld);
			System.out.println("-------------------------");
			System.out.println("WRITE BACK");
			System.out.println("Instruction in WRITE BACK Stage: " + this.changeToString(instructionToWBOld));
			System.out.println("WRITE DATA = " + resWBOld);
			System.out.println("RD = " + destinationWBOld);
			System.out.println("-------------------------");
			if(oldRValue != null){
				if (oldRValue != resWBOld){
					System.out.println("Updates to REGISTERS");
					System.out.println("R[" + destinationWBOld+"] was " + oldRValue + " and changed to "+ resWBOld);
				}
			}
			if(oldMemValue != null){
				if (oldMemValue != rdValueHelperOld){
					System.out.println("Updates to MEMORY");
					System.out.println("MEM[" + resMemOld +"] was " + oldMemValue + " and changed to "+ rdValueHelperOld);
				}
			}
			System.out.println("###########################");
			cycles++;
		}
		
		for(int i = 0; i < 32; i++){
			System.out.println("Contents of R" + i + " : " + R[i]);
		}
		System.out.println("Contents of PC : " + R[32]);
		System.out.println("-------------------------");
		for(int i = 0; i < 1024; i++){
			System.out.println("Contents of INSTRUCTION MEMORY[" + i + "] : " + mem[i]);
		}
		System.out.println("-------------------------");
		for(int i = 1024; i < 2048; i++){
			System.out.println("Contents of DATA MEMORY[" + i + "] : " + mem[i]);
		}
		
	}
	
	public String changeToString(Integer val){
		if(val == null){
			return null;
		}
		String res = "";
		int opCode = val & 0b11110000000000000000000000000000;
		opCode = opCode >>> 28;
		int rd = val & 0b00001111100000000000000000000000;
		rd = rd >>> 23;
		int rs = val & 0b00000000011111000000000000000000;
		rs = rs >>> 18;
    	int rt = val & 0b00000000000000111110000000000000;
    	rt = rt >>> 13;
		int shamt = val & 0b00000000000000000001111111111111;  
		int imm = val & 0b00000000000000111111111111111111;
		int address = val & 0b00001111111111111111111111111111;	    		  
		switch(opCode){
			case 0:
				res += "ADD R" + rd + " R" + rs + " R" + rt;
				break;
			case 1:
				res += "SUB R" + rd + " R" + rs + " R" + rt;
				break;
			case 2:
				res += "MUL R" + rd + " R" + rs + " R" + rt;
				break;
			case 3:
				if((imm & 0b00000000000000100000000000000000) == 0b00000000000000100000000000000000){
		    		imm +=0b11111111111111000000000000000000;
		    	}
				res += "MOVI R" + rd + " " + imm;
				break;
			case 4:
				if((imm & 0b00000000000000100000000000000000) == 0b00000000000000100000000000000000){
		    		imm +=0b11111111111111000000000000000000;
		    	}
				res += "JEQ R" + rd + " R" + rs + " " + imm;
				break;
			case 5:
				res += "AND R" + rd + " R" + rs + " R" + rt;
				break;
			case 6:
				if((imm & 0b00000000000000100000000000000000) == 0b00000000000000100000000000000000){
		    		imm +=0b11111111111111000000000000000000;
		    	}
				res += "XORI R" + rd + " R" + rs + " " + imm;
				break;
			case 7:
				res += "JMP " + address;
				break;
			case 8:
				res += "LSL R" + rd + " R" + rs + " " + shamt;
				break;
			case 9:
				res += "LSR R" + rd + " R" + rs + " " + shamt;
				break;
			case 10:
				res += "MOVR R" + rd + " R" + rs + " " + imm;
				break;
			case 11:
				res += "MOVM R" + rd + " R" + rs + " " + imm;
				break;
		}
		
		return res;
	}
	
	
	public static void main(String[] args){
		Processor p = new Processor();
		p.parse("Program 1.txt");
		p.run();
	}
}
