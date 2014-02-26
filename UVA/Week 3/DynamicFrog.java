import java.io.*;
import java.util.*;

public class DynamicFrog {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
	
		int numTests = Integer.parseInt(input.readLine());
		int[] rocks;

		int numRocks;
		int width;
		int ind;
		int maxLeap;
	
		String rock;
		String[] val;
		int test = 1;
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			maxLeap = 0;
			numRocks = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
	
			line = input.readLine();
			st = new StringTokenizer(line);

			sb.append("Case " + test + ": ");
			rocks = new int[2 + 2 * numRocks];
			rocks[0] = 0;
			
			ind = 1;
			while (st.hasMoreTokens()) {
				rock = st.nextToken();
				val = rock.split("-");
				if (val[0].equals("B")) {
					rocks[ind] = Integer.parseInt(val[1]);
					rocks[ind + 1] = Integer.parseInt(val[1]);
					ind += 2;
				} else {
					rocks[ind] = Integer.parseInt(val[1]);
					ind ++;
				}
			}

			rocks[ind] = width;
	
			for (int i = 2; i <= ind; i ++) {
				maxLeap = Math.max(maxLeap, (rocks[i] - rocks[i - 2]));
			}

			sb.append(maxLeap + "\n");
			test ++;				
		}
		
		System.out.print(sb);
	}
}
