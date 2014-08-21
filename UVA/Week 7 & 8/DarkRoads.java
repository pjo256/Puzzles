/**
 * UVA 11631
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/A
 */

import java.io.*;
import java.util.*;

public class DarkRoads {


    private class UnionFindSet {
        int numTreelets;
        
        int[] label;
        int[] size;

        public UnionFindSet(int numTreelets) {
            this.numTreelets = numTreelets;
            
            this.size = new int[numTreelets];
            this.label = new int[numTreelets];

            for (int ind = 0; ind < numTreelets; ind ++) {
                size[ind] = ind;
                label[ind] = ind;
            }
        }


        public boolean disjoint(int node_1, int node_2) {
            return find(node_1) != find(node_2);
        }

        public void union(int node_1, int node_2) {
            int root_1 = find(node_1);
            int root_2 = find(node_2);
            if (root_1 != root_2) {
                if (size[root_1] < size[root_2]) {
                    label[root_1] = root_2;
                    size[root_2] += size[root_1];
                } else {
                    label[root_2] = root_1;
                    size[root_1] += size[root_2];
                }

                numTreelets--;
            }
        }

        public int find(int node) {
            int root = node;
            while(root != label[root]) {
                root = label[root];
            }
            while(node != root) {
                int currNode = label[node];
                label[node] = root;
                node = currNode;
            }
            return root;
        }
    
    }
 
    private class Edge implements Comparable<Edge> {
        int A, B, w;
        public Edge(int A, int B, int w) {
            this.A = Math.min(A, B);
            this.B = Math.max(A, B);
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

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int numJunctions;
        int numStreets;
        int weight;

        int ind;

        int start;
        int end;
        int len;

        int totalCost;

        DarkRoads.Edge e = null;
        DarkRoads.UnionFindSet uf = null;

        DarkRoads inst = new DarkRoads();
        ArrayList<DarkRoads.Edge> edgeList = null;

        while ((line = input.readLine()) != null) {
            st = new StringTokenizer(line);
            numJunctions = Integer.parseInt(st.nextToken());
            numStreets = Integer.parseInt(st.nextToken());
            
            if (numJunctions == 0 && numStreets == 0) {
                break;
            }
            ind = 0;
            totalCost = 0;

            edgeList = new ArrayList<DarkRoads.Edge>();
            while (ind < numStreets) {
                st = new StringTokenizer(input.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                len = Integer.parseInt(st.nextToken());
                totalCost += len;
                e = inst.new Edge(start, end, len);
                edgeList.add(e);
                ind ++;
            }

            Collections.sort(edgeList);
            int minCost = 0;
            uf = inst.new UnionFindSet(numJunctions);
            for (DarkRoads.Edge street : edgeList) {
                if (uf.disjoint(street.A, street.B)) { 
                    minCost += street.w; 
                    uf.union(street.A, street.B);
                }
            }

            System.out.printf("%d\n", (totalCost - minCost));
        }

    }
}