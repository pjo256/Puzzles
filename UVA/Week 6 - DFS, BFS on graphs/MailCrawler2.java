import java.io.*;
import java.util.*;

public class Main {

	public static final int INIT = -1;
	public static final int VISITED = 0;
	public static final int IN_CYCLE = -2;
	public static final int SCC_PROCESSED = -3;

	public static int cycStart = -1;
	public static int cycleLen = 0;
	public static boolean cycleFound;

	public static int preOrderNumb = 0;
	public static Stack<Integer> sCC;


	public static void tarjanSCCDriver(int[] sCC_parent, int[] adjList, int[] reachableFrom) {
		int[] preOrdering = new int[adjList.length];
		int[] low = new int[adjList.length];
		boolean[] sCC_state = new boolean[adjList.length];
		Arrays.fill(preOrdering, INIT);
		Arrays.fill(low, 0);
		Arrays.fill(sCC_state, false);
		for (int i = 1; i < preOrdering.length; i ++) {
			if (preOrdering[i] == INIT) {
				tarjanSCC(adjList, reachableFrom, preOrdering, low, sCC_state, sCC_parent, i);
			}
		}

		for (int i = 1; i < preOrdering.length; i ++) {
			if (sCC_parent[i] != -1) {
				reachableFrom[i] = reachableFrom[sCC_parent[i]];
			}
		}
	}

	private static void tarjanSCC(int[] adjList, int [] reachableFrom, int[] preOrdering, int[] low, boolean[] sCC_state, int[] sCC_parent, int v) {
		preOrdering[v] = preOrderNumb;
		low[v] = ++preOrderNumb;

		sCC.push(v);
		sCC_state[v] = true;
		int neighbor = adjList[v];
		if (preOrdering[neighbor] == INIT) {
			tarjanSCC(adjList, reachableFrom, preOrdering, low, sCC_state, sCC_parent, neighbor);
			low[v] = Math.min(low[v], low[neighbor]);
		} else if (sCC_state[neighbor]) {
			low[v] = Math.min(low[v], preOrdering[neighbor]);
		}

		if (low[v] == preOrdering[v]) {
			//new strong component
			cycleLen = 0;
			int x;
			while (true) {
				x = sCC.pop();
				sCC_state[x] = true;
				sCC_parent[x] = v;
				cycleLen ++;
				if (x == v) {
					break;
				}

			} 

			reachableFrom[v] = cycleLen;
		}

	}


	private static int dfs(int[] adjList, int[] state, int[] reachableFrom, int v) {
		if (state[v] != INIT) {
			return 0;
		} else {
				state[v] = VISITED;
				int ret = 0;
				int neighbor = adjList[v];
				
				if (state[neighbor] == INIT && reachableFrom[neighbor] == -1 && adjList[neighbor] != -1) {
					reachableFrom[neighbor] = dfs(adjList, state, reachableFrom, neighbor);
				} 

				ret += reachableFrom[neighbor] + 1;
			
				reachableFrom[v] = ret;
				state[v] = INIT;
				return ret;	
		}

		
	}

	public static int dfsDriver(int[] adjList, int[] reachableFrom, int numVertices) {
		int[] state = new int[adjList.length];
		int[] sCC_parent = new int[adjList.length];

	
		int maxVertex = 0;
		int maxReach = Integer.MIN_VALUE;
		int vReach;
		Arrays.fill(state, INIT);
		Arrays.fill(sCC_parent, -1);


		//Cache all cycle lengths first
		tarjanSCCDriver(sCC_parent, adjList, reachableFrom);
		

		//Run a cached DFS checking the number of vertices reachable from any vertex v. 
		//We want the vertex with maximum reachability.
		for (int v = 1; v <= numVertices; v ++) {
			vReach = 0;
			if (reachableFrom[v] == -1 && adjList[v] != -1) {
				vReach = dfs(adjList, state, reachableFrom, v);
			} 

			if (sCC_parent[v] != -1) {
				vReach = reachableFrom[v];
			}
			
			reachableFrom[v] = Math.max(reachableFrom[v], vReach);
			if (maxReach < reachableFrom[v]) {
				maxReach = reachableFrom[v];
				maxVertex = v;
			} else if (maxReach == reachableFrom[v]) {
				maxVertex = Math.min(maxVertex, v);
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

		int[] adjList;
		int[] reachableFrom;

		int sender;
		int recepient;
		int caseNum = 1;
		int bestSender;
		//boolean first = true;
		while ((line = input.readLine()) != null) {
			//if (!first) {
			//	sb.append("\n");
			//}
			sCC = new Stack<Integer>();
			//first = false;
			numVertices = Integer.parseInt(line);
			ind = 0;
			preOrderNumb = 0;
			adjList = new int[numVertices + 1];
			reachableFrom = new int[numVertices + 1];
			Arrays.fill(adjList, -1);
			Arrays.fill(reachableFrom, -1);
			

			while (ind < numVertices) {
				st = new StringTokenizer(input.readLine());
				sender = Integer.parseInt(st.nextToken());
				recepient = Integer.parseInt(st.nextToken());
				adjList[sender] = recepient;
				ind ++;
			}

			bestSender = dfsDriver(adjList, reachableFrom, numVertices);
			sb.append("Case " + caseNum + ": " + bestSender);
			sb.append("\n");
			
			caseNum ++;
		}

		System.out.print(sb);

	}
}