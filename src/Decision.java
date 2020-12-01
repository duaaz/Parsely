
public class Decision {
	private boolean acceptance;
	private double percentile;
	private int totalPoints;
	
	public Decision(boolean newAcceptance, double newPercentile, int newTotalPoints) {
		acceptance = newAcceptance;
		percentile = newPercentile;
		totalPoints = newTotalPoints;
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
}
