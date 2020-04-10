package A4;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import A3.KPD;
import A3.MN;
import A3.PNTAB;
import A3.PassOne;

public class PassTwo extends PassOne {
	
	BufferedReader macroCall;
	String macroCallPath;
	
	HashMap <String,APTAB> aptabMacroCalls;
	
	
	PassTwo() throws IOException{
		this.displayMacroFile();
		this.readMacroFile();
		this.displayMNTAB();
		this.displayMDTAB();
		this.displayKPDTAB();
		this.displayPNTAB();
		

		this.macroCallPath = "/home/darealappu/Eclipse Workspaces/SPOS/SPOS/src/A4/macrocalls.txt";
		this.macroCall = new BufferedReader(new FileReader(macroCallPath));
		
		aptabMacroCalls = new HashMap<String,APTAB>();
		
	}
	
	
	public void displayMacroCallsAndGenerateAPTAB() throws IOException{
		System.out.println("=========================================");
		System.out.println("Macro Code Path");
		this.macroCall = new BufferedReader(new FileReader(macroCallPath));
		String line = macroCall.readLine();
		
		while(line!=null){
			//PRINT MACRO CALL
			System.out.println(line);
			
			//Split the line
			String tokens[] = line.split(" |,");
			
			//Compare each macro call name with name in MNT
			for(MN mn : mntab){
				
				//if name is found
				if(mn.getName().equals(tokens[0])){
					
					//Create a new aptab for the macro function
					APTAB aptab = new APTAB();
					
					//Adding the defaults to the Aptab
					for(int i=mn.getKPTP();i<mn.getKPTP()+mn.getKeywordParameterCount();i++){
						int aptabIndex = (i-mn.getKPTP()+mn.getPositionalParameterCount()+1);
						String aptabKey = "(P,"+Integer.toString(aptabIndex)+")";
						aptab.table.put(aptabKey,kpdtab.get(i-1).defaultValue);
					}
					
					int aptabIndex = 0;
					
					//Iterate through the arguments
					for(int i=1;i<tokens.length;i++){
						
						String regex = "\\d+";
						
						//If token is a digit
						//It is a positional parameter
						if(tokens[i].matches(regex)){
							aptabIndex++;
							String aptabKey = "(P,"+Integer.toString(aptabIndex)+")";
							aptab.table.put(aptabKey, tokens[i]);
						}
						
						//if it is a keyword parameter
						else if(tokens[i].startsWith("&") && tokens[i].contains("=")){
							
							//Get the name of parameter
							String parameterName = tokens[i].substring(1, tokens[i].indexOf("="));
							String newValue = tokens[i].substring(tokens[i].indexOf("=")+1);
							
							//Go through the KPTAB
							for(int j=mn.getKPTP();j<mn.getKPTP()+mn.getKeywordParameterCount();j++) {
								
								//If the Name is found in KPTAB
								if(kpdtab.get(j-1).name.equals(parameterName)) { 
									
									//Get the corresponding index in APTAB
									int aptabIndex2 = mn.getPositionalParameterCount() + (j-mn.getKPTP()) +1  ;
									String aptabKey2 = "(P,"+Integer.toString(aptabIndex2)+")";
									
									//Enter the new value/Ovveride Default
									aptab.table.put(aptabKey2,newValue);
								}
							}
							
							
							
							//aptab.table.put(aptabKey, tokens[i].substring(tokens[i].indexOf("=")+1));
						}
						
						aptabMacroCalls.put(mn.getName(),aptab);
					}
					
				}
				

				
			}
			
			line = macroCall.readLine();
		}
		System.out.println("=========================================");
	}
	
	
	public void displayAPTAB(){
		
		for(MN mn : this.mntab){
			System.out.println("=========================================");
			System.out.println("APTAB for Macro "+mn.getName());
			System.out.println("Key\tValue");

			Set <String> keyset = aptabMacroCalls.get(mn.getName()).table.keySet();
	        Iterator<String> itr = keyset.iterator();
	        
	        
	        while(itr.hasNext()) {
	            String key = itr.next();
	            System.out.println(key+"\t"+aptabMacroCalls.get(mn.getName()).table.get(key));
	        }
			
		}
		System.out.println("=========================================");
	}
	
	public void expandCode() {

		System.out.println("=========================================");
		System.out.println("Expanded Code before replacing Parameters");
		
		for(MN mn : mntab) {
			
			int index = mn.getMDTP()-1;
			
			
			String line = mdt.table.get(index);
			while(!line.equalsIgnoreCase("MEND")) {
				
				System.out.println(line);
				line = mdt.table.get(++index);
			}
			
		}
		

		System.out.println("=========================================");
	}
	
	public void replaceParameters() {

		System.out.println("=========================================");
		System.out.println("Expanded Code after Replacing Parameters");
		
		for(MN mn : mntab) {
			
			int index = mn.getMDTP()-1;
			
			
			String line = mdt.table.get(index);
			while(!line.equalsIgnoreCase("MEND")) {
				

				Set <String> keyset = aptabMacroCalls.get(mn.getName()).table.keySet();
		        Iterator<String> itr = keyset.iterator();
		        while(itr.hasNext()) {
		            String key = itr.next();
		            if(line.contains(key)) {
		            	String newValue = aptabMacroCalls.get(mn.getName()).table.get(key);
		            	
		            	//Replace the key with the new Value
		            	line = line.replace(key,newValue);
	
		            }
		        }
				
				
				System.out.println(line);
				line = mdt.table.get(++index);
			}
			
		}
		

		System.out.println("=========================================");
	}
	
	

	public static void main(String args[])throws IOException{
		
		PassTwo pass2 = new PassTwo();
		pass2.displayMacroCallsAndGenerateAPTAB();
		pass2.displayAPTAB();
		pass2.expandCode();
		pass2.replaceParameters();
		
	}
}
