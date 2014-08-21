/**
 * UVA 11957
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/F
 */
import java.io.*;
import java.util.*;

public class Checkers {

	public static final int INIT = -1;
	public static final int FREE = 0;
	public static final int BLOCKED = 1;
	public static final int BIG = 1000007;

	private static boolean legalMove(int i, int j, int dim) {
		return (i < dim && i >= 0) && (j < dim && j >= 0);
	}
	
	public static long pathsDP(int[][] state, long[][] pathsToKing, int i, int j, int dim) {
		if (!legalMove(i, j, dim) || state[i][j] == BLOCKED) {
			return 0;
		} else if (pathsToKing[i][j] != INIT) {
			return pathsToKing[i][j];
		} else {
			long countWays = 0;
			if (legalMove(i - 1, j + 1, dim)) {
				if (state[i - 1][j + 1] == BLOCKED) {
					countWays += (pathsDP(state, pathsToKing, i - 2, j + 2, dim) % BIG);
				} else {
					countWays += (pathsDP(state, pathsToKing, i - 1, j + 1, dim) % BIG);
				}
			}

			if (legalMove(i - 1, j - 1, dim)) {
				if (state[i - 1][j - 1] == BLOCKED) {
					countWays += (pathsDP(state, pathsToKing, i - 2, j - 2, dim) % BIG);
				} else {
					countWays += (pathsDP(state, pathsToKing, i - 1, j - 1, dim) % BIG);
				}
			}

			pathsToKing[i][j] = (countWays % BIG);
			return pathsToKing[i][j];
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int numTests = Integer.parseInt(input.readLine());
		int dim;

		int lineNum;
		long[][] pathsToKing;
		int[][] state;

		char gridToken;

		int caseNum = 1;
		char[] points;
		int rInd;
		int cInd;

		int wRInd;
		int wCInd;
		long numPaths;

		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			dim = Integer.parseInt(st.nextToken());

			wRInd = wCInd = 0;

			pathsToKing = new long[dim][dim];
			state = new int[dim][dim];

			rInd = 0;
			for (lineNum = 0; lineNum < dim; lineNum ++) {
				points = input.readLine().toCharArray();
				cInd = 0;
				while (cInd < points.length) {
					gridToken = points[cInd];
					//vertexToPoint.put(vertex, gridToken); //Used this to check parsing, was initially using a StringTokenizer but input has no whitespaces
					if (gridToken == 'B') {
						state[rInd][cInd] = BLOCKED;
					} else if (gridToken == 'W') {
						wRInd = rInd;
						wCInd = cInd;
					} else {
						state[rInd][cInd] = FREE;
					}
					
					cInd ++;
				}
				rInd ++;

			}

			for (cInd = 0; cInd < dim; cInd ++) {
				if (state[0][cInd] == BLOCKED) {
					pathsToKing[0][cInd] = 0;
				} else {
					pathsToKing[0][cInd] = 1;
				}
			}

			for (rInd = 1; rInd < dim; rInd ++) {
				Arrays.fill(pathsToKing[rInd], INIT);
			}

			numPaths = pathsDP(state, pathsToKing, wRInd, wCInd, dim) % BIG;
			sb.append("Case " + caseNum + ": " + numPaths + "\n");
			caseNum ++;
			

		}

		System.out.print(sb);

	}
}