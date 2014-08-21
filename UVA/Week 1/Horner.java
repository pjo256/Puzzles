/**
 * UVA 498
 * http://vjudge.net/vjudge/contest/view.action?cid=39861#problem/B
 */

import java.io.*;
import java.util.*;

public class Horner {
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;

		ArrayList<Integer> coeff = new ArrayList<Integer>();
		ArrayList<Integer> inputs = new ArrayList<Integer>();

		StringBuilder sb = new StringBuilder();
		while( (line = input.readLine()) != null) {
			coeff.clear();
			inputs.clear();
			StringTokenizer st = new StringTokenizer(line);

			while (st.hasMoreTokens()) {
				coeff.add(Integer.parseInt(st.nextToken()));
			}

			line = input.readLine();

			st = new StringTokenizer(line);

			while (st.hasMoreTokens()) {
				inputs.add(Integer.parseInt(st.nextToken()));
			}

			hornerMethod(coeff, inputs);

			for (int ind = 0; ind < inputs.size(); ind ++) {
				sb.append(inputs.get(ind));

				if (ind != inputs.size() - 1) {
					sb.append(" ");
				}
			}

			sb.append("\n");
			
		}

		System.out.print(sb);

	}

	public static void hornerMethod(ArrayList<Integer> coeff, ArrayList<Integer> inputs) {
	
		int num_coeffs = coeff.size();
		int sol;
		int degree; 
		for (int ind = 0; ind < inputs.size(); ind ++) {
			sol = 0;
			degree = 0;
			for (int sum_ind = num_coeffs - 1; sum_ind >= 0; sum_ind --) {
				sol += coeff.get(sum_ind) * Math.pow(inputs.get(ind), degree) ;
				degree ++;	
			}

			inputs.set(ind, sol);
		}



	}

	

}
