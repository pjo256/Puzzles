import java.io.*;
import java.util.*;

public class PowerIndex2 {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int totalVotes;
		int currSum;
		int ind;
		int numParties;
		int requiredVotes;
		int vote;
		int numTests = Integer.parseInt(input.readLine());
		int temp;	
		boolean[] currentCoalition;
		int[] votes = new int[20];
		int[] powerIndex;
		while(numTests > 0) {
			st = new StringTokenizer(input.readLine());

			numParties = Integer.parseInt(st.nextToken());
			totalVotes = 0;
			ind = 0;
			//votes = new int[numParties];
			powerIndex = new int[numParties];
			temp = numParties;
			while (temp > 0) {
				vote = Integer.parseInt(st.nextToken());
				totalVotes += vote;
				votes[ind] = vote;
				ind ++;
				temp --;
			}		

			
	
			requiredVotes = (totalVotes / 2);
			
			for (int group = 0; group < (1 << numParties); group += 1) {
				//Form the current group of parties
				currentCoalition = new boolean[numParties];
				currSum = 0;
				for (int i = 0; i < numParties; i ++) {
					int currBit = 1 << i;
					if ( (group & currBit) != 0) {
						currentCoalition[i] = true;
						currSum += votes[i];
					}
				}
				
				//Check if any party not in the current coalition can 
				//contribute to give majority vote
				for (int i = 0; i < numParties; i ++) {
					if (!currentCoalition[i] && currSum <= requiredVotes && currSum + votes[i] > requiredVotes) {
						powerIndex[i] += 1;
					}
				}
			}
		
			for (int party = 0; party < numParties; party ++) {	
				if (powerIndex[party] > 0) {
                                	sb.append("party " + (party + 1) + " has power index " + powerIndex[party] + "\n");
                        	}
			}
			
			sb.append("\n");	
			numTests --;
		}
		
		System.out.print(sb.toString());
	}

}

