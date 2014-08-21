/**
 * UVA 11340
 * http://vjudge.net/vjudge/contest/view.action?cid=39861#problem/C
 */

import java.lang.*;
import java.io.*;
import java.util.*;

public class NewsWorthy {
	
	

	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		int numTests = Integer.parseInt(input.readLine());
		
		int numArticleLines;
		int numValuedChars;
		HashMap<Character, Integer> charToWorth;	
		

		char valuedChar;
		int value;

		String formattedCents;
		int sumInCents;
		int sumInDollars;

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int test_case = 0; test_case < numTests; test_case++) {
			
			sumInCents = 0;
			numValuedChars = Integer.parseInt(input.readLine());
			charToWorth = new HashMap<Character, Integer>(numValuedChars);

			while(numValuedChars > 0 && (line = input.readLine()) != null) {	
				
				
				st = new StringTokenizer(line);

				valuedChar = st.nextToken().charAt(0);
				value = Integer.parseInt(st.nextToken());

				if (!charToWorth.containsKey(valuedChar)) {
					charToWorth.put(valuedChar, value);
				}

				numValuedChars --;

			}

			numArticleLines = Integer.parseInt(input.readLine());
			while (numArticleLines > 0 && (line = input.readLine()) != null) {
				
				sumInCents +=  valueOf(line, charToWorth);
				numArticleLines --;
			}

			

			sumInDollars = sumInCents / 100;
			sumInCents = sumInCents % 100;
			
			sb.append(sumInDollars);
			
					
			formattedCents = "";
			if (sumInCents < 10) {
				formattedCents = "0";
			}

			formattedCents += String.valueOf(sumInCents);
			
			sb.append("." + formattedCents + "$");
			sb.append("\n");
			
		}

		System.out.print(sb);

	}

	public static int valueOf(String line, HashMap<Character, Integer> charToWorth) {
		
		int lineSumCents = 0;
		StringTokenizer st = new StringTokenizer(line);

		while (st.hasMoreTokens()) {

			for (char c : st.nextToken().toCharArray()) {
				if (charToWorth.containsKey(c)) {
					lineSumCents += charToWorth.get(c);

				}

			}
		}

		return lineSumCents;
	}
}

