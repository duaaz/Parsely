
public class Decision {
	private boolean acceptance;
	private double percentile;
	private int totalPoints;
	private String explanation;
	
	public Decision(boolean newAcceptance, double newPercentile, int newTotalPoints, String newEx) {
		acceptance = newAcceptance;
		percentile = newPercentile;
		totalPoints = newTotalPoints;
		explanation = newEx;
	}
	
	public void setAcceptance(boolean newDecision) {
		acceptance = newDecision;
	}
	
	public boolean getAcceptance() {
		return acceptance;
	}
	
	public void setPercentile(double newPercent) {
		percentile = newPercent;
	}
	
	public double getPercentile() {
		return percentile;
	}
	
	public void setPoints(int newPoints) {
		totalPoints = newPoints;
	}
	
	public int getTotalPoints() {
		return totalPoints;
	}
	
	public void setExplanation(String newExplain) {
		explanation = newExplain;
	}
	
	public String getExplanation() {
		return explanation;
	}
}
