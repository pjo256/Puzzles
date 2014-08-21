/**
 * UVA 11995
 * http://vjudge.net/vjudge/contest/view.action?cid=39861#problem/D
 */

import java.io.*;
import java.util.*;

public class DataBags {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int numOps = 0;
		int bagCmd = 0;
		int item = 0;

		Stack<Integer> stackBag = null;
		ArrayDeque<Integer> queueBag = null;
		PriorityQueue<Integer> pQueueBag = null;
		
		StringBuilder stackPop = null;
		StringBuilder queuePop = null;
		StringBuilder pQPop = null;

		StringBuilder poppedItems = null;

		boolean isStack = true;
		boolean isQueue = true;
		boolean isMaxPQueue = true;

		boolean poppedEmpty = false;
		boolean neverPopped = true;

		Integer stEle = null;
		Integer qEle = null;
		Integer pQEle = null;
		while ( (line = input.readLine()) != null) {
		
			st = new StringTokenizer(line);
		
			if (st.countTokens() == 1) {
				numOps = Integer.parseInt(st.nextToken());
				stackBag = new Stack<Integer>();
				queueBag = new ArrayDeque<Integer>();
				pQueueBag = new PriorityQueue<Integer>(numOps, Collections.reverseOrder());
				stackPop = new StringBuilder();
				queuePop = new StringBuilder();
				pQPop = new StringBuilder();
				poppedItems = new StringBuilder();

				poppedEmpty = false;
				neverPopped = true;

				isStack = true;
				isQueue = true;
				isMaxPQueue = true;
			} else if (st.countTokens() == 2) {
				
				
				bagCmd = Integer.parseInt(st.nextToken());
				item = Integer.parseInt(st.nextToken());
			
				if (bagCmd == 1 && !poppedEmpty) {
					
					stackBag.push(item);
					queueBag.addLast(item);
					pQueueBag.add(item);
					
				} else if (bagCmd == 2 && !poppedEmpty) {
					neverPopped = false;
					stEle = (!stackBag.isEmpty()) ? stackBag.pop() : null;
					
					qEle = queueBag.pollFirst();
					pQEle = pQueueBag.poll();
				
					if (stEle != null && qEle != null && pQEle != null) {
						poppedItems.append(item);
						stackPop.append(stEle);
						queuePop.append(qEle);
						pQPop.append(pQEle);

						isStack = (stEle.equals(item) && stackPop.toString().equals(poppedItems.toString())) ? true : false;	
						isQueue = (qEle.equals(item) && queuePop.toString().equals(poppedItems.toString())) ? true : false;
						isMaxPQueue = (pQEle.equals(item) && pQPop.toString().equals(poppedItems.toString())) ? true : false;
					} else {
						poppedEmpty = true;
						isStack = false;
						isQueue = false;
						isMaxPQueue = false;
					}
				}

				numOps --;
			
				if (numOps == 0) {

					if (!neverPopped && (poppedEmpty || !(isQueue || isStack || isMaxPQueue)) ) {
						sb.append("impossible");
					} else if ( neverPopped || (isQueue && isStack) || (isStack && isMaxPQueue) || (isQueue && isMaxPQueue)) {
						sb.append("not sure");
					} else if (isQueue) {
						sb.append("queue");
					} else if (isStack) {
						sb.append("stack");
					} else if (isMaxPQueue) {
						sb.append("priority queue");
					}

					sb.append("\n");
					
				}
			} 

		}

		System.out.print(sb);

	}

}

