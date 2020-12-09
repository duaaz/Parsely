import java.awt.Font;
import java.util.HashSet;

public class BellCurve {
	//you will need to install the stndDev pack from Princeton 
	private double min;
	private double max;
	private double avg;
	private double stndDev;
	
	//constructs bell curve given double average and double standard deviation as parameters
	public BellCurve(double miu, double sigma) {
		avg = miu;
		stndDev = sigma;
		min = avg-(3*stndDev);
		max = avg+(3*stndDev);
	}
	
	//plots bell curve on graphical canvas 
	 public void plot() {
		 	double offset = 0.01;
		 	double y = 0.1;
		 	 if(avg > 150 || stndDev > 100) {
		 		 y = 0.5;
		 	 }
		 	StdDraw.setCanvasSize(600, 800);
	        StdDraw.setXscale(min, max);
	        StdDraw.setYscale(0, 0.1);
	        StdDraw.text(avg, (offset), "Candidate Total Points");
	        StdDraw.setPenRadius(0.015);
	        StdDraw.setPenColor(153, 204, 153);
	        for (double x = min-10; x <= max+10; x += y) {
	            StdDraw.point(x, ((offset)+0.005)+pdf(x,avg,stndDev));
	            if(Math.abs(x%stndDev) < y) {
	            	StdDraw.text(x, ((offset)+0.003), (int)(x) + " ");
	            }
	        }
	 }
	 
	 //plots specific candidate poitns against overall bell curve on canvas
	 public void plotIndvCandidate(double points, String name) {
		 plot();
		 StdDraw.setPenColor(StdDraw.BLACK);
		 Font font = new Font("Arial", Font.BOLD, 50);
		 StdDraw.setFont(font);
		 StdDraw.text(points, 0.015+pdf(points,avg,stndDev), "x");
		 Font font2 = new Font("Arial", Font.ITALIC, 16);
		 StdDraw.setFont(font2);
		 StdDraw.text(points, 0.0125+pdf(points,avg,stndDev), name+", "+points);
	 }
	 
	 //return pdf(x) = standard Gaussian pdf
	 public double pdf(double x) {
	        return Math.exp(-x*x/2)/ Math.sqrt(2 * Math.PI);
	    }
	    
	 //return return Gaussain pdf with consideration of the average and mean 
	 public double pdf(double x, double avg, double stndDev) {
	        return pdf((x - avg)/stndDev)/ stndDev;
	    }
}
