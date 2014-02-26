import java.io.*;
import java.util.*;

public class TargetSum {

	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int targetSum;
		int numElements;
		int currSum;
		int ind;
		int[] list;
		HashSet<ArrayList<Integer>> answers;
		ArrayList<Integer> indicesUsed;

		while((line = input.readLine()) != null) {
			st = new StringTokenizer(line);

			targetSum = Integer.parseInt(st.nextToken());
			numElements = Integer.parseInt(st.nextToken());
			answers = new HashSet<ArrayList<Integer>>();
			ArrayList<Integer> numbers = new ArrayList<Integer>();

			if (targetSum == 0 && numElements == 0) {
				break;
			}	
			
			ind = 0;
			while (st.hasMoreTokens()) {
				/*list[ind]*/ numbers.add(Integer.parseInt(st.nextToken()));
				ind ++;
			}		
			
			sb.append("Sums of " + targetSum + ":\n");

			for (int mask = 0; mask < (1 << numbers.size()); mask ++) {
				currSum = 0;
				indicesUsed = new ArrayList<Integer>();
				for (int i = 0; i < numbers.size(); i ++) {
					int currBit = 1 << i;
					if ( (mask & currBit) != 0) {
						currSum += numbers.get(i);
						indicesUsed.add(numbers.get(i));
					}
				}

				if (currSum == targetSum) {
					answers.add(indicesUsed);
				}
			}

			List<ArrayList<Integer>> sortedAnswers = new ArrayList<ArrayList<Integer>>(answers);
			Collections.sort(sortedAnswers, new Comparator<ArrayList<Integer>> () {
				public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
					int len = Math.min(a.size(),b.size());
					int i;
					for (i = 0; i < len; i ++) {
						if (a.get(i).compareTo(b.get(i)) != 0) {
							return -1 * a.get(i).compareTo(b.get(i));
						}
					}

					int lastPos = i - 1;

					if (a.size() < b.size()) {
						return -1 * a.get(lastPos).compareTo(b.get(lastPos + 1));
					} else if (b.size() > a.size()) {
						return -1 * a.get(lastPos + 1).compareTo(b.get(lastPos));
					} else {
						return 0;
					}
				}
			});
			for (ArrayList<Integer> answer : sortedAnswers) {
				ind = 0;
				Collections.sort(answer, Collections.reverseOrder());
				for (ind = 0; ind < answer.size(); ind ++) {
					if (ind < answer.size() - 1) {
						sb.append(answer.get(ind) + "+");
					} else {
						sb.append(answer.get(ind) + "\n");
					}
				}

			}

			if (answers.size() == 0) {
				sb.append("NONE\n");
			}	
		}
		
		System.out.print(sb);
	}

}

