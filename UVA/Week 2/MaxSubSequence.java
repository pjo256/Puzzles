/**
 * UVA 787
 * http://vjudge.net/vjudge/contest/view.action?cid=40131#problem/A
 */

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class MaxSubSequence {
	
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
	
		int currNum = 0;
		int sizeSequence;
		int ind;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BigInteger maxProd;
		BigInteger prod;
		int[] sequence = new int[100];
		while ((line = input.readLine()) != null) {
			
			sizeSequence = line.length() - 1;
			st = new StringTokenizer(line);
			ind = 0;
			
			while (st.hasMoreTokens()) {
				currNum = Integer.parseInt(st.nextToken());
				if (currNum != -999999) {
					sequence[ind] = currNum;
				} else {
					break;
				}
			
				ind ++;	
			}

			maxProd = BigInteger.valueOf(sequence[0]);

			for (int i = 0; i < ind; i ++) {
				prod = BigInteger.valueOf(1);
				for (int j = i; j < ind; j ++) {
					prod = prod.multiply(BigInteger.valueOf(sequence[j]));
					maxProd = maxProd.max(prod);
				}
			}
			
			sb.append(maxProd.toString() + "\n");
			
		}
		System.out.print(sb);

		

	}

}
