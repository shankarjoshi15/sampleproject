package org.arraysum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraySum {

	public static void main(String[] args) {
	
		int resultsum=14;
		int[] arr = new int[]{7,4,6,-6,3,8,2,1,4};
	    Map<Integer, List<Integer>> cacheMap = new HashMap<Integer, List<Integer>>();
	    List<Integer> initial = new ArrayList<Integer>();
	    initial.add(-1);
	    cacheMap.put(0, initial);
	    int calculatedSum = 0;
		System.out.println("Given Input array is : " + getArrayString(arr) + " sum to be equals: " + resultsum);
	    // Loop across all elements of the array
	    for(int i=0; i< arr.length; i++) {
	        calculatedSum += arr[i];
	        if(cacheMap.containsKey(calculatedSum - resultsum)) {   
	            List<Integer> startIndices = cacheMap.get(calculatedSum - resultsum);
	            for(int start : startIndices) {
	            	System.out.println("Sub array indexes are  "+ (start+1)+ " and  "+ i);
	            	
	            	for(int ind = start+1;ind<i;ind++)
	            	{
	            		System.out.print(arr[ind] + ", ");
	            	}
	                System.out.println("");
	            }
	        }

	        List<Integer> lstIndexes = new ArrayList<Integer>();
	        if(cacheMap.containsKey(calculatedSum)) { 
	            lstIndexes = cacheMap.get(calculatedSum);
	        }
	        lstIndexes.add(i);
	        cacheMap.put(calculatedSum, lstIndexes);
	    }
	
	}
	
	private static String getArrayString(int[] array)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++)
		{
			sb.append(array[i]).append(',');
		}
		return sb.toString();
	}
}
