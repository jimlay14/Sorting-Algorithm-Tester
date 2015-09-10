import java.util.Arrays;

/**
 * A utility class containing methods for sorting arrays
 */
@SuppressWarnings({"unchecked","rawtypes"}) //So we can easily work with arrays of Comparables
public class Sorts
{
	/**
	 * Method that sorts the given list with a bubble sort
	 * @param list The list of comparable items to be sorted
	 * @return an array with comparison and swap data
	 */
	public static int[] bubbleSort(Comparable[] list)
    {
		int comparisons = 0;
		int swaps = 0;
		
		boolean sorted;
    	do
    	{
    		sorted = true;
    		for(int i=0; i<list.length-1; i++)
    		{
    			comparisons++;
    			if(list[i].compareTo(list[i+1]) > 0)
    			{
    				Comparable tmp = list[i];
    				list[i] = list[i+1];
    				list[i+1] = tmp;
    				swaps++;
    				sorted = false;
    			}
    		}
    		
    	}
    	while(!sorted);
//    	System.out.print("Bubble Sort: ");
    	int[] data = {comparisons, swaps};
    	return data;
    }
	
	/**
	 * Method that sorts the given list using a selection sort
	 * @param list The list of comparable items to be sorted
	 * @return an array with comparison and swap data
	 */
	public static int[] selectionSort(Comparable[] list)
	{
		int comparisons = 0;
		int swaps = 0;	
		
		for(int i=0; i<list.length-1; i++)
		{
			int selected = i;
			for(int j=i+1; j<list.length; j++)
			{
				comparisons++; //compares to each item after current index in the array
				if(list[j].compareTo(list[selected]) < 0)
				{
					selected = j;
				}
			}
			Comparable tmp = list[i];
			list[i] = list[selected];
			list[selected] = tmp;
			if(selected != i)
				swaps++; //only count swaps when we do swap indexes
		}
		
		int[] data = {comparisons, swaps};
    	return data;
	}
	
	/**
	 * Method that uses the insertion sort to sort the given list
	 * @param list The list to sort
	 * @return an array with comparison and swap data
	 */
	public static int[] insertionSort(Comparable[] list)
	{
		int comparisons = 0;
		int swaps = 0;
		
		for(int i=1; i<list.length; i++)
		{

			Comparable inserted = list[i]; //who to insert
			int hole = i;
			if(inserted.compareTo(list[hole-1]) >= 0)
				comparisons++; //add one comparison for first comparison with item lower, but only if the comparison would not be counted otherwise
			while(hole > 0 && inserted.compareTo(list[hole-1]) < 0)
			{
				comparisons++; //comparing during loop
				list[hole] = list[hole-1]; //move the hole
				swaps++; //swapping the hole location
				hole--; //reset the index
			}
			if(hole != i){
				swaps++; //only add last swap if original position of item changes
			}
			if (hole != i && hole > 0){
				comparisons++; //add last comparison if item changed position to anything but the start (would not have been counted because the while loop ends)
			}
			
			list[hole] = inserted; //put in the hole
		}
	//	System.out.println(swaps);

		int[] data = {comparisons, swaps};
    	return data;
	}
	
	/**
	 * Algorithm for a bin (bucket sorting)
	 * @param list the list to sort; must be Integer values, and all elements need to be unique in this case
	 */
	public static Integer[] binSort(Integer[] list){
		Integer maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < list.length; i++){
			if(list[i] > maxVal)
				maxVal = list[i]; //loop through the list to find the largest item in the array
		}
		Integer[] newList = new Integer[maxVal+1]; //include one extra spot on the off chance 0 is not included
		for(int i = 0; i < list.length; i++){
			Integer index = list[i];
			newList[index] = index;
		}
		return newList;
	}
	
	/**
	 * Method that uses the shell sort to sort the given list
	 * @param list The list to sort
	 */
	public static void shellSort(Comparable[] list)
	{
		int gap = list.length/2; //start at half the list length
		
//		int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1}; //used in variation of method
//		int gapIndex = 0;
		
		while(gap > 0)
		{
//		while(gapIndex < gaps.length) //for specific gap lists
//		{
//			gap = gaps[gapIndex];
//			if(gap < list.length)
//			{
				for(int i = gap; i < list.length; i++) //go through all of the subarrays
				{
					Comparable inserted = list[i]; //insertion sort
					int hole = i;
					while(hole >= gap && inserted.compareTo(list[hole-gap]) < 0)
					{
						list[hole] = list[hole-gap];
						hole = hole-gap;
					}
					list[hole] = inserted;
				}
//				gapIndex++;
//		}
				if(gap != 2)
					gap = (int)(gap/2.2);
				else
					gap = 1;
			}
	}

	/**
	 * Generic version of the merge sort algorithm that sorts a list of comparable items
	 * @param list The list to sort
	 */
	public static <T extends Comparable<T>> int[] mergeSort(T[] list)
	{
		int[] allData = new int[2];
		if(list.length > 1)
		{
			int mid = list.length/2;
			T[] left = Arrays.copyOfRange(list,0,mid);
			T[] right = Arrays.copyOfRange(list,mid,list.length);
			int[] leftData = mergeSort(left);
			int[] rightData = mergeSort(right);
			int[] thisData = merge(left,right,list);
			allData[0] = leftData[0] + rightData[0] + thisData[0];
			allData[1] = leftData[1] + rightData[1] + thisData[1];
		}
		return allData;
	}

	/**
	 * Generic version of the mergeing method used in the merge sort
	 * @param left The left array to merge into the master list
	 * @param right The right array to merge into the master list
	 * @param master The final list that the left and right lists are merged into
	 */
	public static <T extends Comparable<T>> int[] merge(T[] left, T[] right, T[] master)
	{
		int leftIndex = 0;
		int rightIndex = 0;
		int masterIndex = 0;
		
		int comparisons = 0;
		int swaps = 0;
		
		while(leftIndex < left.length && rightIndex < right.length)
		{
			comparisons++; //comparing two items in the array
			T leftItem = left[leftIndex]; //we'll be referencing this several times
			if(leftItem.compareTo(right[rightIndex]) < 0)
			{
				if(master[masterIndex] != leftItem)
					swaps++; //only increase swaps if something in the master list changed
				
				master[masterIndex] = leftItem;
				leftIndex++;
			}
			else
			{
				if(master[masterIndex] != right[rightIndex])
					swaps++; //only increase swaps if something in the master list changed
				
				master[masterIndex] = right[rightIndex];
				rightIndex++;
			}
			masterIndex++;
		}

		while(leftIndex < left.length)
		{
			if(master[masterIndex] != left[leftIndex])
				swaps++; //increase swaps if changing an item
			
			master[masterIndex] = left[leftIndex];
			leftIndex++;
			masterIndex++;
		}

		while(rightIndex < right.length)
		{
			if(master[masterIndex] != right[rightIndex])
				swaps++; //...
			
			master[masterIndex] = right[rightIndex];
			rightIndex++;
			masterIndex++;
		}
		int[] data = {comparisons, swaps};
    	return data;
	}
	
	public static <T extends Comparable<T>> int[] quickSort(T[] table){
		return quickSort(table, 0, table.length-1);
	}
	
	/**
	 * Quick sort method, adapted from Koffman and Wolfgang text
	 * @param table The list to sort
	 * @return an int[] containing comparison and swap data
	 */
	public static <T extends Comparable<T>> int[] quickSort(T[] table, int first, int last){
		int comparisons = 0;
		int swaps = 0;
		
		int down = last;
		if(first < last){
			comparisons++;
			T pivot = table[first];
			int up = first;
			do{
				while((up < last) && pivot.compareTo(table[up]) >= 0){
					comparisons++;
					up++;
				}
				while(pivot.compareTo(table[down]) < 0){
					comparisons++;
					down--;
				}
				if(up < down){
					swaps++;
					T tmp = table[down];
					table[down] = table[up];
					table[up] = tmp;
				}
			} while (up < down);
			swaps++;
			T tmp = table[down];
			table[down] = table[up];
			table[up] = tmp;
		}
		int[] leftData = quickSort(table, first, down-1);
		int[] rightData = quickSort(table, down+1, last);
		
		int compData = comparisons + leftData[0] + rightData[0];
		int swapData = swaps + leftData[1] + rightData[1];
		int[] data = {compData, swapData};
    	return data;
	}
	
	/**
	 * Heap sorts a comparable array
	 * @param list The list to sort
	 * @return an int[] containing comparison and swap data
	 */
	public static int[] heapSort(Comparable[] list){
		int comparisons = 0;
		int swaps = 0;
		
		//add everyone to the heap (includes comparisons and swapping)
		for(int i=1; i<list.length; i++){
			//adapted from Heap.add()
			int child = i;
			int parent = (child-1)/2;
			while(parent >=0 && list[parent].compareTo(list[child]) < 0){
				comparisons++; //comparing every time
				Comparable tmp = list[parent];
				list[parent] = list[child];
				list[child] = tmp;
				swaps++; //swapping here
				child = parent;
				parent = (child-1)/2;
			}
		}
		//remove everyone from the heap
		for(int last=list.length-1; last > 0; last--){
			//put last guy in his place
			Comparable tmp = list[0];
			list[0] = list[last];
			list[last] = tmp;
			swaps++;
			//adapted from Heap.pop()
			int current = 0;
			boolean adjusting = true; //sentinel
			while(adjusting){
				int leftChild = current*2+1;
				int rightChild = leftChild+1;

				if(leftChild < last) //if has left child
				{
					int maxChild = leftChild; //max heap
					comparisons++; //comparing here
					if(rightChild < last && list[rightChild].compareTo(list[leftChild]) > 0) //has right, and he's bigger?
						maxChild = rightChild;
	 				
					if(list[current].compareTo(list[maxChild]) < 0){
						//swap
						swaps++; //Check!
						tmp = list[maxChild];
						list[maxChild] = list[current];
						list[current] = tmp;
						current = maxChild; //current min, who I just swapped
					} else //kids are smaller, so done
						adjusting = false;
				} else //no kids, done
					adjusting = false;
			}
		}
		int[] data = {comparisons, swaps};
    	return data;
	}
}
