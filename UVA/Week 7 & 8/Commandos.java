/**
 * UVA 11463
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/D
 */

import java.io.*;
import java.util.*;

public class Commandos {

 
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

    public static void floyd_warshall(int numVertices, ArrayList<Commandos.Edge> edgeList, int[][] dist) {

        for (int i = 0; i < numVertices; i ++) {
            Arrays.fill(dist[i], 1 << 20);
            dist[i][i] = 0;
        }

        for (Commandos.Edge e : edgeList) {
            dist[e.A][e.B] = e.w;
            dist[e.B][e.A] = e.w;
        }

        for (int k = 0; k < numVertices; k ++) {
            for (int i = 0; i < numVertices; i ++) {
                for (int j = 0; j < numVertices; j ++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int numBuildings;
        int numRoads;
        int caseNum;

        int ind;

        int start;
        int end;
        int t;
        Commandos.Edge e = null;

        Commandos inst = new Commandos();
        ArrayList<Commandos.Edge> edgeList = null;

        int[][] dist;


        caseNum = 1;
        int numTests = Integer.parseInt(input.readLine());
        while ((line = input.readLine()) != null) {
            st = new StringTokenizer(line);
            if (st.countTokens() == 0) {
                continue;
            }

            numBuildings = Integer.parseInt(st.nextToken());
            numRoads = Integer.parseInt(input.readLine());
            dist = new int[100][100];
        
            ind = 0;

            edgeList = new ArrayList<Commandos.Edge>();
            while (ind < numRoads) {
                st = new StringTokenizer(input.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                e = inst.new Edge(start, end, 1);
                edgeList.add(e);
                ind ++;
            }

            st = new StringTokenizer(input.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            floyd_warshall(numBuildings, edgeList, dist);
            t = Integer.MIN_VALUE;
            for (int k = 0; k < numBuildings; k ++) {
                //One commando c is sent to intermediate vertex k, using N commandos
                //Mission complete when last commando has arrives at end, having travelled furthest.
                t = Math.max(t, dist[start][k] + dist[k][end]);
            }

            sb.append("Case " + caseNum + ": " + t + "\n");
            caseNum ++;

            

        }

        System.out.print(sb.toString());

    }
}