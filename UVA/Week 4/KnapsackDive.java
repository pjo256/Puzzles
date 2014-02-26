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

		HashMap<Integer, ArrayList<Integer>> bestDives;
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			capacity = Integer.parseInt(st.nextToken());
			diving_const = Integer.parseInt(st.nextToken());

			numTreasures = Integer.parseInt(input.readLine());
			found = 0;
			gold = new int[numTreasures + 1];
			weights = new int[numTreasures + 1];
			bestDives = new HashMap<Integer, ArrayList<Integer>>(numTreasures + 1);
			
			for (int i = 1; i < numTreasures + 1; i ++) {
				bestDives.put(i, new ArrayList<Integer>(2));
			}

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

			bounty = bestDive(lookUp, numTreasures, gold, weights, capacity, bestDives, diving_const);
			
			sb.append(bounty + "\n").append(found + "\n");
			
			for (Map.Entry<Integer, ArrayList<Integer>> entry : bestDives.entrySet()) {
				if (entry.getValue().size() == 2) {
					sb.append(entry.getValue().get(0) + " " + entry.getValue().get(1) + "\n");
				}
			}
		}
		
		System.out.print(sb);

	}

	public static int bestDive(int[][] lookUp, int treasureInd, int[] gold, int[] weights, int capacity, HashMap<Integer, ArrayList<Integer>> output, int diving_const) {
		if (treasureInd == 0 || capacity == 0) {
			return 0;
		}  else if (lookUp[treasureInd][capacity] != -1) {
			return lookUp[treasureInd][capacity];
		} else {
			int maxVal = -1;
			int depth;
			if (capacity < weights[treasureInd]) {
				maxVal = bestDive(lookUp, treasureInd - 1, gold, weights, capacity, output, diving_const);
			} else {
				int dive = gold[treasureInd] + bestDive(lookUp, treasureInd - 1, gold, weights, capacity - weights[treasureInd], output, diving_const);
				int noDive = bestDive(lookUp, treasureInd - 1, gold, weights, capacity, output, diving_const);
			
				if (dive > noDive) {
					depth = weights[treasureInd] / (3 * diving_const);
					if (output.get(treasureInd).size() == 0) {
						ArrayList<Integer> ans = output.get(treasureInd);
						ans.add(depth);
						ans.add(gold[treasureInd]);
						found ++;
						output.put(treasureInd, ans);
					}
					maxVal = dive;
				} else {
					maxVal = noDive;
				} 
			
			}

			
			lookUp[treasureInd][capacity] = maxVal;
			return maxVal;
			
		}
	}
}
