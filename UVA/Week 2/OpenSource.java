import java.io.*;
import java.util.*;

public class OpenSource {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		HashMap<String, HashSet<String>> projectToStudents;
		HashMap<String, Integer> projectToCount;

		String currProject = null;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		ArrayList<String> studentsLastRound = new ArrayList<String>(2);
		//HashSet<String> votingStudents;
		HashSet<String> participatedStudents;
		HashSet<String> illegal;

		int voteCount;
		//long start = System.currentTimeMillis();
		
		boolean startProject = true;
		while ((line = input.readLine()) != null && line.charAt(0) != '0') {

			projectToCount = new HashMap<String, Integer>(100);
			projectToStudents = new HashMap<String, HashSet<String>>(100);
			participatedStudents = new HashSet<String>(10000);
			illegal = new HashSet<String>(10);

			while (line.charAt(0) != '1') {
				if (Character.isUpperCase(line.charAt(0))) {
					//if (currProject != null) {
					//	projectToCount.put(currProject, projectToCount.get(currProject) + 
					//		votingStudents.size());
					//}

					//for (String voter : votingStudents) {
					//	participatedStudents.add(voter);
					//}

					projectToStudents.put(line, new HashSet<String>());
					//projectToCount.put(line, 0);
					//votingStudents = new HashMap<String>();
					currProject = line;
					startProject = true;
				} else if (Character.isLowerCase(line.charAt(0))) {
					if (startProject) {
						for (String voter : studentsLastRound) {
							participatedStudents.add(voter);
						}
				
						studentsLastRound.clear();
						startProject = false;
					}
	
					if (!participatedStudents.contains(line)) {
						projectToStudents.get(currProject).add(line);
						//votingStudents.add(line);
						studentsLastRound.add(line);
					} else {
						illegal.add(line);
					}
				} else {
					break;
				}


				line = input.readLine();
			}

			for (Map.Entry<String, HashSet<String>> entry : projectToStudents.entrySet()) {
				voteCount = entry.getValue().size();
				for (String disqualified : illegal) {
					if (entry.getValue().contains(disqualified)) {
						voteCount--;
					}
					
				}
				projectToCount.put(entry.getKey(), voteCount);
			}

			Set<Map.Entry<String, Integer>> countEntries = /*new ArrayList<Map.Entry<String, Integer>>(*/projectToCount.entrySet();
			Collections.sort(countEntries, new Comparator<Map.Entry<String, Integer>>() {

				@Override
				public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
					if (e1.getValue() == e2.getValue()) {
						return e1.getKey().compareTo(e2.getKey());
					} else {
						return -1 * e1.getValue().compareTo(e2.getValue());
					}
				}
			});

			
			for (Map.Entry<String, Integer> projectCount : countEntries) {
				sb.append(projectCount.toString() + "\n");
			} 

		}

		System.out.println(sb.toString());
	}
}	
