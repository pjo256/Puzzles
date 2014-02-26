import java.io.*;
import java.util.*;

public class Ants {

	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
	
		st = new StringTokenizer(input.readLine());
		int numTests = Integer.parseInt(st.nextToken());
		int numAnts = 0;
		int currPos = 0;
		int lenPole = 0;

		int min_time;
		int max_time;

		st = new StringTokenizer(input.readLine());
		while (st.hasMoreTokens()) {
			
			if (st.hasMoreTokens()) { lenPole = Integer.parseInt(st.nextToken()); }
			else { 
				st = new StringTokenizer(input.readLine()); 
				lenPole = Integer.parseInt(st.nextToken());
			}

			if (st.hasMoreTokens()) { numAnts = Integer.parseInt(st.nextToken()); }
                        else { 
				st = new StringTokenizer(input.readLine()); 
				numAnts = Integer.parseInt(st.nextToken());
			}

			min_time = Integer.MIN_VALUE;
			max_time = Integer.MIN_VALUE;
			
			if (!st.hasMoreTokens()) {
				st = new StringTokenizer(input.readLine());
			}

			while (numAnts > 0) {
				while (st.hasMoreTokens()) {
					currPos = Integer.parseInt(st.nextToken());	
					min_time = Math.max(min_time, Math.min(currPos, lenPole - currPos));
					max_time = Math.max(max_time, Math.max(currPos, lenPole - currPos));
					numAnts --;
					if (numAnts == 0) {
						break;
					}	
				}

				if (numAnts > 0 || !st.hasMoreTokens()) {
					if ((line = input.readLine()) != null) {
						st = new StringTokenizer(line);
					}
				}
			}

			sb.append(min_time + " " + max_time + "\n");
		}

		System.out.print(sb);
	}

}
