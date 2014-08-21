/**
 * UVA 11137
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/A
 */

import java.io.*;
import java.util.*;

public class Cubrency {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;

		int targetAmount;
		long ans;
		long[][] lookUp = new long[10000 + 1][21 + 1];

		for (int i = 0; i < 10001; i ++) {
			for (int j = 0; j < 22; j ++) {
				lookUp[i][j] = -1;
			}
		}

		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			
			while (st.hasMoreTokens()) {
				targetAmount = Integer.parseInt(st.nextToken());
				ans = solveWays(lookUp, targetAmount, 21);
				sb.append(ans + "\n");
			}
		}

		System.out.print(sb);
	}

	public static long solveWays(long[][] lookUp, int amountRem, int lastCoin) {
		if (amountRem == 0 || lastCoin == 1) {
			return 1;
		} else if (amountRem < 0) {
			return 0;
		} else if (lookUp[amountRem][lastCoin] != -1) {
			return lookUp[amountRem][lastCoin];
		} else {
			long currSum = 0;
			//for (int i = 2; i <= lastCoin; i ++) {
			currSum = solveWays(lookUp, amountRem, lastCoin - 1) + solveWays(lookUp, (int)(amountRem - Math.pow(lastCoin, 3)), lastCoin); 
			//}
			//System.out.println(currSum + "last" + lastCoin);

			lookUp[amountRem][lastCoin] = currSum;
			//System.out.println("lookUp : amount " + amountRem + " : lastCoin : " + lastCoin + " " + lookUp[amountRem][lastCoin]);
			return currSum;
		}
	}
}
