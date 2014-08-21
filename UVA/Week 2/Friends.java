/**
 * UVA 11503
 * http://vjudge.net/vjudge/contest/view.action?cid=40131#problem/C
 */

import java.io.*;
import java.util.*;

public class Friends {

	private static class UnionFindSet {
		private int numTreelets;
		
		private int[] label;
		private int[] size;

		public UnionFindSet(int numTreelets) {
			this.numTreelets = numTreelets;
			
			this.size = new int[numTreelets];
			this.label = new int[numTreelets];

			for (int ind = 0; ind < numTreelets; ind ++) {
				size[ind] = 1;
				label[ind] = ind;
			}
		}


		public boolean disjoint(int node_1, int node_2) {
			return find(node_1) != find(node_2);
		}

		public int sizeOf(int node_1, int node_2) {
			
			int parent_1 = find(node_1);
			int parent_2 = find(node_2);

			if (parent_1 != parent_2) {
				return size[parent_1] + size[parent_2];
			} else {
				return size[parent_1];
			}
		}

		public void union(int node_1, int node_2) {
			int root_1 = find(node_1);
			int root_2 = find(node_2);
			if (root_1 != root_2) {
				if (size[root_1] < size[root_2]) {
					label[root_1] = root_2;
					size[root_2] += size[root_1];
				} else {
					label[root_2] = root_1;
					size[root_1] += size[root_2];
				}

				numTreelets--;
			}
		}

		public int find(int node) {
			int root = node;
			while(root != label[root]) {
				root = label[root];
			}
			while(node != root) {
				int currNode = label[node];
				label[node] = root;
				node = currNode;
			}
			return root;
		}
	
	}


	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int numTests = Integer.parseInt(input.readLine());
		int numFriendShips;

		UnionFindSet friendships;
		HashMap<String, Integer> nameToIndex = new HashMap<String, Integer>();
		
		int globalIndex = 0;
		
		int sumFriends;

		String first;
		String second;

		Integer first_ind;
		Integer second_ind;

		while ( (line = input.readLine()) != null) {
			numFriendShips = Integer.parseInt(line);
			globalIndex = 0;
			friendships = new UnionFindSet(2 * numFriendShips);
			nameToIndex.clear();
			while (numFriendShips > 0) {

				line = input.readLine();
				st = new StringTokenizer(line);
				
				first = st.nextToken();
				second = st.nextToken();

				first_ind = nameToIndex.get(first);
				if (first_ind == null) { 
					nameToIndex.put(first, globalIndex);
					first_ind = globalIndex;
					globalIndex ++;
				}

				second_ind = nameToIndex.get(second);
				if (second_ind == null) {
					nameToIndex.put(second, globalIndex);
					second_ind = globalIndex;
					globalIndex++;
				}
						
				sumFriends = friendships.sizeOf(first_ind, second_ind);
				friendships.union(first_ind, second_ind);
				sb.append(sumFriends + "\n");			

				numFriendShips --;
			}	
				
		}

		System.out.print(sb);


	}

}
