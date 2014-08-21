/**
 * UVA 12118
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/E
 */

import java.io.*;
import java.util.*;

public class MinCut
{

	public static final int OFF = 2;

	private class Edge implements Comparable<Edge> {
        int A, B, w;
        public Edge(int A, int B, int w) {
            this.A = A;
            this.B = B;
            this.w = w;
        }

        public int compareTo(Edge e) {
            if (w != e.w) {
                return w < e.w ? -1 : 1;
            } else {
                return 0;
            }
        }
    }

	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		
	
		int cap;
		int[] edgeCost;
		int[] vertexCost;

		int numMachines;
		int numWires;
		int ind;
		int num;
		int numCatsPerProb;

		int cInd;
		int wInd;

		int start;
		int end;
		int cost;

		ArrayList<Commandos.Edge> edgeList = null;

		while ((line = in.readLine()) != null)
		{
			ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
			st = new StringTokenizer(line);
			numMachines = Integer.parseInt(st.nextToken());
			numWires = Integer.parseInt(st.nextToken());
			
			if (numMachines == 0 && numWires == 0) {
				break;
			}

			edgeCost = new int[numWires];
			vertexCost = new int[numMachines + 1];

			vertexCost[1] = Integer.MAX_VALUE;
			vertexCost[numMachines] = Integer.MAX_VALUE;

			ind = 0;
			st = new StringTokenizer(in.readLine());
			while (ind < numMachines - 2) {
				cInd = Integer.parseInt(st.nextToken());
				cap = Integer.parseInt(st.nextToken());
				vertexCost[cInd] = cap;
				ind ++;
			}

			ind = 0;
			while (ind < numWires) {
                st = new StringTokenizer(input.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());
                e = inst.new Edge(start, end, cost);
                edgeList.add(e);
                ind ++;
            }
            process(edgeList, vertexCost, numWires);
		}
		
	}
	static void addEdge(int u, int v, int cap, int[][] caps, ArrayList<Integer>[] adj)
	{
		try {
			if (caps[u][v] == 0 && caps[v][u] == 0)
			{
				adj[u].add(v); adj[v].add(u);
			} 
			caps[u][v] += cap;
		} catch (Exception e) {
			System.out.println("V size = " + caps.length);
		}
		
	}
	static void process(ArrayList<MinCut.Edge> input, int[] vertexCost, int numEdges)
	{
		int C = 2 * vertexCost.length;
		//System.out.println("Num problems = " + numProblems);
		int s = 1, t = C, sC = 2, V = C;
		ArrayList<Integer>[] adj = new ArrayList[V];
		for (int i = 0; i < V; ++i) adj[i] = new ArrayList<Integer>();
		int[][] caps = new int[V][V];

		int problemsPerCat = 0;
		int prob = sP;
		int cat;

		

		int start;
		int copiedStart;
		int end;
		int copiedEnd;

		for (MinCut.Edge e : input)
		{
			start = e.A * 2;
			copiedStart = start + 1;
			end = e.B * 2;
			copiedEnd = end + 1;
			if (e.A != s && e.B != t) {
				addEdge(start, copiedStart, vertexCost[e.A], caps, adj);
				addEdge(copiedStart, end, e.w, caps, adj);
				addEdge(end, copiedEnd, vertexCost[e.B], caps, adj);
				addEdge(copiedEnd, start, e.w, caps, adj);
			} else if (e.A == s && e.B != t) {
				addEdge(start, end, e.w, caps, adj);
				addEdge(end, copiedEnd, vertexCost[e.B], caps, adj);
				addEdge(copiedEnd, start, e.w, caps, adj);
			} else if (e.A != s && e.B == t) {
				addEdge(start, copiedStart, vertexCost[e.A], caps, adj);
				addEdge(copiedStart, end, e.w, caps, adj);
				addEdge(end, start, e.w, caps, adj);
			}
		}

		int flow = maxflow(adj,caps,s,t);
		if (flow < problemsPerCat) System.out.print(0 + "\n");
		else
		{
			System.out.println(flow);
		}
	}
	static int maxflow(ArrayList<Integer>[] adj, int[][] caps, int source, int sink)
	{
		int ret = 0;
		while (true)
		{
			int f = augment(adj,caps,source,sink);
			if (f == 0) break;
			ret += f;
		}
		return ret;
	}
	static int augment(ArrayList<Integer>[] adj, int[][] caps, int source, int sink)
	{
		Queue<Integer> q = new ArrayDeque<Integer>();
		int[] pred = new int[adj.length];
		Arrays.fill(pred,-1);
		int[] f = new int[adj.length];
		pred[source] = source; f[source] = Integer.MAX_VALUE; q.add(source);
		while (!q.isEmpty())
		{
			int curr = q.poll(), currf = f[curr];
			if (curr == sink)
			{
				update(caps,pred,curr,f[curr]);
				return f[curr];
			}
			for (int i = 0; i < adj[curr].size(); ++i)
			{
				int j = adj[curr].get(i);
				if (pred[j] != -1 || caps[curr][j] == 0) continue;
				pred[j] = curr; f[j] = Math.min(currf, caps[curr][j]); q.add(j);
			}
		}
		return 0;
	}
	static void update(int[][] caps, int[] pred, int curr, int f)
	{
		int p = pred[curr];
		if (p == curr) return;
		caps[p][curr] -= f;  caps[curr][p] += f;
		update(caps,pred,p,f);
	}
}