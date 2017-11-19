import java.util.Arrays;
import java.util.HashSet;

// A Dynamic Programming based solution for 0-1 Knapsack problem
class SubsetSumProblem
{
 
	// Driver program for testing
	public static void main(String args[])
	{
		
		int[] weights = new  int[]{5, 10, 12, 7, 15, 13, 8, 25};
		int maxWeight = 21;
		
		SubsetSumProblem ss = new SubsetSumProblem(weights, maxWeight);

		ss.find_OPT();
		
		ss.printOPT();
		System.out.println(ss.getOptimalWeight());

		System.out.println(ss.find_O_usualMethod(ss.get_n(), maxWeight));
		System.out.println(ss.find_O_alternativeMethod(ss.get_n(), maxWeight));
	}
	
	private int OPT[][];
	private Array_1D_1BasedIndexing v;	// input values
	private Array_1D_1BasedIndexing w;	// input weights
	private int n;						// number of inputs
	private int targetW;				// maximum weight of knapsack
	
	
	public SubsetSumProblem(int[] weights, int W)
	{
		w = new Array_1D_1BasedIndexing( weights );
		n = w.length();
		targetW = W;
		OPT = new int[n+1][targetW+1];
	}
	
	// Returns the maximum value that can be put in a knapsack of capacity W
	public void find_OPT()
	{
	 // Build table OPT[][] in bottom up manner
	 for (int i = 0; i <= n; i++)
	 {
		 for ( int iteration_w = 0; iteration_w <= targetW; iteration_w++)
		 {
			 if (i==0 || iteration_w==0)
				  OPT[i][iteration_w] = 0;
			 else if (w.get(i) > iteration_w)
				 OPT[i][iteration_w] = OPT[i-1][iteration_w];
			 else
				 OPT[i][iteration_w] = max(w.get(i) + OPT[i-1][iteration_w-w.get(i)],  OPT[i-1][iteration_w]);
		 }
	  }
	  
	}
	
	public HashSet<Integer> find_O_usualMethod(int tempN, int tempW)
	{
		if (tempN==0)
			return new HashSet<Integer>();
		else if (w.get(tempN)>tempW)
			return find_O_usualMethod(tempN-1, tempW);
		else if (OPT[tempN-1][tempW] > (OPT[tempN-1][tempW-w.get(tempN)] + w.get(tempN)))
			return find_O_usualMethod(tempN-1, tempW);
		else
		{
			HashSet<Integer> set = find_O_usualMethod(tempN-1, tempW-w.get(tempN));
			set.add(tempN);
			return set;
		}
	}
	
	public HashSet<Integer> find_O_alternativeMethod(int tempN, int tempW)
	{
		if (tempN==0 || tempW == 0)
			return new HashSet<Integer>();
		else if (OPT[tempN][tempW] == OPT[tempN-1][tempW])
			return find_O_alternativeMethod(tempN-1, tempW);
		else
		{
			HashSet<Integer> set = find_O_alternativeMethod(tempN-1, tempW-w.get(tempN));
			set.add(tempN);
			return set;
		}
	}
	
	public void printOPT()
	{
		for (int row = 0; row < OPT.length; row++)
			System.out.println( Arrays.toString( OPT[row] ) );
	}
	
	public int getOptimalWeight()
	{
		return OPT[w.length()][targetW];
	}
	
	public int get_n()
	{
		return n;
	}
	
 
	// A utility function that returns maximum of two integers
	static int max(int a, int b) { return (a > b)? a : b; }
}
