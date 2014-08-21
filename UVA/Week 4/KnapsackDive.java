/**
 * UVA 990
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/B
 */

import java.util.*;
import java.io.*;

public class KnapsackDive {

	public static int found;

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		int numTreasures;
		int capacity;
		int diving_const;
		int[] gold;
		int[] weights;
		
		int bounty;
		int depth;

		int[][] lookUp;

		boolean printNewLine = false;
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);

			if (st.countTokens() == 0) {
				continue;
			}

			if (printNewLine) { System.out.println(); } 
			else { printNewLine = true; }
			
			capacity = Integer.parseInt(st.nextToken());
			diving_const = Integer.parseInt(st.nextToken());
			numTreasures = Integer.parseInt(input.readLine());
			found = 0;
			gold = new int[numTreasures + 1];
			weights = new int[numTreasures + 1];
			

			for (int i = 1; i < numTreasures + 1; i ++) {
				st = new StringTokenizer(input.readLine());
				depth = Integer.parseInt(st.nextToken());
				weights[i] = 3 * diving_const * depth;
				gold[i] = Integer.parseInt(st.nextToken());					
			}

			lookUp = new int[numTreasures + 1][capacity + 1];
			for (int i = 0; i < numTreasures + 1; i ++) {
				for (int j = 0; j < capacity + 1; j ++) {
						lookUp[i][j] = -1;
				}
			}

			
			System.out.print(bestDive(lookUp, 1, gold, weights, capacity, diving_const) + "\n");
			sb = new StringBuilder();
			System.out.print(buildOut(lookUp, 1, capacity, gold, weights, diving_const, sb) + "\n");
			System.out.print(sb);
		}
	
		
		
		

	}

	public static int bestDive(int[][] lookUp, int treasureInd, int[] gold, int[] weights, int capacity,  int diving_const) {
		if (treasureInd == gold.length || capacity == 0) {
			return 0;
		}  else if (lookUp[treasureInd][capacity] != -1) {
			return lookUp[treasureInd][capacity];
		} else {
			int maxVal = -1;
			int depth;
			if (capacity < weights[treasureInd]) {
				maxVal = bestDive(lookUp, treasureInd + 1, gold, weights, capacity, diving_const);
			} else {
				int dive = gold[treasureInd] + bestDive(lookUp, treasureInd + 1, gold, weights, capacity - weights[treasureInd], diving_const);
				int noDive = bestDive(lookUp, treasureInd + 1, gold, weights, capacity, diving_const);
			
				if (dive > noDive) {
					depth = weights[treasureInd] / (3 * diving_const);
					maxVal = dive;
				} else {
					maxVal = noDive;
				}
			
			}

			lookUp[treasureInd][capacity] = maxVal;
			return maxVal;
		}
	}

	//Based on Brett's path-recovery suggestion
	public static int buildOut(int[][] lookUp, int treasureInd, int capacity, int[] gold, int[] weights, int diving_const, StringBuilder sb) {
		if (treasureInd == gold.length) {
			return 0;
		}
		int airLeft = capacity - weights[treasureInd];
		if (airLeft >= 0 && (bestDive(lookUp, treasureInd + 1, gold, weights, airLeft, diving_const) + gold[treasureInd] == bestDive(lookUp, treasureInd, gold, weights, capacity, diving_const))) {
			sb.append((weights[treasureInd] / (3 * diving_const))).append(" ").append(gold[treasureInd]).append("\n");
			return buildOut(lookUp, treasureInd + 1, airLeft, gold, weights, diving_const, sb) + 1;
		} else {
			return buildOut(lookUp, treasureInd + 1, capacity, gold, weights, diving_const, sb);
		}

	}
	/*
	public static void postOrderPrint(String[][] decisions, int treasureInd, int capacity, int[] gold, int[] weights, int diving_const, StringBuilder sb) {
		if (treasureInd != 0 && capacity != 0) {
			String decision = decisions[treasureInd][capacity];
			StringTokenizer st = new StringTokenizer(decision);
			int treasure = Integer.parseInt(st.nextToken());
			int cap = Integer.parseInt(st.nextToken());
			postOrderPrint(decisions, treasure, cap, gold, weights, diving_const, sb);
			if (treasure == treasureInd - 1 && cap == capacity - weights[treasureInd]) {
				sb.append(weights[treasureInd] / (3 * diving_const)).append(" ").append(gold[treasureInd]).append("\n");
			} 
		}

	}
	*/
}
