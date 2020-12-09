
public class Decision {
	private boolean acceptance;
	private double percentile;
	private int totalPoints;
	private String explanation;
	
	//creates decision object given boolean acceptance, double percentile, integer total points, string explanation 
	//as parameters
	public Decision(boolean newAcceptance, double newPercentile, int newTotalPoints, String newEx) {
		acceptance = newAcceptance;
		percentile = newPercentile;
		totalPoints = newTotalPoints;
		explanation = newEx;
	}
	
	//set acceptance field given boolean parameter
	public void setAcceptance(boolean newDecision) {
		acceptance = newDecision;
	}
	
	//returns acceptance field as boolean 
	public boolean getAcceptance() {
		return acceptance;
	}
	
	//set percentile field given double parameter
	public void setPercentile(double newPercent) {
		percentile = newPercent;
	}
	
	//returns percentile field as double
	public double getPercentile() {
		return percentile;
	}
	
	//set totalPoints field given integer parameter
	public void setPoints(int newPoints) {
		totalPoints = newPoints;
	}
	
	//returns totalPoints field as integer
	public int getTotalPoints() {
		return totalPoints;
	}
	
	//set explanation field given String parameter
	public void setExplanation(String newExplain) {
		explanation = newExplain;
	}
	
	//returns explanation field as String
	public String getExplanation() {
		return explanation;
	}
}
