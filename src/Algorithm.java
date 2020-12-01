import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Algorithm {
	
	//Set of all potential candidates in pool 
	private HashSet<Resume> candidates;
	//map of candidates and if they are accepted/rejected along with percentile against population 
	private HashMap<Resume, Decision> acceptance;
	private double median;
	private double stndDev;
	private double avg;
	private HashSet<String> basicSkills;
	private HashSet<String> desiredSkills;
	private String weight;
	
	public Algorithm(HashSet<Resume> newCandidates, HashSet<String> newBasicSkills, HashSet<String> newDesiredSkills, String newWeight) throws FileNotFoundException {
		basicSkills = newBasicSkills;
		desiredSkills = newDesiredSkills;
		weight = newWeight;
		candidates = newCandidates;
		//these three fields get defined in evaluate method
		median = 0;
		stndDev=0;
		avg=0;
		acceptance = this.evaluate(candidates);
	}
	
	public boolean qualify(HashSet<String> basicSkills, Resume candidate) {
		//don't need another containsAll method, java has this method as part of collections
		//I need a getkeywords method without the file parameter
		if (candidate.getKeyWords().containsAll(basicSkills)) return true;
		return false;
	}
	
	public Integer assess(Resume candidate, HashSet<String> desiredSkills, String weight) throws FileNotFoundException {
		int totalPoints = 0;
		Set<String> skills = candidate.getKeyWords();
		Map<String, Integer> experience = candidate.getExperience();
		//determines if recruiter enters in skills, experience, or both for weighted points 
		if(weight.toLowerCase().equals("skills")) {
			for(String skill:desiredSkills) {
				if(skills.contains(skill)) {
					totalPoints+=2;
					totalPoints+=experience.get(skill);
				}
			}
			
		}else if(weight.toLowerCase().equals("experience")) {
			for(String skill:desiredSkills) {
				if(skills.contains(skill)) {
					totalPoints+=1;
					totalPoints+=2*experience.get(skill);
				}
			}
		}else {
			for(String skill:desiredSkills) {
				if(skills.contains(skill)) {
					totalPoints+=1;
					totalPoints+=experience.get(skill);
				}
			}
		}
		return totalPoints;
	}
	
	//determines if candidate is rejected/accepted
	//if candidate is less than 50% percentile, they are rejected (can potentially let recruiter enter method)
	//puts results in a HashMap 
	//make another object instead 
	public HashMap<Resume, Decision> evaluate(HashSet<Resume> potCandidates) throws FileNotFoundException{
		HashMap<Resume, Decision> evaluation = new HashMap<>();
		Iterator<Resume> itr = potCandidates.iterator();
		//reject candidates without basic skills
		while(itr.hasNext()) {
			Resume candidate = itr.next();
			if(!qualify(basicSkills, candidate)) {
				//just give 0 points if don't even meet basic qualifications 
				evaluation.put(candidate, new Decision(false, 0.0, 0));
				itr.remove();
			}
		}
		//do assess method to assign points to candidates
		HashMap<Integer, Resume> candidatePoints = new HashMap<>();
		while(itr.hasNext()) {
			Resume curCandidate = itr.next();
			//assign point value to each candidate
			candidatePoints.put(assess(curCandidate,desiredSkills,weight), curCandidate);
		}
		//sort from least to most total points, with points as they key 
		TreeMap<Integer, Resume> tempOrderedCandidates = new TreeMap<>(candidatePoints);
		//array list of total points in order for calculating statistics 
		//sorted an array list in this manner because don't know how to sort a set
		ArrayList<Integer> orderedPoints = new ArrayList<>();
		//add all candidate points in ordered arraylist
		for(Integer points: tempOrderedCandidates.keySet()) {
			orderedPoints.add(points);
		}
		//little sketch to be calculating fields here but I think it should be ok
		median = getMedian(orderedPoints);
		stndDev = getStandardDev(orderedPoints, median);
		avg = getAverage(orderedPoints);
		//less than certain percentile -> rejected
		//in this case use 50% as example, can be changed if want recruiter to make choice 
		for(Integer points:tempOrderedCandidates.keySet()) {
			int totalPoints = points;
			Double percent = getPercentile(points, orderedPoints);
			Resume candidate = tempOrderedCandidates.get(points);
			//give each candidate a decision
			Decision temp = new Decision(false, percent, totalPoints);
			if(percent < 0.5) {
				//if less than 50%, candidate is rejected
				evaluation.put(candidate, temp);
			}else {
				//only accept candidate if they are better than 50%
				temp.setAcceptance(true);
				evaluation.put(candidate, temp);
			}
		}
		return evaluation; 
	}
	
	
	//print overall statistics of all candidates
	public void printAllResults() {
		System.out.println("--------- Overall Statistics ---------");
		System.out.println("Median: " + median);
		System.out.println("Average: " + avg);
		System.out.println("Standard Deviation: " + stndDev);
		System.out.println("--------- Candidate Statistics ---------");
		for(Resume candidate:acceptance.keySet()) {
			System.out.println("Candidate: " + candidate.getName());
			if(acceptance.get(candidate).getAcceptance()) {
				System.out.println("Candidate was accepted");
			}else {
				System.out.println("Candidate was rejected");
			}
			System.out.println("Percentile: " + acceptance.get(candidate).getPercentile());
		}
		printCurve();
	}
	
	//prints individual candidate statistics 
	public void printIndvResult(Resume candidate) {
		System.out.println("Candidate: " + candidate.getName());
		if(acceptance.get(candidate).getAcceptance()) {
			System.out.println("Candidate was accepted");
		}else {
			System.out.println("Candidate was rejected");
		}
		System.out.println("Percentile: " + acceptance.get(candidate).getPercentile());
		//need getKeyWords method 
		System.out.println("Main Skills: " + candidate.getKeyWords().toString());
		printIndvCurve(candidate);
	}
	
	//prints out overall bell curve of population 
	public void printCurve() {
		BellCurve newCurve = new BellCurve(avg, stndDev);
		newCurve.plot();
	}
	
	//plots point against curve for candidate
	public void printIndvCurve(Resume candidate) {
			BellCurve newCurve = new BellCurve(avg, stndDev);
			newCurve.plotIndvCandidate(acceptance.get(candidate).getTotalPoints());
	}
		
	//returns median points of population
	public Double getMedian(ArrayList<Integer> orderedCandidates) {
		if(orderedCandidates.size()%2!=0) {
			return (double) (orderedCandidates.get(orderedCandidates.size()/2));
		}else {
			int m1 = orderedCandidates.get(orderedCandidates.size()/2);
			int m2 = orderedCandidates.get((orderedCandidates.size()/2)+1);
			return (m1+m2)/2.0;
		}	
	}
	
	//returns average points of population
	public Double getAverage(ArrayList<Integer> orderedCandidates) {
		double total = 0;
		for(Integer points: orderedCandidates) {
			total+=points;
		}
		return total/orderedCandidates.size();
	}
	
	//returns standard deviation in points of population
	public Double getStandardDev(ArrayList<Integer> orderedCandidates, Double median) {
		double sum = 0;
		for(Integer points: orderedCandidates) {
			double temp = points;
			sum+= Math.pow(temp-median, 2.0);
		}
		return Math.sqrt(sum/orderedCandidates.size());
	}
	
	//returns percentile of candidate 
	public double getPercentile(Integer candidate, ArrayList<Integer> orderedCandidates) {
		int index = orderedCandidates.indexOf(candidate);
		return (double)(index/orderedCandidates.size());
	}
	
}
