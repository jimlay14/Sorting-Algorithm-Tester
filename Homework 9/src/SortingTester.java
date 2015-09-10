import java.util.*;

/**
 * A class of in-place array sorting methods
 */
public class SortingTester
{	
	public static void main(String[] args)
    {
	//	mergeSortTest(10000, 7);
	//	runtimeTest(10000, 6);
    //	permutationSortTest(2, 12);
		fastSortTest(10000, 6);
	//	mergeSortTest(10000, 6);
	//	quadraticSortTest(2, 5);
     }

	/**
	 * Performs the needed quadratic sorting algorithm tests on permutations of a given length
	 * @param length The length of the array for which permutations are made
	 */
	public static void bubbleSortTest(int length){				
			Integer[] list = new Integer[length];
			for(int i=0; i<length; i++)
				list[i] = i+1;
			int numPermutations = 0;
			
			//BubbleSort data
			AnalyzeableHashMap bubbleCompData = new AnalyzeableHashMap(); //comparisons
			AnalyzeableHashMap bubbleSwapData = new AnalyzeableHashMap(); //swaps

			PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
			System.out.println("Bubble sort test on arrays of length " + length + "...\n");
			long start = System.currentTimeMillis();
			for(Integer[] perm : permutations)
			{					
				numPermutations++;
				//I removed the permutation printing to speed the process when testing larger lengths; you can print if you want to
				//System.out.println(Arrays.toString(perm));
				
				//bubble sort data gathered
				int[] bubbleData = Sorts.bubbleSort(perm);
				//System.out.println(bubbleData[0] + " " + bubbleData[1]);
				bubbleCompData.add(bubbleData[0]);
				bubbleSwapData.add(bubbleData[1]);
			}
			long end =System.currentTimeMillis();
			System.out.println("Number of permutations: " + numPermutations + "\n");	
			System.out.println("Bubble Sort Comparisons: " + bubbleCompData.calculateData());
		//	bubbleCompData.printTable();
			System.out.println("Bubble Sort Swaps: " + bubbleSwapData.calculateData() +"\n");
			
			System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Performs the needed quadratic sorting algorithm tests for Insertion sort on permutations of a given length
	 * @param length The length of the array for which permutations are made
	 */
	public static void insertionSortTest(int length){				
			Integer[] list = new Integer[length];
			for(int i=0; i<length; i++)
				list[i] = i+1;
			int numPermutations = 0;
			
			//Collect data on quadratic sorting algorithms

			//Insertion sort data
			AnalyzeableHashMap insertionCompData = new AnalyzeableHashMap(); //..
			AnalyzeableHashMap insertionSwapData = new AnalyzeableHashMap();
			
			PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
			System.out.println("Insertion sort test on arrays of length " + length + "...\n");
			long start = System.currentTimeMillis();
			for(Integer[] perm : permutations)
			{					
				numPermutations++;
				//I removed the permutation printing to speed the process when testing larger lengths; you can print if you want to
				//System.out.println(Arrays.toString(perm));
				
				//insertion sort data gathered
				int[] insertionData = Sorts.insertionSort(perm);
				insertionCompData.add(insertionData[0]);
				insertionSwapData.add(insertionData[1]);
			}
			long end =System.currentTimeMillis();
			System.out.println("Number of permutations: " + numPermutations + "\n");
			System.out.println("Insertion Sort Comparisons: " + insertionCompData.calculateData());
		//	insertionCompData.printTable();
			System.out.println("Insertion Sort Swaps: " + insertionSwapData.calculateData() +"\n");
			System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Performs the needed quadratic sorting algorithm tests for Selection Sort on permutations of a given length
	 * @param length The length of the array for which permutations are made
	 */
	public static void selectionSortTest(int length){				
			Integer[] list = new Integer[length];
			for(int i=0; i<length; i++)
				list[i] = i+1;
			int numPermutations = 0;
			
			//Selection sort data
			AnalyzeableHashMap selectionCompData = new AnalyzeableHashMap();
			AnalyzeableHashMap selectionSwapData = new AnalyzeableHashMap();
			
			PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
			System.out.println("Selection sort test on arrays of length " + length + "...\n");
			long start = System.currentTimeMillis();
			for(Integer[] perm : permutations)
			{					
				numPermutations++;
				//I removed the permutation printing to speed the process when testing larger lengths; you can print if you want to
				//System.out.println(Arrays.toString(perm));

				//Selection sort data gathered
				int[] selectionData = Sorts.selectionSort(perm);
				selectionCompData.add(selectionData[0]);
				selectionSwapData.add(selectionData[1]);
			}
			long end =System.currentTimeMillis();
			System.out.println("Number of permutations: " + numPermutations + "\n");
			System.out.println("Selection Sort Comparisons: " + selectionCompData.calculateData());
		//	selectionCompData.printTable();
			System.out.println("Selection Sort Swaps: " + selectionSwapData.calculateData() + "\n");
			System.out.println((end-start)+"ms" + "\n");
	}
	
	
	/**
	 * Method that performs the needed quadratic sorting algorithm tests on a range of list sizes
	 * @param beginLength The initial length of the array of integers to make permutations of
	 * @param endLength The final length of the array of integers to make permutations of
	 */
	public static void quadraticSortTest(int beginLength, int endLength){		
		for(int i = beginLength; i <= endLength; i++){
			System.out.println("********************************* Quadratic Testing on lists of length " + i + " *********************************\n");
			
			bubbleSortTest(i);
			insertionSortTest(i);
			selectionSortTest(i);
		}
	}
	
	/**
	 * Performs the specified number of merge sort tests on arrays that are a given power of ten
	 * 
	 * I'm using the AnalyzeableArrayList here because the number of comparisons made when sorting large lists
	 * makes my AnalyzeableHashMap not feasible due to space constraints. The AnalyzeableArrayList, on the other hand, 
	 * can handle data for the number of permutations I'll make in my tests.
	 * 
	 * @param numCases The number of different permutations we create
	 * @param arraySizePow The power of 10 that we use, specifying the list's length
	 */
	public static void mergeSortTest(int numCases, int arraySizePow){
		long start,end;
		System.out.println("Merge sort test on arrays of length " + Math.pow(10, arraySizePow) + "...\n");
		//Merge data
		AnalyzeableArrayList mergeCompData = new AnalyzeableArrayList();
		AnalyzeableArrayList mergeSwapData = new AnalyzeableArrayList();
		int counter = numCases;
    	start = System.nanoTime();
		while(counter > 0){
			Integer[] toSort = shuffled((int)Math.pow(10, arraySizePow), 0); //unseeded shuffling
			//System.out.println(Arrays.toString(toSort));
			int[] mergeData = Sorts.mergeSort(toSort);
			//System.out.println(Arrays.toString(toSort));
			mergeCompData.add(mergeData[0]);
			mergeSwapData.add(mergeData[1]);
			
			counter--;
		}
    	end = System.nanoTime();
    	System.out.println("Number of permutations: " + numCases + "\n");
		System.out.println("Merge Sort Comparisons: " + mergeCompData.calculateData());
//		mergeCompData.printTable();
		System.out.println("Merge Sort Swaps: " + mergeSwapData.calculateData() + "\n");
		System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Performs the specified number of heap sort tests on arrays that are a given power of ten
	 * 
	 * I'm using the AnalyzeableArrayList here because the number of comparisons made when sorting large lists
	 * makes my AnalyzeableHashMap not feasible due to space constraints. The AnalyzeableArrayList, on the other hand, 
	 * can handle data for the number of permutations I'll make in my tests.
	 * 
	 * @param numCases The number of different permutations we create
	 * @param arraySizePow The power of 10 that we use, specifying the list's length
	 */
	public static void heapSortTest(int numCases, int arraySizePow){
		long start,end;
		System.out.println("Heap sort test on arrays of length " + Math.pow(10, arraySizePow) + "...\n");
		//Merge data
		AnalyzeableArrayList heapCompData = new AnalyzeableArrayList();
		AnalyzeableArrayList heapSwapData = new AnalyzeableArrayList();
		int counter = numCases;
    	start = System.nanoTime();
		while(counter > 0){
			Integer[] toSort = shuffled((int)Math.pow(10, arraySizePow), 0); //unseeded shuffling
			//System.out.println(Arrays.toString(toSort));
			int[] mergeData = Sorts.heapSort(toSort);
			//System.out.println(Arrays.toString(toSort));
			heapCompData.add(mergeData[0]);
			heapSwapData.add(mergeData[1]);
			
			counter--;
		}
    	end = System.nanoTime();
    	System.out.println("Number of permutations: " + numCases + "\n");
		System.out.println("Heap Sort Comparisons: " + heapCompData.calculateData());
//		mergeCompData.printTable();
		System.out.println("Heap Sort Swaps: " + heapSwapData.calculateData() + "\n");
		System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Performs the specified number of quick sort tests on arrays that are a given power of ten
	 * 
	 * I'm using the AnalyzeableArrayList here because the number of comparisons made when sorting large lists
	 * makes my AnalyzeableHashMap not feasible due to space constraints. The AnalyzeableArrayList, on the other hand, 
	 * can handle data for the number of permutations I'll make in my tests.
	 * 
	 * @param numCases The number of different permutations we create
	 * @param arraySizePow The power of 10 that we use, specifying the list's length
	 */
	public static void quickSortTest(int numCases, int arraySizePow){
		long start,end;
		System.out.println("Quick sort test on arrays of length " + Math.pow(10, arraySizePow) + "...\n");
		//Merge data
		AnalyzeableArrayList quickCompData = new AnalyzeableArrayList();
		AnalyzeableArrayList quickSwapData = new AnalyzeableArrayList();
		int counter = numCases;
    	start = System.nanoTime();
		while(counter > 0){
			Integer[] toSort = shuffled((int)Math.pow(10, arraySizePow), 0); //unseeded shuffling
			//System.out.println(Arrays.toString(toSort));
			int[] mergeData = Sorts.heapSort(toSort);
			//System.out.println(Arrays.toString(toSort));
			quickCompData.add(mergeData[0]);
			quickSwapData.add(mergeData[1]);
			
			counter--;
		}
    	end = System.nanoTime();
    	System.out.println("Number of permutations: " + numCases + "\n");
		System.out.println("Quick Sort Comparisons: " + quickCompData.calculateData());
//		mergeCompData.printTable();
		System.out.println("Quick Sort Swaps: " + quickSwapData.calculateData() + "\n");
		System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Method that performs the needed fast sorting algorithm tests on a range of list sizes
	 * @param permutations The number of permutations to make for each size
	 * @param endPow The final power of ten that lists should be made a size of
	 */
	public static void fastSortTest(int permutations, int endPow){		
		int currentPow = 1;
		while(currentPow < endPow){ //start at lists of length 10, then work our way up
			System.out.println("********************************* Fast Sort Testing on lists of length " + Math.pow(10,currentPow) + " *********************************\n");
			//
			mergeSortTest(permutations, currentPow);
			heapSortTest(permutations, currentPow);
			quickSortTest(permutations, currentPow);
			
			currentPow++;
		}
	}
	
	/**
	 * Method that performs a bin sort test on permutations of a given length
	 * @param length The length of the strings we will perform the bin sort on.
	 */
	public static void permutationBinSortTest(int length){
		Integer[] list = new Integer[length];
		for(int i=0; i<length; i++)
			list[i] = i+1;
		int numPermutations = 0;
		
		PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
		System.out.println("Bin sort test on arrays of length " + length + "...");
		long start = System.currentTimeMillis();
		for(Integer[] perm : permutations)
		{					
			numPermutations++;
			//I removed the permutation printing to speed the process when testing larger lengths; you can print if you want to
			//System.out.println(Arrays.toString(perm));

			//Bin sort data gathered
			Sorts.binSort(perm);
		//	System.out.println(Arrays.toString(sortedList));
		}
		long end =System.currentTimeMillis();
		System.out.println("Number of permutations: " + numPermutations);
		System.out.println((end-start)+"ms" + "\n");
	}
	
	/**
	 * Tests a bin sort on the given number of permutations of arrays of length ten to the given power
	 * @param numCases The number of permutations of the array to make.
	 * @param arraySizePow The number to which we raise 10 to to find the length of the array.
	 */
	public static void runtimeBinSortTest(int numCases, int arraySizePow){
		long start,end;
		System.out.println("Bin sort test on arrays of length " + Math.pow(10, arraySizePow) + "...");
		int counter = numCases;
    	start = System.currentTimeMillis();
		while(counter > 0){
			Integer[] toSort = shuffled((int)Math.pow(10, arraySizePow), 0); //unseeded shuffling
			Sorts.binSort(toSort);	
			counter--;
		}
    	end = System.currentTimeMillis();
		System.out.println("Bin sort time: " + (end-start)+"ms" + "\n");
	}
	
	/**
	 * Method that allows testing using the PermutationSet class to be performed on other methods, such as shell sorting and non-comparison based sorts.
	 * 
	 * Testing for a bubble sort is done in parallel for comparison.
	 * 
	 * @param beginLength the starting length of the strings we will make permutations of
	 * @param endLength the longest length of the strings we will make permutations of
	 */
	public static void permutationSortTest(int beginLength, int endLength){
		for(int i = beginLength; i <= endLength; i++){
			System.out.println("********************************* Testing on lists of length " + i + " *********************************\n");
			
			//Bubble Sort Test in parallel**************************************************************
			Integer[] list = new Integer[i];
			for(int j=0; j<i; j++)
				list[j] = j+1;
			int numPermutations = 0;
			PermutationSet<Integer> permutations = new PermutationSet<Integer>(list);
			System.out.println("Bubble sort test on arrays of length " + i + "...");
			long start = System.currentTimeMillis();
			for(Integer[] perm : permutations)
			{					
				numPermutations++;
				Sorts.bubbleSort(perm); //comparison data is not collected; we only want to compare the run time of the sorts
			}
			long end =System.currentTimeMillis();
			System.out.println("Number of permutations: " + numPermutations);
			System.out.println((end-start)+"ms" + "\n");
			//End of parallel bubble sort test************************************************************
			
			permutationBinSortTest(i);
		}
	}
	
	/**
	 * Method that allows longer lists to be used to test the run time of some sorting algorithms. A merge sort test runs in parallel for comparison
	 * @param permutations The number of permutations we want to test for each array size
	 * @param endPow the last power to which we raise our lists' length to.
	 */
	public static void runtimeTest(int permutations, int endPow){
		int currentPow = 1;
		while(currentPow < endPow){ //start at lists of length 10, then work our way up
			System.out.println("********************************* Testing on lists of length " + Math.pow(10,currentPow) + " *********************************\n");
			//parallel merge sort test**********************************************************
			long start,end;
			System.out.println("Merge sort test on arrays of length " + Math.pow(10, currentPow) + "...");
			int counter = permutations;
	    	start = System.currentTimeMillis();
			while(counter > 0){
				Integer[] toSort = shuffled((int)Math.pow(10, currentPow), 0); //unseeded shuffling
				//System.out.println(Arrays.toString(toSort));
				Sorts.mergeSort(toSort);		
				counter--;
			}
	    	end = System.currentTimeMillis();
			System.out.println("Merge sort time: " + (end-start)+"ms" + "\n");
			//End of parallel merge sort test****************************************************
			
			runtimeBinSortTest(permutations, currentPow); //the arrays that are sorted will be different, although we are looking at the average case.
			//by taking out the data collection bit, we can sort arrays of larger than length 10^6.
			currentPow++;
		}
	}
	

	/**
	 * Returns a shuffled (randomly sorted) array of integers from 1 to the given number
	 * @param n The number of numbers to shuffle
	 * @param seed A random seed, if less than 0 then unseeded
	 * @return An array of shuffled integers
	 */
	public static Integer[] shuffled(int n, int seed)
	{
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0; i<n; i++)
		{
			nums.add(i+1);
		}
		if(seed > 0)
			Collections.shuffle(nums, new Random(seed));
		else
			Collections.shuffle(nums);
		return nums.toArray(new Integer[0]);		
	}

    /**
     * Prints out the given array, with every 20th number on a new line (for readability)
     * @param numbers the array to print out
     */
    public static void printArray(Integer[] numbers)
    {
        for(int i=0; i<numbers.length; i++)
        {
            System.out.print(numbers[i]+", ");
            if(i > 0 && i%20 == 0) //every 20th number, go to the next line
            {
                System.out.println();
            }
        }
        System.out.println();
    }

	
}
