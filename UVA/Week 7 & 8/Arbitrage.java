/**
 * UVA 104
 * http://vjudge.net/vjudge/contest/view.action?cid=42233#problem/G
 */

import java.io.*;
import java.util.*;

public class Arbitrage {

    //Double.compare works better.
    public static final double EPS = 0.000000001;

    //call with i, i since transaction chain must end at origin
    //country i to country j
    //prints "i ... kIntermediate[i - 1][j - 1][s] j"
    public static void print_path(int dim, int i, int j, int t, int[][][] kIntermediate, StringBuilder sb) {
        if (t < 1) {
            sb.append(i);
        } else {
            int country = kIntermediate[i - 1][j - 1][t];
            print_path(dim, i, country, t - 1, kIntermediate, sb); 
            sb.append(" ").append(j);
        }
    }

    //Based on Prof. Siegel's FW with path costs defined as product of weights rather than sum
    //kIntermediate used for path recovery
    public static void fw(double[][][] pathCost, int[][][] kIntermediate, int dim) {
        //pathCost[i][j][trade] stores the maximum profit moving from currency i to j in trade trades
        //1st trade --> Direct currency transfer, which is the init'd value.
        //Intermediate currency used for trade >= 2.
        
        for (int trade = 2; trade <= dim; trade ++) {
            for (int k = 0; k < dim; k ++) {
                for (int i = 0; i < dim; i ++) {
                    for (int j = 0; j < dim; j ++) {
                        //Extend path by trading from k to j
                        
                        //if (dim == 20 && Double.compare(pathCost[i][k][trade - 1], 1.00049764032455) == 0 && trade <= 2) {
                          //  System.out.println("pathCost[" + i + "][" + k + "][" + (trade - 1)+ "] == " + pathCost[i][k][trade - 1]);
                         //   System.out.println("pathCost[" + k + "][" + j + "][1] == " + pathCost[k][j][1]);

                       // }
                        
                        
                        if (Double.compare(pathCost[i][j][trade], pathCost[i][k][trade - 1] * pathCost[k][j][1]) < 0) {
                            //Transfer to country k + 1's currency for higher profit
                            kIntermediate[i][j][trade] = k + 1;
                            pathCost[i][j][trade] = pathCost[i][k][trade - 1] * pathCost[k][j][1];
                            
                        }
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

        int dim;
        int numTrades; //previous submission was wrong, forgot to zero-pad third dimension

        int[][][] kIntermediate;
        double[][][] pathCost;


        int country;
        int rInd;
        int cInd;
        boolean noArbitrage;
        boolean found;


        while ((line = input.readLine()) != null) {
            if (line.trim().length() == 0) { continue; }

            st = new StringTokenizer(line);            
            dim = Integer.parseInt(st.nextToken());
            numTrades = dim + 1;
            
            //trade dimension is zero-padded
            kIntermediate = new int[dim][dim][numTrades];
            pathCost = new double[dim][dim][numTrades];

            rInd = 0;
            while (rInd < dim) {
                st = new StringTokenizer(input.readLine());
                cInd = 0;
                while (st.hasMoreTokens()) {
                    //Direct exchange between countries is given by table
                    pathCost[rInd][cInd][1] = 1.0;
                    if (rInd != cInd) {
                        pathCost[rInd][cInd][1] = Double.parseDouble(st.nextToken());
                    }

                    kIntermediate[rInd][cInd][1] = rInd;

                    
                    cInd ++;
                }
                rInd ++;
            }

            fw(pathCost, kIntermediate, dim);

            noArbitrage = true;
            found = false;
            for (int trade = 2; trade <= dim && noArbitrage; trade ++) {
                found = false;
                for (rInd = 0; rInd < dim && !found; rInd ++) {
                    if (Double.compare(pathCost[rInd][rInd][trade], 1.01) > 0) {
                        //Path strating from currency rInd turned a profit
                        found = true;
                        noArbitrage = false;
                        country = rInd + 1;
                        print_path(dim, country, country, trade, kIntermediate, sb);
                        sb.append("\n");
                    } else {

                        //if (dim == 20) { sb.append(pathCost[rInd][rInd][trade] + "\n"); }
                    }
                }
            }

            if (noArbitrage) {
                sb.append("no arbitrage sequence exists\n");
            }
        }

        System.out.print(sb.toString());


    }
}