import java.io.*;
import java.util.*;

public class K_Occurrence {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		//HashMap<Integer, ArrayList<Integer>> occurrenceToKth = new HashMap<Integer, ArrayList<Integer>>();


		ArrayList<ArrayList<Integer>> occurrenceToKth = new ArrayList<ArrayList<Integer>>(1000001);
	
		HashSet<Integer> processed = new HashSet<Integer>();
		for (int i = 0; i < 100001; i++) {
			occurrenceToKth.add(i, new ArrayList<Integer>());
		}

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int testSize = 0;
		int numQueries = 0;
		int lineNum = 0;

		int currNum;
		int listInd;

		int kth;
		int target = 0;

		//long start = System.currentTimeMillis();


		while ( (line = input.readLine()) != null) {
		
			st = new StringTokenizer(line);
			
			testSize = Integer.parseInt(st.nextToken());
			numQueries = Integer.parseInt(st.nextToken());
			processed.clear();
			//occurrenceToKth.clear();
			
			listInd = 0;	
			st = new StringTokenizer(input.readLine());
			while (listInd < testSize) {
				currNum = Integer.parseInt(st.nextToken());
				processed.add(currNum);
				occurrenceToKth.get(currNum).add(listInd + 1);
	
				//occurrences.add(listInd + 1);
				/*
				if (occurrences == null) {
                                        occurrences = new ArrayList<Integer>(2);
                                        occurrences.add(listInd + 1);
					occurrenceToKth.put(currNum, occurrences);
                                } else {
					occurrences.add(listInd + 1);
				}
				*/

				
				
	
				/*
				if (occurrenceToKth.containsKey(currNum)) {
					occurrenceToKth.get(currNum).add(listInd + 1);
				} else {
					occurrenceToKth.put(currNum, new ArrayList<Integer>(2));
					occurrenceToKth.get(currNum).add(listInd + 1);
				}
				*/

				listInd ++;
			}


		
			
			while (numQueries > 0) {
				st = new StringTokenizer(input.readLine());
				kth = Integer.parseInt(st.nextToken());	
				target = Integer.parseInt(st.nextToken());
				
				
				 ArrayList<Integer> occurrences = occurrenceToKth.get(target);
				 if (occurrences.size() < kth) {
                        		sb.append(0);
                		} else {
                        		sb.append(occurrences.get(kth - 1));
                		}

                		sb.append("\n");
				numQueries --;	
			}

			for (Integer processed_num : processed) {
				occurrenceToKth.get(processed_num).clear();
			}
						
		}

		//long end = System.currentTimeMillis();
		
		System.out.print(sb);
		//System.out.println(end - start);
		//input.close();

	}
}
