import java.util.*; 
  
public class Bankers 
{ 
 
    static int P = 4; 
   
    static int R = 3; 

    static int total = 0; 

    static boolean is_available(int process_id, int allocated[][], 
                    int max[][], int need[][], int available[])  
    { 
  
        boolean flag = true; 
  
      
        for (int i = 0; i < R; i++) 
        { 
  
            if (need[process_id][i] > available[i])  
            { 
                flag = false; 
            } 
        } 
        System.out.println(process_id+" is available: "+ flag); 
        return flag; 
    } 
  

    static void safe_sequence(boolean marked[], int allocated[][], int max[][], 
                        int need[][], int available[], Vector<Integer> safe)  
    { 
  
        for (int i = 0; i < P; i++)  
        { 
  
        	System.out.println(i+" marked: "+!marked[i]);
            if (!marked[i] && is_available(i, allocated, max, need, available)) 
            { 
  
               
                marked[i] = true; 
  
                
                for (int j = 0; j < R; j++)  
                { 
                	 System.out.println("Old Available: "+available[j]+" plus "+allocated[i][j] );
                    available[j] += allocated[i][j]; 
                    System.out.println("New Available: "+available[j] ); 
                } 
  
                safe.add(i); 
                System.out.println("Safe Added: "+i); 
                System.out.println("The Vector is: " + safe); 
 
                safe_sequence(marked, allocated, max, need, available, safe); 
                safe.removeElementAt(safe.size() - 1); 
                System.out.println("Safe Removed "); 
         
                marked[i] = false; 
  
                System.out.println("Available Decreased "); 
                for (int j = 0; j < R; j++)  
                { 
                    available[j] -= allocated[i][j]; 
                } 
            } 
        } 
  
        if (safe.size() == P)  
        { 
  
            total++; 
            System.out.println("We add 1 to ever vector element "); 
            for (int i = 0; i < P; i++)  
            {
            	
            	
                System.out.print("P" + (safe.get(i) + 1)); 
                if (i != (P - 1))  
                { 
                    System.out.print("--> "); 
                } 
            } 
  
            System.out.println("");; 
        } 
    } 
  
    public static void main(String[] args)  
    { 
        int allocated[][] = {{1, 0, 0}, 
        {6, 1, 2}, 
        {2, 1, 1}, 
        {0, 0, 2}}; 
  
        int max[][] = {{3, 2, 2}, 
        {6, 1, 3}, 
        {3, 1, 4}, 
        {4, 2, 2}}; 
 
        int resources[] = {9, 3, 6}; 
 
        int[] available = new int[R]; 
  
        for (int i = 0; i < R; i++) 
        { 
  
            int sum = 0; 
            for (int j = 0; j < P; j++)  
            { 
                sum += allocated[j][i]; 
            } 
  
            available[i] = resources[i] - sum; 
        } 
  
      
        Vector<Integer> safe = new Vector<Integer>(); 
  
        boolean[] marked = new boolean[P]; 
  
     
        int[][] need = new int[P][R]; 
        for (int i = 0; i < P; i++) 
        { 
            for (int j = 0; j < R; j++) 
            { 
                need[i][j] = max[i][j] - allocated[i][j]; 
            } 
        } 
        System.out.println("\nNeed matrix:"); 
        for (int i = 0; i < P; i++) 
        { 
            for (int j = 0; j < R; j++) 
            { 
            	System.out.print(need[i][j]); 
            } 
            System.out.println();
        }
  
        System.out.println("Safe sequences are:"); 
        safe_sequence(marked, allocated, max, need, available, safe); 
  
        System.out.println("\nThere are total " + total + " safe-sequences"); 
        
        
       
  
    } 
} 
