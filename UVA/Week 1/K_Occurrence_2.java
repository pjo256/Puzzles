import java.lang.*;
import java.io.*;
import java.util.*;

public class K_Occurrence_2 {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		

		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//long start = System.currentTimeMillis();
		int testSize = 0;
		int numQueries = 0;
		int lineNum = 0;

		int currNum;
		int listInd;

		int kth;
		int target;

		ArrayList<Integer> list = new ArrayList<Integer>();

		while ( (line = input.readLine()) != null) {
		
			st = new StringTokenizer(line);
			
			if (lineNum == 0) {
				testSize = Integer.parseInt(st.nextToken());
				numQueries = Integer.parseInt(st.nextToken());
						
				lineNum ++;
			} else if (lineNum == 1) {
				list.clear();
				while (st.hasMoreTokens()) {
					currNum = Integer.parseInt(st.nextToken());
					list.add(currNum);	
				}
	
				lineNum ++;
			} else {
				kth = Integer.parseInt(st.nextToken());
				target = Integer.parseInt(st.nextToken());
				queryKthVal(kth, target, list, sb);
				numQueries --;					
			}

			if (numQueries == 0) {
				lineNum = 0;
			}

		}

		//long end = System.currentTimeMillis();
		System.out.print(sb.toString());
		//System.out.println(end - start);
	}

	public static void queryKthVal(int kth, int val, ArrayList<Integer> list,  StringBuilder sb) {

		int currNum;
		int target_ind = -1;

		
		
		for (int ind = 0; ind < list.size(); ind ++) {
			currNum = list.get(ind);

			if (val == currNum) {
				kth --;
			}

			if (kth == 0) {
				target_ind = ind + 1;
				break;
			}
		}


		if (target_ind == -1 || kth != 0) {
			sb.append(0);
		} else {
			sb.append(target_ind);
		}
		sb.append("\n");
	}

	
}
