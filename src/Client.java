import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class Client {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		HashSet<Resume> candidates = new HashSet<>();
		HashSet<String> basicKeyWords = new HashSet<>();
		HashSet<String> desiredKeyWords = new HashSet<>();
		
	    System.out.println("Hello technical recruiter!");
	    Scanner scanner = new Scanner(System.in);
	    // Asking for the basic (required) skills for the candidate resume
	    System.out.print("Please type in required skills for each candidate separated by commas (ex: 'Java, Python, Agile'): ");
	    String allReqSkills = scanner.nextLine();
	    String[] allReqSkillsSplit = allReqSkills.split(", ");
	    for (String reqSkill : allReqSkillsSplit) {
	    	basicKeyWords.add(reqSkill.toLowerCase());
	    }
	    // Asking for the desired but not required skills for the candidate resume
	    System.out.print("Please type in desired (but not required) skills for each candidate separated by commas (ex: 'Figma, JavaScript'): ");
	    String allDesSkills = scanner.nextLine();
	    String[] allDesSkillsSplit = allDesSkills.split(", ");
	    for (String desSkill : allDesSkillsSplit) {
	    	desiredKeyWords.add(desSkill.toLowerCase());
	    }
	
	    System.out.println("Do you value experience, skills, or both equally in a candidate?");
	    //assess(Resume candidate, HashSet<String> desiredSkills, String weight)
	    System.out.print("Type 'experience', 'skills', or 'equal': ");
	    String weight = scanner.next();
	    // pass weight into assess
	    System.out.print("Would you like to 'type' in all the file names of Resumes you're considering or 'read' them in from a file where they are separated by commas?: ");
	    String response = scanner.nextLine();
	    System.out.println();
	    if(response.equals("type")) {
	    	
	    }
	    
	    System.out.println("Type in all the file names of candidate resumes you wish to consider, separated by commas: ");
	    scanner.nextLine();
	    //will need to figure out how to do scanner.nextLine() eventually
	    //Scanner scanner2 = new Scanner(System.in);
	    String allResumeNames = scanner.nextLine();
	    String[] allNamesSplit = allResumeNames.split(", ");
	    for (String name : allNamesSplit) {
	    	Resume candidate = new Resume(new File(name));
	    	candidates.add(candidate);
	    }
	    
		Algorithm evaluating = new Algorithm(candidates, basicKeyWords, desiredKeyWords, weight);
	    evaluating.evaluate(candidates);
	    System.out.print("Would you like to print it out evaluation results for candidates individually, or as a group? ");
	    String howToPrint = scanner.nextLine();
	    System.out.println();
	    System.out.println("Individual Results");
	    if (howToPrint.equals("individually")) {
	    	for (Resume cand : candidates) {
	    		evaluating.printIndvResult(cand);
	    		System.out.println();
	    		TimeUnit.SECONDS.sleep(10);
	    	}
	    } else {
	    	System.out.println("Group Results");
	    	evaluating.printAllResults();
	    }
	}

}

