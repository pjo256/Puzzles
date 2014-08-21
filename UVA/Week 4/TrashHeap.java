/**
 * UVA 10755
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/C
 */

import java.io.*;
import java.util.*;

public class TrashHeap {
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;

		//long[][][] heap;
		long[][][] sums;
		int value; 

		int height;
		int length;
		int width;
		int numTests = Integer.parseInt(input.readLine());

		long val;
		long currSum;
		long maxSum;
	
		int tokensRem;	
		int numsLeft;
		while ((line = input.readLine()) != null) {
			
			
			st = new StringTokenizer(line);
			if (st.countTokens() == 0) {
				continue;
			}
			//Add zero-padding because we don't consider the (i, 0, k), (0, j, k), (i, j, 0) layers of the cube.
			height = 0;
			length = 0;
			width = 0;
			height += 1 + Integer.parseInt(st.nextToken());
			length += 1 + Integer.parseInt(st.nextToken());
			width += 1 + Integer.parseInt(st.nextToken());

			line = input.readLine();
			st = new StringTokenizer(line);
			
			//heap = new long[height][length][width];
			sums = new long[height][length][width];
			tokensRem = st.countTokens();
			numsLeft = (height - 1) * (length - 1) * (width - 1);
			for (int i = 1; i < height; i ++) {
				for (int j = 1; j < length; j ++) {
					for (int k = 1; k < width; k ++) {
						if (numsLeft > 0 && tokensRem <= 0) {
							st = new StringTokenizer(input.readLine());
							tokensRem = st.countTokens();
						}

						if (tokensRem > 0) {
							val = Long.parseLong(st.nextToken());
			//				heap[i][j][k] = val;
							sums[i][j][k] = val + (sums[i][j - 1][k] + sums[i][j][k - 1]) - sums[i][j - 1][k - 1];
							tokensRem--;
							numsLeft --;
						}
					}
				}
			}

			maxSum = Integer.MIN_VALUE;
			for (int i = 1; i < length; i ++) {
				for (int j = 1; j < width; j ++) {
					for (int k = i; k < length; k ++) {
						for (int l = j; l < width; l ++) {
							//Top-left corner is fixed to (i, j), which is stretched by k and l to the bottom-right corner (k, l)							
							currSum = 0;		
							for (int h = 1; h < height; h ++) {;
								currSum += sums[h][k][l] - sums[h][i - 1][l] - sums[h][k][j - 1] + sums[h][i - 1][j - 1];
								maxSum = Math.max(maxSum, currSum);
								currSum = Math.max(0, currSum);
							}
						}
					}
				}
			}
		

			numTests --;	
			sb.append(maxSum + "\n");
			if (numTests > 0) { sb.append("\n"); }
		}
	
		System.out.print(sb);

	}

}
