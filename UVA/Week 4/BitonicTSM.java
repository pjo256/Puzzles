import java.util.*;
import java.io.*;

public class BitonicTSM {

    static class Point
    {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
        public String toString() { return String.format("(%.03f,%.03f)", x,y); }
    }   

    static double dist(Point a, Point b) { return Math.hypot(a.x-b.x,a.y-b.y); }

    static double dp(int p1, int p2, int numPoints, double[][] dist, double[][] lookUp) {
        int v = 1 + Math.max(p1, p2);
        if (v == numPoints - 1) {
            return dist[p1][v] + dist[v][p2];
        }

        if (lookUp[p1][p2] > -0.5) {
            return lookUp[p1][p2];
        }

        lookUp[p1][p2] = Math.min(dist[p1][v] + dp(v, p2, numPoints, dist, lookUp),
                                  dist[v][p2] + dp(p1, v, numPoints, dist, lookUp));
    }
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int numPoints;
        double[] dist;
        double[][] lookUp;
        Point[] pnts;
        while ((line = input.readLine()) != null) {
            st = new StringTokenizer(line);
            numPoints = Integer.parseInt(st.nextToken());
            for (int i = 0; i < numPoints; ++i) {
                st = new StringTokenizer(input.readLine());
                pnts[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for (int i = 0; i < numPoints; ++i) {
                for (int j = 0; j < numPoints; ++j) {
                    dist[i][j] = dist(pnts[i], pnts[j]);
                }
            }

            lookUp = new double[numPoints][numPoints];
            for (int i = 0; i < numPoints; ++i) {
                Array.fill(lookUp[i], -1);
            }

            sb.append(String.format("%.3f\n", dp(0, 0, numPoints, dist, lookUp)));
        }
        System.out.print(sb.toString());
    }
}