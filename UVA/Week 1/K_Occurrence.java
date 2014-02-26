	import java.io.*;
	import java.util.*;

	public class K_Occurrence {
		
		public static void main(String[] args) throws Exception {
		
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String line;
			
			ArrayList<ArrayList<Integer>> occurrenceToKth = new ArrayList<ArrayList<Integer>>(1000001);
		
		//HashSet<Integer> processed = new HashSet<Integer>();
		int [] processed = new int[1000000];
		int test_counter = 0;

		for (int i = 0; i < 1000001; i++) {
			occurrenceToKth.add(i, new ArrayList<Integer>());
		}

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		long start = System.currentTimeMillis();		
		int testSize = 0;
		int numQueries = 0;
		int lineNum = 0;

		int currNum;
		int listInd;

		int kth;
		int target = 0;

		while ( (line = input.readLine()) != null) {
		
			st = new StringTokenizer(line);
			
			testSize = Integer.parseInt(st.nextToken());
			numQueries = Integer.parseInt(st.nextToken());
			//processed = new HashSet<Integer>();
			listInd = 0;	
			st = new StringTokenizer(input.readLine());
			while (st.hasMoreTokens()) {
				currNum = Integer.parseInt(st.nextToken());
				if (processed[currNum] != test_counter) {
					occurrenceToKth.get(currNum).clear();
					occurrenceToKth.get(currNum).add(listInd + 1);
					processed[currNum]++;
				} else 
					occurrenceToKth.get(currNum).add(listInd + 1);
	
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

			//for (Integer processed_num : processed) {
			//	occurrenceToKth.get(processed_num).clear();
			//}
						
		}

		
		System.out.print(sb.toString());
		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}
}

