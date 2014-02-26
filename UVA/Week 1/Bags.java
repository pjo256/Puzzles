import java.io.*;
import java.util.*;

public class Bags {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int numTests = 0;
		
		int numOps = 0;

		int bagCmd = 0;
		int item = 0;

		int pushedElements = 0;

		boolean invalidBag = false;

		StringBuilder push = null;		
		StringBuilder pop = null; 

		HashMap<Integer, Integer> elementToFreq = null;
		while ( (line = input.readLine()) != null) {
		
			st = new StringTokenizer(line);
		
			if (st.countTokens() == 1) {
				invalidBag = false;
				numOps = Integer.parseInt(st.nextToken());
				push = new StringBuilder();
				pop = new StringBuilder();
				elementToFreq = new HashMap<Integer, Integer>();
			} else if (st.countTokens() == 2) {
				
				
				bagCmd = Integer.parseInt(st.nextToken());
				item = Integer.parseInt(st.nextToken());
			
				if (bagCmd == 1) {
					pushedElements ++;
					push.append(item + "");

					if (elementToFreq.containsKey(item)) {
						elementToFreq.put(item, elementToFreq.get(item) + 1);
					} else {
						elementToFreq.put(item, 1);
					}
				} else if (bagCmd == 2) {
					
					if (elementToFreq.containsKey(item) && elementToFreq.get(item) > 0) {
						pop.append(item + "");
						pushedElements --;
						elementToFreq.put(item, elementToFreq.get(item) - 1);
					} else {
						if (!invalidBag) {
							sb.append("impossible\n");
						}
						invalidBag = true;
						continue;
					}
				}

				if (pushedElements == 0) {
					//Append a sentinel to denote emptied bag 
					push.append("_");
					pop.append("_");
				}
				numOps --;
				//Push: 1 1 1 _ 2 2
				//Pop:  1 1 1 _ 2 2
				if (numOps == 0 && !invalidBag) {
					checkBagType(push, pop, sb);
				}
			} 

		}

		System.out.print(sb);

	}

	public static void checkBagType(StringBuilder push, StringBuilder pop, StringBuilder sb) {

		boolean isQueue = false;
		boolean isStack = false;
		boolean isPQueue = false;
	
		String pushStr = push.toString();
		String popStr = pop.toString();

		int popLen = popStr.length();
		int pushLen = pushStr.length();
		
		if (popLen > pushLen) {
			sb.append("impossible");
			sb.append("\n");
		} else {
			

			//StringTokenizer pushTokens = new StringTokenizer(pushStr, "_");
			//StringTokenizer popTokens = new StringTokenizer(popStr, "_");

			//int pushSeq = pushTokens.countTokens();
			//int popSeq = popTokens.countTokens();

			//int puInd = 0;
			//int poInd = 0;

			//while (puInd < pushSeq && poInd < popSeq) {
			if (pushStr.startsWith(popStr, pushLen - popLen)) {
				isQueue = true;
			} 

			
			if (pushStr.startsWith(pop.reverse().toString(), pushLen - popLen)) {
				isStack = true;
			} 

			
			isPQueue = true;
			for (int ind = pop.length() - 1; ind > 0; ind --) {
				if (!(Character.getNumericValue(pop.charAt(ind)) > 
					Character.getNumericValue(pop.charAt(ind - 1)))) {
						isPQueue = false;
						break;
				}			
			}

			if (!(isQueue || isStack || isPQueue)) {
				sb.append("impossible");
			} else if ( (isQueue && isStack) || (isStack && isPQueue) || (isQueue && isPQueue)) {
				sb.append("not sure");
			} else if (isQueue) {
				sb.append("queue");
			} else if (isStack) {
				sb.append("stack");
			} else if (isPQueue) {
				sb.append("priority queue");
			}

			sb.append("\n");
		}
	}

	
}
