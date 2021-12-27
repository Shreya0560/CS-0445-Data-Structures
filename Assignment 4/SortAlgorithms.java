import java.util.*;
public class SortAlgorithms
{  
	public static int MIN_SIZE;
	public static int setMinSize(int N) 
	{  
		MIN_SIZE = N;  
		return MIN_SIZE;
	}
	
	
	//SIMPLE QUICKSORT
	public static <T extends Comparable<? super T>>
		   void quickSort(T[] array, int n)
	{
		if(n == 0) 
		{ 
			throw new IndexOutOfBoundsException();
		} 
		quickSort(array, 0, n - 1);
	} // end quickSort

	public static <T extends Comparable<? super T>>
		   void quickSort(T[] array, int first, int last)
	{
		if ((last - first) + 1 < MIN_SIZE)
		{
			insertionSort(array, first, last);
		}
		else
		{
			// create the partition: Smaller | Pivot | Larger
			int pivotIndex = partition(array, first, last);
	      
			// sort subarrays Smaller and Larger
			quickSort(array, first, pivotIndex-1);
			quickSort(array, pivotIndex+1, last);
		} // end if
	}  // end quickSort

	private static <T extends Comparable<? super T>>
	        int partition(T[] a, int first, int last)
	{
		int pivotIndex = last;  // simply pick pivot as rightmost element
		T pivot = a[pivotIndex];

		// determine subarrays Smaller = a[first..endSmaller]
		//                 and Larger  = a[endSmaller+1..last-1]
		// such that elements in Smaller are <= pivot and 
		// elements in Larger are >= pivot; initially, these subarrays are empty

		int indexFromLeft = first; 
		int indexFromRight = last - 1; 

		boolean done = false;
		while (!done)
		{
			// starting at beginning of array, leave elements that are < pivot; 
			// locate first element that is >= pivot
			while (a[indexFromLeft].compareTo(pivot) < 0)
				indexFromLeft++;

			// starting at end of array, leave elements that are > pivot; 
			// locate first element that is <= pivot

			while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
				indexFromRight--;

			// Assertion: a[indexFromLeft] >= pivot and 
			//            a[indexFromRight] <= pivot.

			if (indexFromLeft < indexFromRight)
			{
				swap(a, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else 
				done = true;
		} // end while

		// place pivot between Smaller and Larger subarrays
		swap(a, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;

		// Assertion:
		// Smaller = a[first..pivotIndex-1]
		// Pivot = a[pivotIndex]
		// Larger  = a[pivotIndex + 1..last]

		return pivotIndex; 
	}  // end partition

	private static void swap(Object [] a, int i, int j)
	{
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	} // end swap 
	
	//---------------------------------------------------------
	
	// MERGE SORT
	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] a, int n)
	{
		mergeSort(a, 0, n - 1);
	} // end mergeSort

	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] a, int first, int last)
	{
	  T[] tempArray = (T[])new Comparable<?>[a.length];
	  mergeSort(a, tempArray, first, last);
	} // end mergeSort
	
	private static <T extends Comparable<? super T>>
	        void mergeSort(T[] a, T[] tempArray, int first, int last)
	{
	    if (last - first + 1 < MIN_SIZE)
		{
			insertionSort(a, first, last);
		}
		else
	    {  // sort each half
	      int mid = (first + last)/2;// index of midpoint
	      mergeSort(a, tempArray, first, mid);  // sort left half array[first..mid]
	      mergeSort(a, tempArray, mid + 1, last); // sort right half array[mid+1..last]

			if (a[mid].compareTo(a[mid + 1]) > 0)      // Question 2, Chapter 9
	     	 	merge(a, tempArray, first, mid, last); // merge the two halves
	   //	else skip merge step
	    }  // end if
	}  // end mergeSort
	
	private static <T extends Comparable<? super T>> 
	        void merge(T[] a, T[] tempArray, int first, int mid, int last)
	{
		// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// while both subarrays are not empty, copy the
	   // smaller item into the temporary array
		int index = beginHalf1; // next available location in
								            // tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order
	   
	      if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0)
	      {  
	      	tempArray[index] = a[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {  
	      	tempArray[index] = a[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = a[beginHalf2];
		
	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      a[index] = tempArray[index];
	}  // end merge
// -------------------------------------------------------------------------------

// MEDIAN OF 3 QUICK SORT
	public static <T extends Comparable<? super T>>
		   void quickSort2(T[] array, int n)
	{
		quickSort2(array, 0, n-1);
	} // end quickSort
	
	/** Sorts an array into ascending order. Uses quick sort with
	 *  median-of-three pivot selection for arrays of at least 
	 *  MIN_SIZE elements, and uses insertion sort for other arrays. */
	public static <T extends Comparable<? super T>>
	       void quickSort2(T[] a, int first, int last)
	{
	  if (last - first + 1 < MIN_SIZE)
	  {
	    insertionSort(a, first, last);
	  }
	  else
	  {
	    // create the partition: Smaller | Pivot | Larger
	    int pivotIndex = partition2(a, first, last);
	    
	    // sort subarrays Smaller and Larger
	    quickSort2(a, first, pivotIndex - 1);
	    quickSort2(a, pivotIndex + 1, last);
	  } // end if
	} // end quickSort

	// 12.17
	/** Task: Partitions an array as part of quick sort into two subarrays
	 *        called Smaller and Larger that are separated by a single
	 *        element called the pivot. 
	 *        Elements in Smaller are <= pivot and appear before the 
	 *        pivot in the array.
	 *        Elements in Larger are >= pivot and appear after the 
	 *        pivot in the array.
	 *  @param a      an array of Comparable objects
	 *  @param first  the integer index of the first array element; 
	 *                first >= 0 and < a.length 
	 *  @param last   the integer index of the last array element; 
	 *                last - first >= 3; last < a.length
	 *  @return the index of the pivot */
	private static <T extends Comparable<? super T>>
	        int partition2(T[] a, int first, int last)
	{
	  int mid = (first + last)/2;
	  sortFirstMiddleLast(a, first, mid, last);
	  
	  // Assertion: The pivot is a[mid]; a[first] <= pivot and 
	  // a[last] >= pivot, so do not compare these two array elements
	  // with pivot.
	  
	  // move pivot to next-to-last position in array
	  swap2(a, mid, last - 1);
	  int pivotIndex = last - 1;
	  T pivot = a[pivotIndex];
	  
	  // determine subarrays Smaller = a[first..endSmaller]
	  // and                 Larger  = a[endSmaller+1..last-1]
	  // such that elements in Smaller are <= pivot and 
	  // elements in Larger are >= pivot; initially, these subarrays are empty

	  int indexFromLeft = first + 1; 
	  int indexFromRight = last - 2; 
	  boolean done = false;
	  while (!done)
	  {
	    // starting at beginning of array, leave elements that are < pivot;
	    // locate first element that is >= pivot; you will find one,
	    // since last element is >= pivot
	    while (a[indexFromLeft].compareTo(pivot) < 0)
	      indexFromLeft++;
	      
	    // starting at end of array, leave elements that are > pivot; 
	    // locate first element that is <= pivot; you will find one, 
	    // since first element is <= pivot
	    while (a[indexFromRight].compareTo(pivot) > 0)
	      indexFromRight--;
	      
	    assert a[indexFromLeft].compareTo(pivot) >= 0 && 
	           a[indexFromRight].compareTo(pivot) <= 0;
	           
	    if (indexFromLeft < indexFromRight)
	    {
	      swap2(a, indexFromLeft, indexFromRight);
	      indexFromLeft++;
	      indexFromRight--;
	    }
	    else 
	      done = true;
	  } // end while
	  
	  // place pivot between Smaller and Larger subarrays
	  swap2(a, pivotIndex, indexFromLeft);
	  pivotIndex = indexFromLeft;
	  
	  // Assertion:
	  //   Smaller = a[first..pivotIndex-1]
	  //   Pivot = a[pivotIndex]
	  //   Larger = a[pivotIndex+1..last]
	  
	  return pivotIndex; 
	} // end partition

	// 12.16
	/** Task: Sorts the first, middle, and last elements of an 
	 *        array into ascending order.
	 *  @param a      an array of Comparable objects
	 *  @param first  the integer index of the first array element; 
	 *                first >= 0 and < a.length 
	 *  @param mid    the integer index of the middle array element
	 *  @param last   the integer index of the last array element; 
	 *                last - first >= 2, last < a.length */
	private static <T extends Comparable<? super T>>
	        void sortFirstMiddleLast(T[] a, int first, int mid, int last)
	{
	  order(a, first, mid); // make a[first] <= a[mid]
	  order(a, mid, last);  // make a[mid] <= a[last]
	  order(a, first, mid); // make a[first] <= a[mid]
	} // end sortFirstMiddleLast

	/** Task: Orders two given array elements into ascending order
	 *        so that a[i] <= a[j].
	 *  @param a  an array of Comparable objects
	 *  @param i  an integer >= 0 and < array.length
	 *  @param j  an integer >= 0 and < array.length */
	private static <T extends Comparable<? super T>>
	        void order(T[] a, int i, int j)
	{
	  if (a[i].compareTo(a[j]) > 0)
	    swap2(a, i, j);
	} // end order

  /** Task: Swaps the array elements a[i] and a[j].
   *  @param a  an array of objects
   *  @param i  an integer >= 0 and < a.length
   *  @param j  an integer >= 0 and < a.length */
  private static void swap2(Object[] a, int i, int j)
  {
    Object temp = a[i];
    a[i] = a[j];
    a[j] = temp; 
  } // end swap

  public static <T extends Comparable<? super T>> 
	       void insertionSort(T[] a, int n)
	{
		insertionSort(a, 0, n - 1);
	} // end insertionSort

  public static <T extends Comparable<? super T>> 
	       void insertionSort(T[] a, int first, int last)
	{
		int unsorted, index;
		
		for (unsorted = first + 1; unsorted <= last; unsorted++)
		{   // Assertion: a[first] <= a[first + 1] <= ... <= a[unsorted - 1]
		
			T firstUnsorted = a[unsorted];
			
			insertInOrder(firstUnsorted, a, first, unsorted - 1);
		} // end for
	} // end insertionSort

  private static <T extends Comparable<? super T>> 
	        void insertInOrder(T element, T[] a, int begin, int end)
	{
		int index;
		
		for (index = end; (index >= begin) && (element.compareTo(a[index]) < 0); index--)
		{
			a[index + 1] = a[index]; // make room
		} // end for
		
		// Assertion: a[index + 1] is available
		a[index + 1] = element;  // insert
	} // end insertInOrder  
	

//---------------------------------------------------------------------------------------
//Random Pivot QuickSort 
public static <T extends Comparable<? super T>>
		   void randquickSort(T[] array, int n)
	{
		//Random rand = new Random(); 
		randquickSort(array, 0, n-1);
	} // end quickSort

	public static <T extends Comparable<? super T>>
		   void randquickSort(T[] array, int first, int last)
	{
		if ((last - first) + 1 < MIN_SIZE)
		{
			insertionSort(array, first, last);
		}
		else
		{
			//int intrand = rand.nextInt(last + 1);
			int pivotIndex = partition3(array, first, last);
	      
			// sort subarrays Smaller and Larger
			//Random rand = new Random();  
			randquickSort(array, first, pivotIndex-1);
			randquickSort(array, pivotIndex+1, last);
		} // end if
	}  // end quickSort

	private static <T extends Comparable<? super T>>
	        int partition3(T[] a, int first, int last)
	{
		Random intrand = new Random(); 
		int pivotIndex = intrand.nextInt((last - first) + 1) + first; 
		T pivot = a[pivotIndex];
		
		swap3(a, pivotIndex, last); //move pivot to last position in array
		pivotIndex = last;

		// determine subarrays Smaller = a[first..endSmaller]
		//                 and Larger  = a[endSmaller+1..last-1]
		// such that elements in Smaller are <= pivot and 
		// elements in Larger are >= pivot; initially, these subarrays are empty

		int indexFromLeft = first; 
		int indexFromRight = last - 1; 

		boolean done = false;
		while (!done)
		{
			// starting at beginning of array, leave elements that are < pivot; 
			// locate first element that is >= pivot
			while (a[indexFromLeft].compareTo(pivot) < 0)
				indexFromLeft++;

			// starting at end of array, leave elements that are > pivot; 
			// locate first element that is <= pivot

			while (a[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first)
				indexFromRight--;

			// Assertion: a[indexFromLeft] >= pivot and 
			//            a[indexFromRight] <= pivot.

			if (indexFromLeft < indexFromRight)
			{
				swap3(a, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else 
				done = true;
		} // end while

		// place pivot between Smaller and Larger subarrays
		swap3(a, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;

		// Assertion:
		// Smaller = a[first..pivotIndex-1]
		// Pivot = a[pivotIndex]
		// Larger  = a[pivotIndex + 1..last]

		return pivotIndex; 
	}  // end partition

	private static void swap3(Object [] a, int i, int j)
	{
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	} // end swap 
	
}