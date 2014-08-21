/**
 * UVA 100
 * http://vjudge.net/vjudge/contest/view.action?cid=39861#problem/A
 */

import java.io.*;
import java.util.*;
import java.lang.Math;

public class CycleLen {

	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;

		ArrayList<Integer> io = new ArrayList<Integer>();

		StringBuilder sb = new StringBuilder();
		while( (line = input.readLine()) != null) {
			io.clear();
			StringTokenizer st = new StringTokenizer(line);

			while (st.hasMoreTokens()) {
				io.add(Integer.parseInt(st.nextToken()));
			}

			findMaxCycle(io);

			for (int ind = 0; ind < io.size(); ind ++) {
				sb.append(io.get(ind));

				if (ind != io.size() - 1) {
					sb.append(" ");
				}
			}

			sb.append("\n");
			
		}

		System.out.print(sb);

	}

	public static void findMaxCycle(ArrayList<Integer> io) {
		
		int min_range = Math.min(io.get(0), io.get(1));
		int max_range = Math.max(io.get(1), io.get(0));
		
		int max_len = Integer.MIN_VALUE;
		int cyc_len;
		int n;
		for (int num = min_range; num < max_range + 1; num ++) {
			
			n = num;
			cyc_len = 1;
			while(n != 1) {
				if (n % 2 != 0) {
					n = 3 * n + 1;
				} else {
					n /=  2;
				}

				cyc_len ++;
			}

			if (cyc_len > max_len) {
				max_len = cyc_len;
			}
			
		}

		io.add(max_len);
	}

}
