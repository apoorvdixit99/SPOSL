
import java.util.*;
  
class LRU 
{  
    static int pageFaults(int pages[], int n, int capacity) 
    { 
       
        HashSet<Integer> s = new HashSet<>(capacity); 
       
        HashMap<Integer, Integer> indexes = new HashMap<>(); 
       
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            if (s.size() < capacity) 
            { 
            	
                if (!s.contains(pages[i])) 
                { System.out.println("Page added representing page fault: "+ pages[i]);
                    s.add(pages[i]); 
                    page_faults++; 
                } 
   
                indexes.put(pages[i], i); 
            } 
       
            else
            {  
            	System.out.println("Set is full");
                if (!s.contains(pages[i])) 
                { 
                	System.out.println("Set doesnt contain "+ pages[i]);
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
                      
                    Iterator<Integer> itr = s.iterator(); 
                      
                    while (itr.hasNext()) { 
                        int temp = itr.next(); 
                        if (indexes.get(temp) < lru) 
                        { 
                            lru = indexes.get(temp); 
                            val = temp; 
                        } 
                    } 
                    System.out.println("Least recently used page is: "+ val);
                    s.remove(val); 
                   indexes.remove(val);
                   System.out.println("Page added representing page fault: "+ pages[i]);
                    s.add(pages[i]); 
       
                    page_faults++; 
                } 
                System.out.println("Set contain "+ pages[i]);
                indexes.put(pages[i], i); 
            } 
        } 
       
        return page_faults; 
    } 

    public static void main(String args[]) 
    { 
        //int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1}; 
        
        //7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
		
	Scanner sc=new Scanner(System.in);

	System.out.println("Enter no of pages");
	int n=sc.nextInt();
	
	System.out.println("Enter page no:");
	int pages[]=new int[n];
	for(int i=0;i<n;i++)
		pages[i]=sc.nextInt();
	
	System.out.println("Enter no of frames");
	int capacity=sc.nextInt();
	
        int pf=pageFaults(pages, n, capacity);
        System.out.println("Page Faults: "+ pf); 
        
    } 
} 
