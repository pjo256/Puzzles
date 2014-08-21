import java.io.*;
import java.util.*;

public class Main3 {
    public static int dfs(int[] adjList, int[] reachableFrom, boolean[] state, int u) {
        state[u] = true;
        int tot = 0;
        int neighbor = adjList[u];
        if(neighbor != -1 && !state[neighbor]) {
            tot = dfs(adjList, reachableFrom, state, neighbor) + 1;
        }
        state[u] = false;
        reachableFrom[u] = tot;
        return tot;
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
        boolean[] state;
        int sender;
        int recepient;
        int caseNum = 1;
        int bestSender;

        while ((line = input.readLine()) != null) {
            numVertices = Integer.parseInt(line);
            adjList = new int[numVertices];
            reachableFrom = new int[numVertices];
            state = new boolean[numVertices];
            Arrays.fill(adjList, -1);
            Arrays.fill(reachableFrom, -1);
            Arrays.fill(state, false);
            ind = 0;
            while (ind < numVertices) {
                st = new StringTokenizer(input.readLine());
                sender = Integer.parseInt(st.nextToken());
                recepient = Integer.parseInt(st.nextToken());
                adjList[sender - 1] = recepient - 1;
                ind ++;
            }

            int ans = 0, best_len = 0;
            for(int i = 0; i < numVertices; i++) {
                if(reachableFrom[i] == -1) dfs(adjList, reachableFrom, state, i);
                if(reachableFrom[i] > best_len) {
                    best_len = reachableFrom[i];
                    ans = i;
                }
            }


            System.out.println("Case " + caseNum + ": " + (ans + 1));
            caseNum ++;
        }
    }
}