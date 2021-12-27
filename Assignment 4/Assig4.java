//import necessary packages
import java.util.*; 
import java.io.FileWriter;  
import java.io.File;
public class Assig4 extends SortAlgorithms
{ 
	static int numtrials;   
	static int arrsize; 
	//static int MIN_SIZE; This variable is automatically inherited because I extended the SortAlgorithms class
	
	static Integer[] minsizes = new Integer[]{3,15,25,50,100,500}; //The different min sizes being tested
	static Random rand = new Random(); //Random object created for random data config.
	
	//an empty array created for each data config. (will fill in later)
	static Integer[] myRand; 
	static Integer[] mySort; 
	static Integer[] myrevSort; 
	
	static File myFile; 
	static FileWriter myWriter; 
	static int initSetUp;  
	
	
	
	public static void main(String[] args) 
	{
		Scanner as = new Scanner(System.in);
		System.out.println("Enter array size");
		arrsize = as.nextInt();     
	  
		Scanner nt = new Scanner(System.in);
		System.out.println("Enter number of trials"); 
		numtrials = nt.nextInt();  
	  
		if(arrsize > 20) //Trace Output Mode is off
		{
			
			Scanner f = new Scanner(System.in);
			System.out.println("Output file name"); 
			String filename = f.next();
			try 
			{
				myFile = new File(filename); 
				myWriter = new FileWriter(myFile); 
			} 
			catch(Exception e) 
			{  
				System.out.println(e.getStackTrace()[0]);
				System.out.println(e);
			} 
		} 
	
		myRand = new Integer[arrsize];
		mySort = new Integer[arrsize];
		myrevSort = new Integer[arrsize];   
		
		randArr(myRand); //fills the array with random sorted data
		sortArr(mySort); //fils the array with sorted data
		revsortArr(myrevSort); //fills the arrays with reverse sorted data
		  
		
		for(int ms : minsizes) //for each min size
		{  
			MIN_SIZE = setMinSize(ms);
			for(int i = 0; i < 3; i ++) //for each data config, run all four algorithms
			{	
					if(i == 0) //data config. is random
					{  
						initSetUp = 1;
						findTimes(myRand,i); //all four algorithms are run, and times are generated
					}  
					else if(i == 1) //data config. is sorted
					{ 
						
						initSetUp = 2; 
						findTimes(mySort,i); 
						
					}
					
					else  
					{
						initSetUp = 3; //data config. is reverse sorted
						findTimes(myrevSort,i); 
					}  
				
			}  
		}  
		if(arrsize > 20)
		{ 
			try{
				myWriter.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	public static void copyArr(Integer [] newArr, Integer [] oldArr) //method to copy array values into another array
	{
		for (int i = 0; i < oldArr.length; i++)
		{	
			newArr[i] = oldArr[i]; 
		}
	}


	public static void dispArr(Integer [] Arr) //method to display array to terminal
	{
		for (int i = 0; i < Arr.length; i++)
		{
			System.out.print(Arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void sortArr(Integer[] arr) //inputs sorted values into an array
	{
		//Integer[] mysortarr = new Integer[arrsize];
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = i + 1;
		} 
	} 
		

	public static void revsortArr(Integer[] arr) //inputs reverse sorted values into an array
	{
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = arrsize - i;
		} 
	} 

	public static void randArr(Integer[] arr) //inputs randomly sorted values into an array
	{
		Random R = new Random(); 
		
		for (int i = 0; i < arr.length; i++)
		{
			arr[i] = Integer.valueOf(R.nextInt(arrsize));
		} 
	}   
	
	
	
	
	public static void findTimes(Integer [] myArray, int setup)
	{
		//4 arrays created, each one for each algorithm
		Integer[] simpQuick = new Integer[arrsize];
		Integer[] Merge = new Integer[arrsize];
		Integer[] Medof3 = new Integer[arrsize];
		Integer[] randPiv = new Integer[arrsize];

		long startTime;
		long finishTime;
		long diff; 
		
		long allQuickTimes = 0;  //total time to run all QuickSort over 10 trials
		double avgQuickTime = 0; //average time to run QuickSort over 10 trials 

		long allMergeTimes = 0;    
		double avgMergeTime = 0;   
		
		long allMed3Times = 0;   
		double avgMed3Time = 0;   

		long allRandomTimes = 0;    
		double avgRandomTime = 0;   

	
		
		for(int i = 0; i < numtrials; i++) //for each trial, run the four algorithms
		{ 

			//Based on the data inital configuration, values are put into myArray
			if(setup == 0)
			{
				randArr(myArray);
			} 
			else if(setup == 1)
			{
				sortArr(myArray);
			} 
			else 
			{ 
				revsortArr(myArray);
			} 
			
			//the same values are copied into each of the four algorithm arrays
			copyArr(simpQuick, myArray);
			copyArr(Merge, myArray);
			copyArr(Medof3, myArray);
			copyArr(randPiv, myArray);
			
			
			if(arrsize <= 100000) //quickSort
			{
				startTime = System.nanoTime();
				quickSort(simpQuick,arrsize);  
				finishTime = System.nanoTime();  
				diff = (finishTime - startTime);   
				allQuickTimes += diff; 
			}
			
			
			
			//mergeSort
			startTime = System.nanoTime();
			mergeSort(Merge,arrsize);  
			finishTime = System.nanoTime();  
			diff = (finishTime - startTime);   
			allMergeTimes += diff;


		
			//Median of 3 Sort
			startTime = System.nanoTime();
			quickSort2(Medof3,arrsize);  
			finishTime = System.nanoTime();  
			diff = (finishTime - startTime);   
			allMed3Times += diff;

			
			//Random QuickSort
			startTime = System.nanoTime();
			randquickSort(randPiv,arrsize);  
			finishTime = System.nanoTime();  
			diff = (finishTime - startTime);   
			allRandomTimes += diff;

	
			if(arrsize <= 20) //Trace output mode is on
			{
				
				if(initSetUp == 1) //Random Data Config. 
				{
					System.out.println();
					System.out.println("Algorithm: Simple QuickSort:");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Random");
					System.out.println("Before simple QuickSort:");
					dispArr(myArray); 
					System.out.println("After simple QuickSort:");
					dispArr(simpQuick);

					
					System.out.println();
					System.out.println("Algorithm: MergeSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Random");
					System.out.println("Before MergeSort:");
					dispArr(myArray);
					System.out.println("After MergeSort:");
					dispArr(Merge);

					
					System.out.println();
					System.out.println("Algorithm: Median of 3 QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Random");
					System.out.println("Before Median of 3 Quicksort:");
					dispArr(myArray);
					System.out.println("After Median of 3 Quicksort:");
					dispArr(Medof3);

					
					System.out.println(); 
					System.out.println("Algorithm: Random QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Random");
					System.out.println("Before Random QuickSort");
					dispArr(myArray);
					System.out.println("After Random QuickSort:");
					dispArr(randPiv);  
				} 
				else if(initSetUp == 2) //Sorted Data Config.
				{  
					System.out.println();
					System.out.println("Algorithm: Simple QuickSort:");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Sorted");
					System.out.println("Before simple QuickSort:");
					dispArr(myArray); 
					System.out.println("After simple QuickSort:");
					dispArr(simpQuick);

					
					System.out.println();
					System.out.println("Algorithm: MergeSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Sorted");
					System.out.println("Before MergeSort:");
					dispArr(myArray);
					System.out.println("After MergeSort:");
					dispArr(Merge);

					
					System.out.println();
					System.out.println("Algorithm: Median of 3 QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Sorted");
					System.out.println("Before Median of 3 Quicksort:");
					dispArr(myArray);
					System.out.println("After Median of 3 Quicksort:");
					dispArr(Medof3);

					
					System.out.println(); 
					System.out.println("Algorithm: Random QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Sorted");
					System.out.println("Before Random QuickSort");
					dispArr(myArray);
					System.out.println("After Random QuickSort:");
					dispArr(randPiv);  
				} 
				else //Reverse Sorted Data Config.
				{ 
					System.out.println();
					System.out.println("Algorithm: Simple QuickSort:");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Reverse Sorted");
					System.out.println("Before simple QuickSort:");
					dispArr(myArray); 
					System.out.println("After simple QuickSort:");
					dispArr(simpQuick);

					
					System.out.println();
					System.out.println("Algorithm: MergeSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Reverse Sorted");
					System.out.println("Before MergeSort:");
					dispArr(myArray);
					System.out.println("After MergeSort:");
					dispArr(Merge);

					
					System.out.println();
					System.out.println("Algorithm: Median of 3 QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Reverse Sorted");
					System.out.println("Before Median of 3 Quicksort:");
					dispArr(myArray);
					System.out.println("After Median of 3 Quicksort:");
					dispArr(Medof3);

					
					System.out.println(); 
					System.out.println("Algorithm: Random QuickSort");
					System.out.println("Array Size: " + arrsize);
					System.out.println("Base Case Less Than: " + MIN_SIZE);
					System.out.println("Data Setup: Reverse Sorted");
					System.out.println("Before Random QuickSort");
					dispArr(myArray);
					System.out.println("After Random QuickSort:");
					dispArr(randPiv);  
				}
			}
			
		} //end for loop 
		
		if(arrsize > 20) //Trace Output mode is off
			{
				try
				{
					if(initSetUp == 1) //Random
					{ 
						if(arrsize <= 100000) 
						{
							
							avgQuickTime = allQuickTimes/(double) numtrials; //calculating avg time
							avgQuickTime = avgQuickTime/1000000000; //convert time from nanoseconds to seconds 
							
							myWriter.write("\n");
							myWriter.write("\nAlgorithm: Simple QuickSort");
							myWriter.write("\nArray Size: " + arrsize);
							myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
							myWriter.write("\nData Setup: Random");
							myWriter.write("\nNumber of trials: " + numtrials);
							myWriter.write("\nAverage Time per trial: " + avgQuickTime + " sec.");  
						}
					
						avgMergeTime = allMergeTimes/(double) numtrials; 
						avgMergeTime = avgMergeTime/1000000000;
					

						myWriter.write("\n"); 
						myWriter.write("\nAlgorithm: MergeSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Random");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMergeTime + " sec."); 
				
						
						avgMed3Time = allMed3Times/(double) numtrials; 
						avgMed3Time = avgMed3Time/1000000000;
					

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Med-of-3 QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Random");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMed3Time + " sec."); 


					
						avgRandomTime = allRandomTimes/(double) numtrials; 
						avgRandomTime = avgRandomTime/1000000000;

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Random Pivot QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Random");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgRandomTime + " sec."); 
					
					} 
					else if(initSetUp == 2) //Sorted
					{ 
						if(arrsize <= 100000) 
						{
							avgQuickTime = allQuickTimes/(double) numtrials;
							avgQuickTime = avgQuickTime/1000000000;
							
							myWriter.write("\n");
							myWriter.write("\nAlgorithm: Simple QuickSort");
							myWriter.write("\nArray Size: " + arrsize);
							myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
							myWriter.write("\nData Setup: Sorted");
							myWriter.write("\nNumber of trials: " + numtrials); 
							myWriter.write("\nAverage Time per trial: " + avgQuickTime + " sec."); 
						}
					
						avgMergeTime = allMergeTimes/(double) numtrials; 
						avgMergeTime = avgMergeTime/1000000000;
					

						myWriter.write("\n"); 
						myWriter.write("\nAlgorithm: MergeSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMergeTime + " sec."); 
				
						
						avgMed3Time = allMed3Times/(double) numtrials; 
						avgMed3Time = avgMed3Time/1000000000;
					

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Med-of-3 QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMed3Time + " sec."); 

					
						avgRandomTime = allRandomTimes/(double) numtrials; 
						avgRandomTime = avgRandomTime/1000000000;

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Random Pivot QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgRandomTime + " sec.");
					
					} 
					else //Reverse Sorted
					{  
						if(arrsize <= 100000) 
						{
							avgQuickTime = allQuickTimes/(double) numtrials; 
							avgQuickTime = avgQuickTime/1000000000;
							
							myWriter.write("\n");
							myWriter.write("\nAlgorithm: Simple QuickSort");
							myWriter.write("\nArray Size: " + arrsize);
							myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
							myWriter.write("\nData Setup: Reverse Sorted");
							myWriter.write("\nNumber of trials: " + numtrials); 
							myWriter.write("\nAverage Time per trial: " + avgQuickTime + " sec.");  
						}
					
						avgMergeTime = allMergeTimes/(double) numtrials; 
						avgMergeTime = avgMergeTime/1000000000;
					

						myWriter.write("\n"); 
						myWriter.write("\nAlgorithm: MergeSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Reverse Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMergeTime + " sec."); 
				
						
						avgMed3Time = allMed3Times/(double) numtrials; 
						avgMed3Time = avgMed3Time/1000000000;
					

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Med-of-3 QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Reverse Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgMed3Time + " sec."); 


					
						avgRandomTime = allRandomTimes/(double) numtrials; 
						avgRandomTime = avgRandomTime/1000000000;

						myWriter.write("\n");
						myWriter.write("\nAlgorithm: Random Pivot QuickSort");
						myWriter.write("\nArray Size: " + arrsize);
						myWriter.write("\nBase Case Less Than: " + MIN_SIZE);
						myWriter.write("\nData Setup: Reverse Sorted");
						myWriter.write("\nNumber of trials: " + numtrials); 
						myWriter.write("\nAverage Time per trial: " + avgRandomTime + " sec."); 
					
					}
				} //end try
				catch(Exception e)
				{
					System.out.println(e);
				} //end catch
			}
		
	}//end findTimes

}


