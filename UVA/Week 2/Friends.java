import java.io.*;
import java.util.*;

public class Main {

	private class UnionFindSet {
		int numTreelets;
		
		int[] label;
		int[] size;

		public UnionFindSet(int numTreelets) {
			this.numTreelets = numTreelets;
			
			this.size = new int[numTreelets];
			this.label = new int[numTreelets];

			for (int ind = 0; ind < numTreelets; ind ++) {
				size[ind] = ind;
				label[ind] = ind;
			}
		}


		public boolean disjoint(int node_1, int node_2) {
			return find(node_1) != find(node_2);
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

		Main.UnionFindSet friendships;
		HashMap<String, Integer> nameToIndex = new HashMap<String, Integer>();
		
		int globalIndex = 0;
		
		int sumFriends;

		String first;
		String second;

		Integer first_ind;
		Integer second_ind;

		while ( (line = input.readLine()) != null) {
			numFriendShips = Integer.parseInt(input.readLine());
			globalIndex = 0;
			friendships = new Main().new UnionFindSet(numFriendShips / 2);
			nameToIndex.clear();
			while (numFriendShips > 0 && (line = input.readLine()) != null) {

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
						
				if (friendships.disjoint(first_ind, second_ind)) {
					sumFriends = friendships.size[first_ind] + friendships.size[second_ind];
					friendships.union(first_ind, second_ind);
					sb.append(sumFriends + "\n");			
				}

				numFriendShips --;
			}	
				
		}

		System.out.print(sb);


	}

}
