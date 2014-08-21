/**
 * UVA 10449
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/C
 */

import java.io.*;
import java.util.*;

public class Highways {

    public static final int INIT = -1;
    public static final int VISITED = 0;
    public static final int CYC_START = -2;
    public static final int IN_CYC = -3;
    public static final int INF = 1000000000;
    public static final int NEG_INF = -1 * INF;
 
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

    public static void bellman_ford(int numVertices, ArrayList<Main.Edge> edgeSet, int[] dist) {
        Arrays.fill(dist, INF);
        dist[1] = 0;

        int false_pos = INF / 10;

        for (int i = 1; i <= 2 * numVertices; i ++) {
            for (Main.Edge edge : edgeSet) {
                if (dist[edge.A] < false_pos && dist[edge.A] + edge.w < dist[edge.B]) {
                    if (i < numVertices) {
                        dist[edge.B] = dist[edge.A] + edge.w;
                    } else {
                        dist[edge.B] = NEG_INF;
                    }
                }
            }
        }
    }

    public static void cyc_dfs(int v, ArrayList<Integer>[] adjList, int[] state) {
        if (state[v] != INIT || state[v] != CYC_START) {
            return;
        } else {
            state[v] = IN_CYC;
            for (int u = 0; u < adjList[v].size(); u ++) {
                int neighbor = adjList[v].get(u);
                if (state[neighbor] == INIT) {
                    cyc_dfs(neighbor, adjList, state);

                }
            }

            
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int numJunctions;
        int numRoads;
        int numQueries;
        int setNum;
        int weight;

        int ind;

        int start;
        int end;

        Main.Edge e = null;

        Main inst = new Main();
        ArrayList<Main.Edge> edgeList = null;

        int[] dist;
        int[] vertexSet;
        //int[] state;
        //ArrayList<Integer>[] adjList;


        setNum = 1;
        while ((line = input.readLine()) != null) {
            if (line.trim().length() == 0) { continue; }

            st = new StringTokenizer(line);            
            numJunctions = Integer.parseInt(st.nextToken());
            
            //state = new int[numJunctions + 1];
            dist = new int[numJunctions + 1];
            vertexSet = new int[numJunctions + 1];
            //adjList = new ArrayList[numJunctions + 1];

            for (int i = 1; i <= numJunctions; i ++) {
                vertexSet[i] = Integer.parseInt(st.nextToken());
                //state[i] = INIT;
                //adjList[i] = new ArrayList<Integer>();
            }

            /*
            for (int i = numJunctions; i < adjList.length; i ++) {
                adjList[i] = new ArrayList<Integer>();
            }
            */

            st = new StringTokenizer(input.readLine());
            numRoads = Integer.parseInt(st.nextToken());
            ind = 0;

            edgeList = new ArrayList<Main.Edge>();
            while (ind < numRoads) {
                st = new StringTokenizer(input.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                if ((start <= 0 || start > numJunctions) || (end <= 0 || end > numJunctions)) { 
                    continue;
                }
                weight = (int) Math.pow((vertexSet[end] - vertexSet[start]), 3);
                e = inst.new Edge(start, end, weight);
                //e.w = (int) Math.pow((vertexSet[e.B] - vertexSet[e.A]), 3);
                edgeList.add(e);
                ind ++;
            }

            /*
            for (Main.Edge edge : edgeList) {
                adjList[edge.A].add(edge.B);
                adjList[edge.B].add(edge.A);
            }
            */

            bellman_ford(numJunctions, edgeList, dist);
            
            /*
            for (int v = 1; v <= numJunctions; v ++) {
                if (state[v] == CYC_START) {
                   cyc_dfs(v, adjList, state);
               }
            }
            */

            st = new StringTokenizer(input.readLine());
            numQueries = Integer.parseInt(st.nextToken());
            ind = 0;
            sb.append("Set #" + setNum + "\n");
            
            while (ind < numQueries) {
                st = new StringTokenizer(input.readLine());
                end = Integer.parseInt(st.nextToken());
                if (end <= 0 || end > numJunctions || dist[end] < 3 || dist[end] >= (INF / 10)) {
                    sb.append("?");
                } else {
                    sb.append(dist[end]);
                }
                sb.append("\n");
                ind ++;
            }

            setNum ++;

        }

        System.out.print(sb.toString());

    }
}