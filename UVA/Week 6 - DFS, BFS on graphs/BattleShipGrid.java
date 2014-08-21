/**
 * UVA 11953
 * http://vjudge.net/vjudge/contest/view.action?cid=41695#problem/A
 */

import java.io.*;
import java.util.*;

public class BattleShipGrid {

	public static final int INIT = -1;
	public static final int VISITED = 0;
	public static final int FIN = 1;
	public static final int BRIDGE = 2; //Connects a ship, but is damaged
	

	// public static int dfsDriver(int[] state) {
	// 	int numComponents = 0;
	// 	for (int unvisited = 0; unvisited < state.length; unvisited ++) {
	// 		if (state[unvisited] == INIT) {
	// 			//System.out.println(unvisited);
	// 			dfs(unvisited, state);
	// 			numComponents ++;
	// 		}
	// 	}

	// 	return numComponents;
	// }

	// private static void dfs(int vertex, int[] state) {

	// 	if (vertex < 0 || (state[vertex] != INIT && state[vertex] != BRIDGE) ) { 
	// 		return;
	// 	} else {
	// 		state[vertex] = VISITED;
	// 		//System.out.println("Vertex number:" + vertex);
	// 		int dim = (int) Math.sqrt(state.length);
	// 		dfs(left(vertex, dim), state);
	// 		dfs(right(vertex, dim), state);
	// 		dfs(up(vertex, dim), state);
	// 		dfs(down(vertex, dim), state);

	// 		state[vertex] = FIN;
	// 	}
	// }

	public static int dfsDriver(int[][] state) {
		int numComponents = 0;
		for (int i = 0; i < state.length; i ++) {
			for (int j = 0; j < state[0].length; j ++) {
				if (state[i][j] == INIT) {
					dfs(i, j, state);
					numComponents ++;
				}
			}
		}
		return numComponents;
	}

	private static void dfs(int i, int j, int[][] state) {
		boolean outOfBounds = (i < 0 || j < 0 || i >= state.length || j >= state[0].length);
		if (outOfBounds || (state[i][j] != INIT && state[i][j] != BRIDGE)) {
			return;
		} else {
			state[i][j] = VISITED;
			dfs(i + 1, j, state);
			dfs(i - 1, j, state);
			dfs(i, j - 1, state);
			dfs(i, j + 1, state);
			state[i][j] = FIN;
		}
	}
	//Top-left corner of grid is vertex 0.
	public static int left(int vertex, int dim) {

		int lInd = ((vertex - 1 >= 0) && ((vertex - 1) % dim != 0)) ? vertex - 1 : -1;
		//System.out.println("Left of " + vertex + ": " + lInd);
		return lInd;
	}

	public static int right(int vertex, int dim) {
		//System.out.print("dim : " + dim + " ");
		//System.out.print("test " + (vertex + 1 % dim));
		int rInd = ((vertex + 1 < (dim * dim)) && ((vertex + 1) % dim != 0)) ? vertex + 1 : -1;
		//System.out.println("Right of " + vertex + ": " + rInd);
		return rInd;
	}


	public static int up(int vertex, int dim) {
		int uInd = (vertex - dim >= 0) ? vertex - dim : -1;
		return uInd;
	}

	public static int down(int vertex, int dim) {
		int dInd = (vertex + dim < (dim * dim)) ? vertex + dim : -1;
		return dInd;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int numTests = Integer.parseInt(input.readLine());
		int dim;

		int numVertices;
		int lineNum;
		int vertex;
		//HashMap<Integer, Char> vertexToPoint;
		int[][] state;

		char gridToken;

		int testNum = 1;
		int numComponents;

		char[] points;
		int rInd;
		int cInd;

		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			dim = Integer.parseInt(st.nextToken());

			//vertexToPont = new HashMap<Integer, Char>();
			state = new int[dim][dim];

			rInd = 0;
			for (lineNum = 0; lineNum < dim; lineNum ++) {
				points = input.readLine().toCharArray();
				cInd = 0;
				while (cInd < points.length) {
					gridToken = points[cInd];
					//vertexToPoint.put(vertex, gridToken); //Used this to check parsing, was initially using a StringTokenizer but input has no whitespaces
					if (gridToken == 'x') {
						state[rInd][cInd] = INIT;
					} else {
						state[rInd][cInd] = (gridToken == '@') ? BRIDGE : FIN;
					}
					
					cInd ++;
				}
				rInd ++;

			}

			numComponents = dfsDriver(state);
			sb.append("Case " + testNum + ": " + numComponents + "\n");
			testNum ++;
			

		}

		//sb.setLength(sb.length() - 1);
		System.out.print(sb);

	}
}