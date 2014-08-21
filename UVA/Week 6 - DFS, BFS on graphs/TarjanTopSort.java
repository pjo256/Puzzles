/**
 * UVA 10305
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/B
 */

import java.io.*;
import java.util.*;

public class TarjanTopSort {

	public static final int INIT = -1;
	public static final int VISITED = 0;
	public static final int FIN = 1;

	//Tarjan's DFS reverse topological sort, based on class notes
	public static void dfs(ArrayList<Integer>[] adjList, int[] state, int v, ArrayList<Integer> reverseTop, int numVertices) {
		if (state[v] != INIT) {
			return;
		} else {
			state[v] = VISITED;

			for (int u = 0; u < adjList[v].size(); u ++) {
				int neighbor = adjList[v].get(u);
				dfs(adjList, state, neighbor, reverseTop, numVertices);
			}
			state[v] = FIN;
			reverseTop.add(v);
		}
	}

	public static ArrayList<Integer> dfsDriver(ArrayList<Integer>[] adjList, int numVertices) {
		ArrayList<Integer> topSorted = new ArrayList<Integer>();
		int[] state = new int[adjList.length];
		Arrays.fill(state, INIT);
	
		for (int v = 0; v <= numVertices; v ++) {
			if (adjList[v] != null && state[v] == INIT) {
				dfs(adjList, state, v, topSorted, numVertices);
			}
		}

		Collections.reverse(topSorted);
		return topSorted;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int numVertices;
		int numEdges;

		int vertex;
		int neighbor;
		int sz;

		ArrayList<Integer>[] adjList;
		ArrayList<Integer> topSorted;
		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			numVertices = Integer.parseInt(st.nextToken());
			numEdges = Integer.parseInt(st.nextToken());

			if (numVertices == 0 && numEdges == 0) {
				continue;
			}

			adjList = new ArrayList[110];
			
			for (int i = 1; i <= numVertices; i ++) {
				adjList[i] = new ArrayList<Integer>(2);
			}

			
			while(numEdges > 0) {
				st = new StringTokenizer(input.readLine());
				vertex = Integer.parseInt(st.nextToken());
				neighbor = Integer.parseInt(st.nextToken());

				adjList[vertex].add(neighbor);
				numEdges --;
			}

			topSorted = dfsDriver(adjList, numVertices);
			sz = topSorted.size();
			for (int i = 0; i < sz; i ++) {
				sb.append(topSorted.get(i));
				if (i < sz - 1) {
					sb.append(" ");
				}
			}
			sb.append("\n");

		}

		sb.setLength(sb.length() - 1);
		System.out.print(sb.toString());


	}

}
