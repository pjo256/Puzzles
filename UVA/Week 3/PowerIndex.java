import java.io.*;
import java.util.*;

public class PowerIndex {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int totalVotes;
		int currSum;
		int ind;
		HashSet<ArrayList<Integer>> answers;
		ArrayList<Integer> partiesUsed;

		int numParties;
		int requiredVotes;
		int vote;
		int numTests = Integer.parseInt(input.readLine());
		while((line = input.readLine()) != null) {
			st = new StringTokenizer(line);

			numParties = Integer.parseInt(st.nextToken());
			answers = new HashSet<ArrayList<Integer>>();
			ArrayList<Integer> votes = new ArrayList<Integer>();
			totalVotes = 0;

			while (numParties > 0 && st.hasMoreTokens()) {
				vote = Integer.parseInt(st.nextToken());
				totalVotes += vote;
				votes.add(vote);
				numParties --;
			}		
			
			requiredVotes = (totalVotes / 2);
			System.out.println(totalVotes);
			for (int mask = 0; mask < (1 << votes.size()); mask ++) {
				currSum = 0;
				partiesUsed = new ArrayList<Integer>();
				for (int i = 0; i < votes.size(); i ++) {
					int currBit = 1 << i;
					if ( (mask & currBit) != 0) {
						currSum += votes.get(i);
						partiesUsed.add(i + 1);
					}
				}

				if (currSum <= requiredVotes) {
					answers.add(partiesUsed);
				}
			}

			ind = 1;
			HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();
			for (ArrayList<Integer> coalition : answers) {
				for (int i = 0; i < coalition.size(); i ++) {
					if (results.containsKey(coalition.get(i))) {
						results.put(coalition.get(i), results.get(coalition.get(i)) + 1);
					} else {
						results.put(coalition.get(i), 1);
					}
				}
			}

			for (Map.Entry<Integer, Integer> kvp : results.entrySet()) {
				sb.append("party " + kvp.getKey() + " has power index " + kvp.getValue() + "\n");
			}

		}
		
		System.out.print(sb);
	}

}
