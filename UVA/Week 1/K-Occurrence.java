import java.lang.*;
import java.io.*;
import java.util.*;

public class K-Occurrence {
	
	

	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		HashMap<Integer, ArrayList<Integer>> occurenceToKth = new HashMap<Integer, ArrayList<Integer>>();

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int testSize;
		int numQueries = 0;
		int lineNum = 0;

		int currNum;
		int listInd;

		int kth;
		int target;

		while ( (line = input.readLine()) != null) {
			
			st = new StringTokenizer(line);
			
			if (lineNum == 0) {
				testSize = Integer.parseInt(st.nextToken());
				numQueries = Integer.parseInt(st.nextToken());

				lineNum ++;
			} else if (lineNum == 1) {
				listInd = 0;
				while (st.hasMoreTokens()) {
					currNum = Integer.parseInt(st.nextToken());
					
					if (occurenceToKth.containsKey(currNum)) {
						occurenceToKth.get(currNum).add(listInd + 1);
					} else {
						occurenceToKth.put(currNum, new ArrayList<Integer>());
						occurenceToKth.get(currNum).add(listInd + 1);
					}

					listInd ++;		
				}

				lineNum ++;
			} else {
				while(numQueries > 0) {	
					kth = Integer.parseInt(st.nextToken());
					target = Integer.parseInt(st.nextToken());

					queryKthVal(kth, target, occurenceToKth, sb);
					numQueries --;
				}

				lineNum = 0;
			}
		
		}

		System.out.print(sb);

	}

	public static void queryKthVal(int kth, int val, HashMap<Integer, ArrayList<Integer>> occurrenceToKth, StringBuilder sb) {

		if (!occurrenceToKth.containsKey(val)) {
			sb.append(0);
		} else {

			ArrayList<Integer> occurences = occurrenceToKth.get(val);
			sb.append(occurences.get(kth - 1));
		}

		sb.append("\n");
	}

	
}

