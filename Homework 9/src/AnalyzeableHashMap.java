
/**
 * A customized HashMap made to store data from sorts. This is meant to allow for data gathering to take place within Java memory space
 * This HashMap is also be sorted via bin (bucket) sort - see below.
 * 
 * Underlying data structure: A bin (bucket) sorted array holding entries. Key values of entries correspond directly to array position.
 * 
 * New Methods:
 * 
 * 	computeMin(): computes the minimum key value of the list
 *  computeMax(): computes the maximum key value of the list
 * 	computeAverage(): computes the average value of items in the list
 *  calculateData(): finds the minimum, maximum, and average value of the array and returns this as a string.
 *  
 *  Changes from generic HashMap:
 *  
 *  Entry class is no longer generic, but only holds two Integer values, the key and the count of that key
 *  HashCodes are no longer used to index entries. Rather, using bin sorting, each key is indexed in the underlying array by the Integer value of the key.
 *  put() is used to simply place a given entry at its proper location, rather than putting in an entirely new entry.
 *  The add() method now does this, by adding a new entry if needed and increasing the existing count otherwise.
 *  The rehash() method now takes in a size parameter and increases the array to that size.
 *  
 *  Although comparison and swapping data could probably be handled in an array in the SortingTester class,
 *  Creating this AnalyzeableHashMap allows for different sorting algorithms to be tested the same way, with
 *  less room for error by making arrays on their own.
 * 
 * AUTHOR'S WARNING - This class is designed explicitly for mapping Integer keys to Integer counts through adding Integer inputs.
 * 						This class also relies upon the use of Integers to maintain accurate minimum and maximum values.
 * 						This class is NOT a generic HashMap. Extending this class may involve changing the Entry class,
 * 						standard HashMap methods (put, rehash,...), and sorting method proofs used. However, in the context
 * 						of gathering an analyzing data for Sorting algorithms, these drawbacks are advantages.
 * 						This class could be improved by using a hashing function to index Entries, allowing for lists
 * 						with large numbers of comparisons to be analyzed; although the computeMax method would need to be redefined.
 * 
 * @author Jacob Imlay
 */
public class AnalyzeableHashMap
{
	
	/**
	 * Class that stores two values: The Integer Data, and the number of times that data occurs
	 * @author Jacob Imlay
	 *
	 */
	public static class Entry
	{
		private Integer key; //The key of the value
		private Integer count; // The count that said key occurs
		
		public Entry(Integer key)
		{
			this.key = key;
			this.count = 1; //starts at one (we begin an entry with a count of one)
		}
				
		public String toString()
		{
			return this.key + " : "+this.count;
		}
	}

	private Entry[] entries; //modified to be a direct array of entries
	private int size;
	
	private final int INITIAL_CAPACITY = 1; //initial capacity is 1 to ensure maximum values correspond to keys
	
	public AnalyzeableHashMap()
	{
		this.entries = new Entry[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	//******************************************************
	//Analyzing Methods
	
	/**
	 * Computes the min by looping through the bin sorted array from the bottom, and returning the key of the first entry found
	 * @return The minimum value of the HashMap
	 */
	public Integer computeMin(){
		Entry minData = entries[0]; //first check the 0 value
		int index = 1;
		while(minData == null){
			minData = entries[index]; //while we are still null, continue looking for valid keys
			index++;
		}
		return minData.key; //return the first occurrence of a valid key, which is by definition of a sorted array the minimum value.
	}
	
	public Integer computeMax(){

		return entries.length - 1; //from the definition of our hashing function, this is the maximum value entered.
		
		//Here's a proof by induction:
		
		//Base case: when we make the list, there is only one spot, the zero spot. We return null if there is no item.
		//When we add an item, it is either (a) within the current range of the array, or (b) above the size of the array, or (c), below the size of the array.
		//  (a) - If the item is less than the current last item, then we know that it is not the current maximum as by definition it is not the last item
		//  (b) - If the item is greater than the current last item, then with our rehashing function below, we resize the array so that
		//			the new item is at index size - 1. Therefore, if the item is the now current maximum value, it is by definition at position entries.length -1
		//  (c) - lastly, in the trivial case where the new item is less than zero, than by the definition of our add method this item is not added.
		//
		//Finally, from our definition of the Entry and AnalyzeableHashMap classes, each key value corresponds to its position in the array.
		// Therefore, by induction, we can state that the maximum desired value is at position entries.length - 1.
	}
	
	/**
	 * Computes the average of values in the array by calculating the sum / the number needed to divide
	 * @return The average of values in the array
	 */
	public Double calculateAverage(){
		Double sum = 0.0;
		Double toDivide = 0.0;
		for(int i = 0; i < entries.length; i++){
			if(entries[i] != null){
				sum += entries[i].key * entries[i].count; //sum increases by the value multiplied by the number of time the value occurs
				toDivide += entries[i].count; //divisor increases by the count that it occurred (sums to total number of permutations)
			}
		}
		return sum / (toDivide);
	}
	
	/**
	 * Computes the min, max, and average of the 
	 * @return
	 */
	public String calculateData(){
		//compute min
		Integer min = computeMin();
		
		//compute max
		Integer max = computeMax();
		
		//compute averages
		Double average = calculateAverage();
		
		//Extra information, if desired:
		//printTable();
		
		return "(min: " + min + ", max: " + max + ", average: " + average + ")";
	}
	
	
		
	/**
	 * add in an entry; this works by:
	 *  If the key is new, then a new entry with count 0 is made.
	 *  Otherwise, the count of the Entry is simply increased
	 */
	public void add(Integer key)
	{
		if(key < 0)
			throw new IllegalArgumentException("This HashMap is built only for non-zero natural number counts");
		
		//REALLY important piece here: we only rehash IF the new key is larger than the current array, ensuring the max value is the last item in the array
		if(key > entries.length-1)
			rehash(key);	

		Entry bin = entries[key];
		if(bin == null)
		{
			bin = new Entry(key);
			entries[key] = bin; //if this is a new entry, make a new entry
			size++; //increase number of items in list
		} else {
			entries[key].count++; //otherwise, increase the count of the entry's occurrence
		}	
	//	System.out.println(bin);
	}
	
	/**
	 * Put in an entry; this is used ONLY when adjusting the array sizes so as to avoid errors, or overwriting data
	 */
	public void put(Entry entry)
	{		
		entries[entry.key] = entry;
	}
	
	/**
	 * Returns the entry data for a given key
	 */
	public Entry get(Integer key)
	{
		if(entries[key] != null)
			return entries[key];
		else
			return null;		
	}
	
	/**
	 * Using bin sorting technique, rather than typical doubling, we make a new array of the exact size we want
	 * @param newValue
	 */
	public void rehash(Integer newValue)
	{
		Entry[] oldTable = entries;
		entries = new Entry[newValue+1];

		for(int i=0; i<oldTable.length; i++)
		{
			if(oldTable[i] != null)
			{
				put(oldTable[i]);
			}
		}
	
	}	
	
	public int size()
	{
		return size;
	}

	public void printTable()
	{
		for(int i = 0; i < entries.length; i++){
			if(entries[i] != null)
				System.out.println("key: " + entries[i].key + ", count: " + entries[i].count);
		}
	}

}
