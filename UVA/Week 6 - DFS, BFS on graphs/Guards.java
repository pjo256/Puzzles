/**
 * UVA 11080
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/D
 */

import java.io.*;
import java.util.*;

public class Guards {

	public static final int INIT = -1;
	public static final int VISITED = 0;
	public static final int FIN = 1;

	public static final int GUARDED = 0;
	public static final int VACANT = 1;

	private static boolean neighborDistinct(ArrayList<Integer>[] adjList, int[] color, int v, int label) {
		for (int u = 0; u < adjList[v].size(); u ++) {
			if (color[adjList[v].get(u)] == label) {
				return false;
			}
		}

		return true;
	}
	

	public static int bfs(ArrayList<Integer>[] adjList, int[] color, int[] cCnt, int[] state, int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		int numJunctions = 0;
		cCnt[GUARDED] = 0;
		cCnt[VACANT] = 0;

		int nextColor;
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			numJunctions ++;

			if (state[vertex] == INIT) { 
				color[vertex] = GUARDED; 
				cCnt[GUARDED] += 1;
				nextColor = VACANT;
			} else {
				nextColor = (color[vertex] == VACANT) ? GUARDED : VACANT;
			}

			for (int i = 0; i < adjList[vertex].size(); i ++) {
				int child = adjList[vertex].get(i);
				if (state[child] == INIT) {
					queue.add(child);
					color[child] = nextColor;
					cCnt[nextColor] += 1;

					state[child] = VISITED;
				}
			}

			state[vertex] = FIN;
		}

		return (numJunctions == 1) ? 1 : Math.min(cCnt[GUARDED], cCnt[VACANT]);
	}

	public static int bfsDriver(ArrayList<Integer>[] adjList, int[] color) {
		int[] state = new int[adjList.length];
		int[] cCnt = new int[2];
		Arrays.fill(state, INIT);

		int numGuards = 0;
		for (int u = 0; u < state.length; u ++) {
			if (state[u] == INIT) {
				numGuards += bfs(adjList, color, cCnt, state, u);
			}
		}


		for (int u = 0; u < state.length; u ++) {
			if ((color[u] == GUARDED && !neighborDistinct(adjList, color, u, GUARDED)) 
					|| (color[u] == VACANT && !neighborDistinct(adjList, color, u, VACANT))) {
				
				numGuards = 0;
				break;
			}
		}

		return (numGuards == 0) ? -1 : numGuards;
	}

	

	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int numVertices;
		int numEdges;

		int junction;
		int neighbor;
		int sz;

		ArrayList<Integer>[] adjList;
		int[] color;

		int numTests = Integer.parseInt(input.readLine());
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			numVertices = Integer.parseInt(st.nextToken());
			numEdges = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[numVertices];
			color = new int[numVertices];
			
			for (int i = 0; i < numVertices; i ++) {
				adjList[i] = new ArrayList<Integer>(2);
				color[i] = -1;
			}

			
			while(numEdges > 0) {
				st = new StringTokenizer(input.readLine());
				junction = Integer.parseInt(st.nextToken());
				neighbor = Integer.parseInt(st.nextToken());


				if (neighbor != junction) {
					//Components with more than one junction
					adjList[junction].add(neighbor);
					adjList[neighbor].add(junction);
				}
				numEdges --;
			}

			
			sb.append(bfsDriver(adjList, color) + "\n");

			

		}

		System.out.print(sb);

	}
}