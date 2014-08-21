/**
 * UVA 12442
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/E
 */

import java.io.*;
import java.util.*;

public class MailCrawler {

	public static final int INIT = -1;
	public static final int VISITED = 0;
	public static final int IN_CYCLE = -2;

	public static int cycStart;
	public static int cycleLen = 0;
	public static boolean cycleFound;

	private static void dfsCycle(ArrayList<Integer>[] adjList, int[] state, int[] reachableFrom, int v) {
		if (state[v] != INIT) {
			return;
		} else {
			state[v] = VISITED;
			for (int u = 0; u < adjList[v].size(); u ++) {
				int neighbor = adjList[v].get(u);

				if (state[neighbor] == INIT && reachableFrom[neighbor] == 0 && !cycleFound) {
					dfsCycle(adjList, state, reachableFrom, neighbor);
				} else if (state[neighbor] == VISITED) {
					cycleFound = true;
					cycStart = neighbor;
				}
			}

			if (cycleFound) {
				if (v != cycStart) {
					cycleLen ++;
					reachableFrom[v] = IN_CYCLE;
				} else {
					reachableFrom[v] = cycleLen;
				}
			}


			// state[v] = VISITED;
			// System.out.println("Starting DFS from " + v);
			// //for (int u = 0; u < adjList[v].size(); u ++) {
			// int neighbor = adjList[v].get(0);
			// int currNode = neighbor;
			// if (state[neighbor] == INIT && reachableFrom[neighbor] == 0) {
			// 		while (currNode != v) {
			// 			currNode = adjList[currNode].get(0);
			// 			if (state[currNode] == VISITED) {
			// 				System.out.println("Found cycle");
			// 				cycleFound = true;
			// 				cycStart = currNode;
			// 			}
			// 			cycleLen ++;
			// 		}
			// }
				
			// //}

			// // if (cycleFound) {
			// // 	if (v != cycStart) {
			// // 		cycleLen ++;
			// // 	} else {
			// // 		reachableFrom[v] = cycleLen;
			// // 		cycleLen = 0;
			// // 		cycleFound = false;
			// // 	}
			// // }

			// // if (cycleFound) {
			// // 	reachableFrom[v] = cycleLen;
				
			// // 	while ()
			// // } else {
			// // 	cycleLen = 0;
			// // }




		}

	}
	private static int dfs(ArrayList<Integer>[] adjList, int[] state, int[] reachableFrom, int v) {
		if (state[v] != INIT) {
			return -1;
		} else {
			state[v] = VISITED;
			int ret = 0;
			for (int u = 0; u < adjList[v].size(); u ++) {
				int neighbor = adjList[v].get(u);
				
				if (reachableFrom[neighbor] == 0) {
					reachableFrom[neighbor] = dfs(adjList, state, reachableFrom, neighbor);
				} 

				//If neighbor was already visited, reachableFrom[v] is not incremented.
				ret += reachableFrom[neighbor] + 1;
			}	
			//System.out.println("REACHABLE " + reachableFrom[v]);
			state[v] = INIT;
			return ret;
		}
	}

	public static int dfsDriver(ArrayList<Integer>[] adjList, int[] reachableFrom, int numVertices) {
		int[] state = new int[adjList.length];
	
		int maxVertex = 0;
		int maxReach = Integer.MIN_VALUE;
		int vReach;
		Arrays.fill(state, INIT);
		Arrays.fill(reachableFrom, 0);
		

		//Cache all cycle lengths first
		for (int v = 1; v <= numVertices; v ++) {
			if (state[v] == INIT && reachableFrom[v] == 0 && !cycleFound) {
				dfsCycle(adjList, state, reachableFrom, v);
			} 
		}

		for (int v = 1; v <= numVertices; v ++) {
			if (reachableFrom[v] == IN_CYCLE) {
				reachableFrom[v] = cycleLen;
			}
		}

		//Run a cached DFS checking the number of vertices reachable from any vertex v. 
		//We want the vertex with maximum reachability.
		for (int v = 1; v <= numVertices; v ++) {
			vReach = 0;
			if (reachableFrom[v] == 0) {
				vReach = dfs(adjList, state, reachableFrom, v);
			} 

			reachableFrom[v] = Math.max(reachableFrom[v], vReach);
			if (maxReach < reachableFrom[v]) {
				maxReach = reachableFrom[v];
				maxVertex = v;
			}
		}

		return maxVertex;
	}


	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int numTests = Integer.parseInt(input.readLine());
		int numVertices;

		int ind;

		ArrayList<Integer>[] adjList;
		int[] reachableFrom;

		int sender = 0;
		int recepient = 0;
		int caseNum = 1;
		int bestSender;
		long startTime = System.currentTimeMillis();
		while ((line = input.readLine()) != null) {
			numVertices = Integer.parseInt(line);
			ind = 0;

			adjList = new ArrayList[numVertices + 1];
			reachableFrom = new int[numVertices + 1];
			
			for (int i = 1; i <= numVertices; i ++) {
				adjList[i] = new ArrayList<Integer>(2);
			}

			while (ind < numVertices) {
				st = new StringTokenizer(input.readLine());
				sender = Integer.parseInt(st.nextToken());
				recepient = Integer.parseInt(st.nextToken());
				
				adjList[sender].add(recepient);
				ind ++;


			}

			bestSender = dfsDriver(adjList, reachableFrom, numVertices);
			sb.append("Case " + caseNum + ": " + bestSender + "\n");
			caseNum ++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);

		System.out.print(sb.toString());

	}
}