package A1;

import java.io.*;

public class Main {

	public static void main(String[] args) {
		FileReader file;
		BufferedReader reader;
		BufferedWriter writer;
		FileWriter fileWriter;
		PassOne assembler = new PassOne();
		try {
			file = new FileReader("/home/darealappu/Eclipse Workspaces/SPOS/SPOS/src/A1/input3.asm");
			reader = new BufferedReader(file);
			
			String line = reader.readLine();
			
			String IC = assembler.parseCode(reader);
			System.out.println("Intermediate Code:");
			System.out.println(IC);
			file.close();
			reader.close();
			fileWriter = new FileWriter("IC.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(IC);
			writer.close();
			
			String SYMTAB = assembler.getSymbolTable();
			System.out.println("Symbol Table:");
			System.out.println(SYMTAB);
			fileWriter = new FileWriter("SYMTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(SYMTAB);
			writer.close();
			
			String LITTAB = assembler.getLiteralTable();
			System.out.println("Literal Table:");
			System.out.println(LITTAB);
			fileWriter = new FileWriter("LITTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(LITTAB);
			writer.close();
			
			String POOLTAB = assembler.getPoolTable();
			System.out.println("Pool Table:");
			System.out.println(POOLTAB);
			POOLTAB = POOLTAB.replace("#", "");
			fileWriter = new FileWriter("POOLTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(POOLTAB);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}