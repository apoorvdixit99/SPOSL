package A3;

import java.io.*;
import java.util.*;

public class PassOne {
	
	BufferedReader macroFile;
	String macroFilePath;
	
	protected Vector <MN> mntab;
	protected MDT mdt;
	protected ArrayList <KPD> kpdtab;
	
	public PassOne() throws IOException{
		this.macroFilePath = "/home/darealappu/Eclipse Workspaces/SPOS/SPOS/src/A3/MacroFile";
		this.macroFile = new BufferedReader(new FileReader(macroFilePath));
		this.mntab = new Vector<MN>();
		this.kpdtab = new ArrayList<KPD>();
		this.mdt = new MDT();
	}
	
	public void displayMacroFile() throws IOException{
		System.out.println("=========================================");
		System.out.println("Macro File");
		macroFile = new BufferedReader(new FileReader(macroFilePath));
		String line = macroFile.readLine();
		while(line!=null){
			System.out.println(line);
			line = macroFile.readLine();
		}
		System.out.println("=========================================");
	}
	
	public void readMacroFile() throws IOException{
		
		macroFile = new BufferedReader(new FileReader(macroFilePath));
		String line = macroFile.readLine();
		
		int positionalParameterCount=0;
		int keywordParameterCount=0;
		String macroName = "";
		int kpdp=0;
		int mdtp=0;
		
		//Initialize PNTAB
		PNTAB pntab = new PNTAB();
		int pntabIndex = 0;
		
		while(line!=null){

			if(line.equalsIgnoreCase("MACRO")){
				
				//Read the next Line
				line = macroFile.readLine();
				
				//Tokenise the next line
				String tokens[] = line.split(" |,|\n|\t");
				
				//Get the Macro Name
				macroName = tokens[0];
				
				//Get KPDP
				kpdp = kpdtab.size()+1;
				
				//Get KPDTAB
				mdtp = mdt.table.size()+1;
				
				//String pntabHashValue = "(P,"+(++pntabIndex)+")";
				
				for(String token: tokens) {
					//Checking for Keyword Parameter
					if(token.startsWith("&") && token.contains("=")){
						++keywordParameterCount;
						
						String KPD_name = token.substring(1, token.indexOf('='));
						String KPD_defaultValue = "" ;
						String pntabHashValue = "(P,"+(++pntabIndex)+")";
						
						if(!token.endsWith("=")){
							KPD_defaultValue = token.substring(token.indexOf('=')+1);
						}

						//Add to PNTAB
						pntab.table.put("&"+KPD_name, pntabHashValue);
						
						//Add to KPDTAB
						kpdtab.add(new KPD(KPD_name,KPD_defaultValue));
						
					}
					//Checking for Positional Parameter
					else if(token.startsWith("&") && !token.contains("=")){
						++positionalParameterCount;
						
						String PPD_name = token.substring(1);
						String PPD_defaultValue = "" ;
						String pntabHashValue = "(P,"+(++pntabIndex)+")";
						
						if(!token.endsWith("=")){
							PPD_defaultValue = token.substring(token.indexOf('=')+1);
						}

						//Add to PNTAB
						pntab.table.put("&"+PPD_name, pntabHashValue);
						
						
						
					}
				}
				
				//Add to mntab
				mntab.add(new MN(macroName,positionalParameterCount,keywordParameterCount,mdtp,kpdp,pntab));

			}
			
			else if((line.equalsIgnoreCase("MEND"))){
				
				//Reinitialize Parameters
				positionalParameterCount=0;
				keywordParameterCount=0;
				macroName = "";
				kpdp=0;
				mdtp=0;
				
				//Initialize PNTAB
				pntab = new PNTAB();
				pntabIndex = 0;
				
				//Add Line to MDT
				//NOTE - CHANGES NEED TO BE MADE LATER
				mdt.table.add(line);
				
			}
			
			else{
				
				//Replace key with value from pntab
				Set <String> keyset = pntab.table.keySet();
		        Iterator<String> itr = keyset.iterator();
		        while(itr.hasNext()) {
		            String key = itr.next();
		            if(line.contains(key)){
		            	line = line.replace(key, pntab.table.get(key));
		            }
		        }

				//Add Line to MDT
				mdt.table.add(line);
			}
			
			//Read the next Line
			line = macroFile.readLine();
			
			
		}
		
		
	}
	
	
	public void displayMNTAB(){
		System.out.println("=========================================");
		System.out.println("MNTAB");
		System.out.println("Name\t#PP\t#KP\tMDTP\tKPDTP");
		
		for(MN mn : this.mntab){
			System.out.print(mn.name+"\t");
			System.out.print(mn.positionalParameterCount+"\t");
			System.out.print(mn.keywordParameterCount+"\t");
			System.out.print(mn.MDTP+"\t");
			System.out.println(mn.KPDTP);
		}
		System.out.println("=========================================");
	}
	
	
	public void displayMDTAB(){
		System.out.println("=========================================");
		System.out.println("MDTAB");
		
		for(int mdtIndex=1;mdtIndex<=mdt.table.size();mdtIndex++){
			System.out.print(mdtIndex+"\t:\t");
			System.out.println(this.mdt.table.get(mdtIndex-1));
		}
		System.out.println("=========================================");
	}
	
	
	public void displayKPDTAB(){
		System.out.println("=========================================");
		System.out.println("KPDTAB");
		System.out.println("name\tdefaultValue");
		
		for(KPD kpd : kpdtab){
			System.out.print(kpd.name+"\t");
			System.out.println(kpd.defaultValue);
		}
		System.out.println("=========================================");
	}
	
	
	public void displayPNTAB(){
		
		for(MN mn : this.mntab){
			System.out.println("=========================================");
			System.out.println("PNTAB for Macro "+mn.name);
			System.out.println("Param\tValue");

			Set <String> keyset = mn.pntab.table.keySet();
	        Iterator<String> itr = keyset.iterator();
	        
	        
	        while(itr.hasNext()) {
	            String key = itr.next();
	            System.out.println(key+"\t"+mn.pntab.table.get(key));
	        }
			
			
			
		}
		System.out.println("=========================================");
	}
	
	
	public static void main(String args[])throws IOException{
		PassOne pass1 = new PassOne();
		pass1.displayMacroFile();
		pass1.readMacroFile();
		pass1.displayMNTAB();
		pass1.displayMDTAB();
		pass1.displayKPDTAB();
		pass1.displayPNTAB();
	}

}
