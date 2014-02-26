import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
public class Problem11991 {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String line;
        int[][] map = new int[1000001][100001];
        String[] temp;
        StringTokenizer st;
	long start = System.currentTimeMillis();
        int N, M, K, V;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                map[x][0]++;
                map[x][map[x][0]] = i + 1;
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                K = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                if (map[V][0] >= K) {
                    pw.write(map[V][K] + "\n");
                } else {
                    pw.write("0\n");
                }
            }
            for (int i = 1; i < 1000001; i++) {
                map[i][0] = 0;
            }
        }
        pw.flush();
        pw.close();
        br.close();
	long end = System.currentTimeMillis();
	System.out.println(end - start);
    }
}
