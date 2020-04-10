import java.io.*;

public class TestCal {
  public native int add(int n1,int n2) ;
   public native int sub(int n1,int n2) ;
    public native int mul(int a, int b);
    public native int div(int a, int b);
  public static void main (String args[]) throws Exception {
    BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("\n Menu: \n 1.Add \n 2. sub \n 3.Mul \n 4. div ");
    String ch=b.readLine();
    
     TestCal t = new TestCal();
     int n1 = 0, n2 = 0;

    switch(ch)
    {
     case "1": 
	    	System.out.println("Enter two integer numbers:");
	    	n1=Integer.parseInt(b.readLine());
	    	n2=Integer.parseInt(b.readLine());
	    	System.out.println("Addition is="+ t.add(n1,n2)) ;
	    	break;
	case "2": 
	    	System.out.println("Enter two integer numbers:");
	    	n1=Integer.parseInt(b.readLine());
	    	n2=Integer.parseInt(b.readLine());
	    	System.out.println("Difference is="+t.sub(n1,n2)) ;
	    	break;
	    case "3": 
	    	System.out.println("Enter two integer numbers:");
	    	n1=Integer.parseInt(b.readLine());
	    	n2=Integer.parseInt(b.readLine());
	    	System.out.println("Multiplication is="+t.mul(n1,n2)) ;
	    	break;
	    case "4": 
	    	System.out.println("Enter two integer numbers:");
	    	n1=Integer.parseInt(b.readLine());
	    	n2=Integer.parseInt(b.readLine());
	    	System.out.println("Division is="+t.div(n1,n2)) ;
	    	break;
    }
  }
  static {
    System.loadLibrary ( "TestCal" ) ;
  }
}
