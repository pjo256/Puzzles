/**
 * UVA 10264
 * http://vjudge.net/vjudge/contest/view.action?cid=40131#problem/D
 */

import java.io.*;
import java.util.*;

public class BitMaskCorner {

  public static void main(String[] args) throws Exception {
      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      String line;
      StringTokenizer st;
      StringBuilder sb = new StringBuilder();
      int dim = 0;
      int cornersRemaining;
      int maxCorners = (int) Math.pow(2, 15);
      int[] weights;
      int[] neighborWeights;
      int numCorners;
      int maxPotency;
      int cornerMask;
      int pointMask;     
      while ((line = input.readLine()) != null) {
          maxPotency = -1;
          neighborWeights = new int[maxCorners];
          weights = new int[maxCorners];
          st = new StringTokenizer(line);
          dim = Integer.parseInt(st.nextToken());
          numCorners = (int) Math.pow(2, dim);
          for (int corner = 0; corner < numCorners; corner ++ ) {
              weights[corner] = Integer.parseInt(input.readLine());
          }
          for (pointMask = 0; pointMask < numCorners; pointMask ++) {
              for (cornerMask = 0; cornerMask < dim; cornerMask++) {
                  int neighbor = pointMask ^ (1 << cornerMask);
                  neighborWeights[pointMask] += weights[neighbor];
              }
          }

          for (pointMask = 0; pointMask < numCorners; pointMask ++) {
              for (cornerMask = 0; cornerMask < dim; cornerMask ++) {
                  int neighbor = pointMask ^ (1 << cornerMask);
                  if (maxPotency < neighborWeights[pointMask] + neighborWeights[neighbor]) {
                   maxPotency = neighborWeights[pointMask] + neighborWeights[neighbor];
                  }
              }
          }
          sb.append(maxPotency + "\n");
      }     
      System.out.print(sb.toString());
  }
}