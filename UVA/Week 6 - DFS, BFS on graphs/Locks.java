/**
 * UVA 12160
 * http://vjudge.net/vjudge/contest/view.action?cid=40828#problem/C
 */

import java.io.*;
import java.util.*;

public class Locks {

	public static final int INIT = 0;

	public static int bfs(int[] stepsTo, int initState, int unLock, int[] keys) {

			Queue<Integer> queue = new ArrayDeque<Integer>();
			queue.add(initState);

			int currState;
			int newState;

			int kInd;
			while (!queue.isEmpty()) {

				currState = queue.poll();
				
				if (currState == unLock) {
					return stepsTo[currState];
				}

				kInd = 0;
				while (kInd < keys.length) {
					newState = (keys[kInd] + currState);
					newState %= 10000;
					
					if (stepsTo[newState] == INIT) {
						queue.add(newState);
						stepsTo[newState] = stepsTo[currState] + 1; //newState was one button-press away
					}

					kInd ++;
				}

			}

			return -1;


	}

	public static void main(String[] args) throws Exception {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int unLock;
		int currCode;
		int numButtons;
		int buttonVal;

		int[] stepsTo = new int[10001];
		int[] keys;
		int ind;

		int stepsToUnlock;
		int caseNum = 1;

		while ((line = input.readLine()) != null) {
			st = new StringTokenizer(line);
			currCode = Integer.parseInt(st.nextToken());
			unLock = Integer.parseInt(st.nextToken());
			numButtons = Integer.parseInt(st.nextToken());
			Arrays.fill(stepsTo, INIT);

			if (unLock == 0 && currCode == 0 && numButtons == 0) {
				break;
			}

			keys = new int[numButtons];
			st = new StringTokenizer(input.readLine());
			ind = 0;

			while(ind < numButtons) {
				buttonVal = Integer.parseInt(st.nextToken());
				keys[ind] = buttonVal;
				ind ++;
			}

			stepsToUnlock = bfs(stepsTo, currCode, unLock, keys);
			sb.append("Case " + caseNum + ": ");
			if (stepsToUnlock != -1) {
				sb.append(stepsToUnlock + "\n");
			} else {
				sb.append("Permanently Locked\n");
			}

			caseNum ++;

		
		}

		System.out.print(sb.toString());

	}
}