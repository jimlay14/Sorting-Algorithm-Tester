import java.util.Arrays;
import java.util.Iterator;

/**
 * A permutation set represents the set of permutations (orderings) of a particular list of elements. 
 * Note that for a list of size n, there are n! different permutations.
 * 
 * Permutations cannot be accessed directly. Instead, they are available via an Iterator, which returns a new array of elements in the permuted order.
 * 
 * @author Joel Ross
 * @author adapted from program by Peter Jensen
 * @version Sp 2014
 */
public class PermutationSet<E> implements Iterable<E[]>
{
	private E[] original; //the original list of elements
	
	/**
	 * Creates a new Set of the permutations (orderings) of the given array of elements.
	 * If the elements are unique, then the permutations will be unique; otherwise duplicate permutations will be included 
	 * 
	 * Due to memory and speed limitations, can only create permutations for lists with up to 20 items.
	 * 
	 * @param elementArray An array of elements to create permutations of
	 */
	public PermutationSet(E[] elementArray)
	{
		if(elementArray.length > 20)
			throw new IllegalArgumentException("Cannot generate permutations for list with more than 20 items.");
		
		this.original = elementArray;
	}
	
	
	/**
	 * Returns an iterator over the set of permutations. This allows one to iterative over the (very large) set of permutations
	 * Each iteration call to .next() will return a new array of elements that represents the next permutation of the original list.
	 */
	public Iterator<E[]> iterator()
	{
		return new PermutationIterator<E>();
	}

	/**
	 * Internal class representing the iterator for a set of permutations
	 */
	public class PermutationIterator<L> implements Iterator<E[]>
	{
		private long permutationNumber;
		private long totalPermutations;
		
		/**
		 * Constructs a new PermutationIterator; private because only the PermutationSet can construct its own iterator.
		 */
		private PermutationIterator()
		{
			permutationNumber = 0;
			totalPermutations = 1;
			for(long i=2; i<=original.length; i++)
				totalPermutations *= i; //calculate factorial!
		}
		
		/**
		 * Returns whether or not there are further permutations to iterate through
		 * @return If there are more permutations
		 */
		public boolean hasNext()
		{
			return permutationNumber < totalPermutations;
		}

		/**
		 * Returns the next permutation in this set of permutations
		 * @return The next permutation, as an array of elements
		 */
		public E[] next()
		{
			E[] permutation = Arrays.copyOf(original, original.length);
			
			//make permutations, algorithm from Peter Jensen
			long f = 1;
			for(int i=2; i<=original.length; i++)
			{
				f = f*(i-1);
				int k = (int)(i - ((permutationNumber/f) % i) - 1);
				E tmp = permutation[i-1];
				permutation[i-1] = permutation[k];
				permutation[k] = tmp;
			}
			
			permutationNumber++;
			return permutation;
			
		}
		
		/**
		 * Remove is not supported. Throws an UnsupportedOperaionException.
		 */
		public void remove()
		{
			throw new UnsupportedOperationException("Cannot remove permutations from a PermutationSet");  			
		}
	}


	// A main method that demonstrates usage of this class
	public static void main(String[] args)
	{
		int n = 3; //size of list to test
		
		Integer[] list = new Integer[n];
		for(int i=0; i<n; i++)
			list[i] = i+1;
		
		PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
		System.out.println("Starting...");
		long start = System.currentTimeMillis();
		for(Integer[] perm : permutations)
		{
			System.out.println(Arrays.toString(perm));
		}
		long end =System.currentTimeMillis();
		System.out.println((end-start)+"ms");		
	}
	
}
