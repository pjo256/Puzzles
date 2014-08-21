/**
 * UVA 111420
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/D
 */

import java.util.*;
import java.io.*;

public class Drawers {

	public static long countWays(long[][][] lookUp, int locked, int numDrawers, int drawersToSecure) {
		int drawersFree = numDrawers - drawersToSecure;
		if (drawersFree < 0 || drawersToSecure < 0) {
			return 0;
		} else if (/*drawersFree == 0*/ numDrawers == 0 && drawersToSecure == 0) {
			lookUp[numDrawers][drawersToSecure][locked] = 1;
			return 1;
		} else if (lookUp[numDrawers][drawersToSecure][locked] != -1) {
			return lookUp[numDrawers][drawersToSecure][locked];
		} else {
			long sum = 0;
			if (locked == 1) {
				//Is the drawer above locked?
				sum = countWays(lookUp, locked, numDrawers - 1, drawersToSecure - 1) + countWays(lookUp, 0, numDrawers - 1, drawersToSecure);
			} else {
				//Secure the current drawer and pass down to the next drawer
				sum = countWays(lookUp, locked, numDrawers - 1, drawersToSecure) + countWays(lookUp, 1, numDrawers - 1, drawersToSecure);
			}

			lookUp[numDrawers][drawersToSecure][locked] = sum;
			return sum;
			
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;
		
		int numDrawers;
		int drawersToSecure;
		long[][][] lookUp;

		
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			numDrawers = Integer.parseInt(st.nextToken());
			drawersToSecure = Integer.parseInt(st.nextToken());
		
			if (numDrawers > 0 && drawersToSecure >= 0) {	
				lookUp = new long[numDrawers + 1][drawersToSecure + 1][2];
				for (int i = 0; i < numDrawers + 1; i ++) {
					for (int j = 0; j < drawersToSecure + 1; j ++) {
						for (int k = 0; k < 2; k ++) {
							lookUp[i][j][k] = -1;
						}
					}	
				}
				
					
				sb.append(countWays(lookUp, 1, numDrawers, drawersToSecure) + "\n");
			} else {
				break;
			}
		}

		System.out.print(sb);
	}
}
