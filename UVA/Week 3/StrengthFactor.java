/**
 * UVA 12032
 * http://vjudge.net/vjudge/contest/view.action?cid=40268#problem/E
 */

import java.io.*;
import java.util.*;

public class StrengthFactor {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int numTests = Integer.parseInt(input.readLine());
		int numRungs;
		int strength;

		int[] ladder;
		int ind;

		int lo;
		int hi;
		int mid;
		int answer;
		int tmp;
	
		int test = 1;
		/*	
		ladder = new int[5];
		ladder[0] = 1;
		ladder[1] = 6;
		ladder[2] = 7;
		ladder[3] = 11;
		ladder[4] = 13;

		System.out.println(victory(ladder, 3));	
		*/
		
		while (test <= numTests) {
			numRungs = Integer.parseInt(input.readLine());
			
			ladder = new int[numRungs];
			ind = 0;
			tmp = numRungs;
			st = new StringTokenizer(input.readLine());
			while (tmp > 0) {
				ladder[ind] = Integer.parseInt(st.nextToken());
				tmp --;
				ind ++;
			}
			
			lo = 0;
			hi = 10000000;
			mid = 0;
			strength = 0;
			while (hi - lo > 0) {
				
				mid = lo + (hi - lo) / 2;
				if(victory(ladder, mid)) {
					//Find lowest strength factor, so we move left by iterating on lo, mid = hi.
					strength = mid;
					hi = mid;
				} else {
					lo = mid + 1;	
				}
			}

			sb.append("Case " + test + ": " + strength + "\n");
			test ++;
		}

		System.out.print(sb.toString());
	
	}

	public static boolean victory(int[] ladder, int k) {
	
		int currPos = 0;
		int distJumped = 0;
		for (int i = 0; i < ladder.length; i ++) {
			distJumped = ladder[i] - currPos;
			if (distJumped == k) {
				k -= 1;
			} else if (distJumped > k) {
				return false;
			}

			currPos = ladder[i];
		}

		return true;
	}

}
