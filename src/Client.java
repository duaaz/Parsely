import java.util.*;
import java.io.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Resume> candidates = new HashSet<>();
		Set<String> basicKeyWords = new HashSet<>();
		Set<String> desiredKeyWords = new HashSet<>();
		
	    System.out.println("Hello technical recruiter!");
	    Scanner scanner = new Scanner(System.in);
	    // Asking for the basic (required) skills for the candidate resume
	    System.out.print("Please type in required skills for each candidate separated by commas (ex: 'Java, Python, Agile'): ");
	    String allReqSkills = scanner.nextLine();
	    String[] allReqSkillsSplit = allReqSkills.split(", ");
	    for (String reqSkill : allReqSkillsSplit) {
	    	basicKeyWords.add(reqSkill);
	    }
	    // Asking for the desired but not required skills for the candidate resume
	    System.out.print("Please type in desired (but not required) skills for each candidate separated by commas (ex: 'Figma, JavaScript'): ");
	    String allDesSkills = scanner.nextLine();
	    String[] allDesSkillsSplit = allDesSkills.split(", ");
	    for (String desSkill : allDesSkillsSplit) {
	    	desiredKeyWords.add(desSkill);
	    }
	
	    

	    System.out.println("Do you value experience, skills, or both equally in a candidate?");
	    //assess(Resume candidate, HashSet<String> desiredSkills, String weight)
	    System.out.print("Type 'experience', 'skills', or 'equal': ");
	    String weight = scanner.next();
	    // pass weight into assess
	    System.out.println("Type in all the file names of candidate resumes you wish to consider, separated by commas: ");
	    String allResumeNames = scanner.nextLine();
	    String[] allNamesSplit = allResumeNames.split(", ");
	    for (String name : allNamesSplit) {
	    	Resume candidate = new Resume(new File(name));
	    	candidates.add(candidate);
	    }
		Algorithm evaluating = new Algorithm(candidates);
		// evaluate
		// print all results or print individual candidate

	}

}

