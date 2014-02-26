#include <vector>
#include <map>
#include <iostream>
#include <cstdio>
#include <string>
#include <sstream>

using namespace std;

int main() {


	unsigned int list_size, num_queries, currNum, kth, target;
	
	//istringstream ss;
	//string line;
	//while (getline(cin, line)) {
	while(scanf("%u %u", &list_size, &num_queries) == 2) {
		//ss.str(line);

		//ss >> list_size;
		//ss >> num_queries;

		map< int, vector<int> > occurrenceToKth;

		//getline(cin, line);
		//ss.str(line);
		for (unsigned int ind = 0; ind < list_size; ind ++) {
			scanf("%u", &currNum);
			occurrenceToKth[currNum].push_back(ind + 1);	
		}

		while (num_queries > 0) {
			scanf("%u %u", &kth, &target);
			if (occurrenceToKth[target].size() < kth) {
				cout << "0" << endl;
			} else {
				cout << occurrenceToKth[target][kth - 1] << endl;
			}

			num_queries -= 1;
		}
		
	}

	return 0;
}
