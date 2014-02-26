import java.io.*;
import java.util.*;

public class Marbles {

	public static int binarySearch(int[] list, int lo, int hi, int key) {
		
		if (hi < lo) {
			return -1;
		} 
		else {
			int mid = (hi - lo)/2 + lo;

			if (list[mid] < key) {
				return binarySearch(list, mid + 1, hi, key);
			} else if (list[mid] > key) {
				return binarySearch(list, lo, mid - 1, key);
			} else {
				
				while((mid - 1) >= 0 && list[mid - 1] == key) {
					mid -= 1;
					
				}

				//1-indexed
				return mid + 1;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int numMarbles;
		int numQueries;

		int ind;
		int[] marbles;

		int queryKey;
		int testCase = 1;
		int firstInd;

		while( (line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			numMarbles = Integer.parseInt(st.nextToken());
			numQueries = Integer.parseInt(st.nextToken());
	
			if (numMarbles == 0 && numQueries == 0) {
				break;
			}
			marbles = new int[numMarbles];
			sb.append("CASE# " + testCase + ":\n");
			ind = 0;
			while (ind < numMarbles) {
				marbles[ind] = Integer.parseInt(input.readLine());
				ind ++;
			}

			Arrays.sort(marbles);
			while (numQueries > 0) {
				queryKey = Integer.parseInt(input.readLine());
				firstInd = binarySearch(marbles, 0, numMarbles - 1, queryKey); 	
				
				if (firstInd != -1) {
					sb.append(queryKey + " found at " + firstInd + "\n");
				} else {
					sb.append(queryKey + " not found\n");
				}
				numQueries --;
			}
	
			testCase ++;
		}

		System.out.print(sb);
	}
}
