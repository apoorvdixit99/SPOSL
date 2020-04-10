package A3;

public class MN {
	
	String name;
	int positionalParameterCount;
	int keywordParameterCount;
	int MDTP;
	int KPDTP;
	PNTAB pntab;
	
	MN(){
		this.name="";
		this.positionalParameterCount=-1;
		this.keywordParameterCount=-1;
		this.MDTP=-1;
		this.KPDTP=-1;
		this.pntab = new PNTAB();
	}
	
	MN(String name,int positionalParameterCount,int keywordParameterCount,int MDTP,int KPDTP){
		this.name=name;
		this.positionalParameterCount=positionalParameterCount;
		this.keywordParameterCount=keywordParameterCount;
		this.MDTP=MDTP;
		this.KPDTP=KPDTP;
		this.pntab = new PNTAB();
	}

	MN(String name,int positionalParameterCount,int keywordParameterCount,int MDTP,int KPDTP, PNTAB pntab){
		this.name=name;
		this.positionalParameterCount=positionalParameterCount;
		this.keywordParameterCount=keywordParameterCount;
		this.MDTP=MDTP;
		this.KPDTP=KPDTP;
		this.pntab = pntab;
	}
	
	 
	public void setName(String name){this.name=name;}
	public void setpositionalParameterCount(int positionalParameterCount){this.positionalParameterCount=positionalParameterCount;}
	public void setKeywordParameterCount(int keywordParameterCount){this.positionalParameterCount=keywordParameterCount;}
	public void setMDTP(int MDTP){this.MDTP=MDTP;}
	public void setKPTP(int KPTP){this.KPDTP=KPTP;}
	
	public String getName(){return this.name;}
	public int getPositionalParameterCount(){return this.positionalParameterCount;}
	public int getKeywordParameterCount(){return this.positionalParameterCount;}
	public int getMDTP(){return this.MDTP;}
	public int getKPTP(){return this.KPDTP;}
}

/*

	


*/
