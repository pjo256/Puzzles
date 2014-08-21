/**
 * UVALive 6139
 * http://vjudge.net/vjudge/contest/view.action?cid=40131#problem/E
 */

import java.io.*;
import java.util.*;

public class FTIntervalProduct {
  private class FenwickTree {
    public int[] FT;
    public int[] values;
    public int size;

    public FenwickTree(int size) {
      FT = new int[size + 1];
      values = new int[size];
    }

    public void buildFenwickTree(int[] list, int key) {
        FT[0] = 0;


        for (int i = 1; i <FT.length; i ++) {
            int j = i;
            values[j - 1] = list[i - 1];
            do {
                if (key == -1) {
                  if (list[i - 1] < 0) {
                    FT[j] += 1;
                  }
                } else if (key == 0) {
                  if (list[i - 1] == 0) {
                    FT[j] += 1;
                  }
                }
                j += LSO(j);
            } while (j < FT.length);
        }
    }

    public int LSO(int x) {
      return x & (-x);
    }

    public int query(int x) {
        return x == 0 ? 0 : FT[x] + query(x ^ LSO(x));
    }

    public void update(int x, int inc) {


        while (x < FT.length) {
            FT[x] += inc;
            x += LSO(x);
        }
    }

    public void print() {
      for (int i = 1; i < FT.length; i ++) {
        System.out.print(FT[i] + " ");
      }

      System.out.println();
    }

  }

  public static void main(String[] args) throws Exception {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String line;
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();

    int size;
    int numRounds;

    char cmd;
    int updateInd;
    int updateVal;

    int lo;
    int hi;

    int zeroCount;
    int negCount;
    int result;

    int listInd;
    int[] list;
    Main.FenwickTree negFT;
    Main.FenwickTree zeroFT;

    while ( (line = input.readLine()) != null) {
      st = new StringTokenizer(line);
      size = Integer.parseInt(st.nextToken());
      numRounds = Integer.parseInt(st.nextToken());
      negFT = new Main().new FenwickTree(size);
      zeroFT = new Main().new FenwickTree(size);

      list = new int[size];

      line = input.readLine();
      st = new StringTokenizer(line);

      listInd = 0;
      while (st.hasMoreTokens()) {
        list[listInd] = Integer.parseInt(st.nextToken());
        listInd ++;
      }

      zeroFT.buildFenwickTree(list, 0);
      negFT.buildFenwickTree(list, -1);


      while (numRounds > 0 && (line = input.readLine()) != null) {
        st = new StringTokenizer(line);
        cmd = st.nextToken().charAt(0);

        if (cmd == 'C') {
          updateInd = Integer.parseInt(st.nextToken());
          updateVal = Integer.parseInt(st.nextToken());

          //Update FT
          //
          if (updateVal < 0) {

              if (list[updateInd - 1] == 0) {
                zeroFT.update(updateInd, -1);
              }

              if (list[updateInd - 1] >= 0) {
                negFT.update(updateInd, 1);
              }
          } else if (updateVal > 0) {

              if (list[updateInd - 1] == 0) {
                zeroFT.update(updateInd, -1);
              }

              if (list[updateInd - 1] < 0) {
                negFT.update(updateInd, -1);
              }

          } else {


              if (list[updateInd - 1] < 0) {
                negFT.update(updateInd, -1);
              }

              if (list[updateInd - 1] != 0) {
                zeroFT.update(updateInd, 1);
              }

          }

          list[updateInd - 1] = updateVal;


        } else if (cmd == 'P') {
          lo = Integer.parseInt(st.nextToken());
          hi = Integer.parseInt(st.nextToken());

          zeroCount = zeroFT.query(hi) - zeroFT.query(lo -1);
          negCount = negFT.query(hi) - negFT.query(lo - 1);


          if (zeroCount > 0) {
            sb.append("0");
          } else if (negCount % 2 == 0) {
            sb.append("+");
          } else {
            sb.append("-");
          }

        }
        numRounds --;
      }

      sb.append("\n");
    }

    System.out.print(sb.toString());

  }
}