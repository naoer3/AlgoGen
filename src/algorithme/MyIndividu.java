package algorithme;

import java.util.ArrayList;

public class MyIndividu{
	
	private double score;
	private ArrayList<Double> Coords = null;

	
	public MyIndividu(double x) {
		
		Coords = new ArrayList<Double>(); 
		setCoords(x);
	}
	

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public ArrayList<Double> getCoords() {
		return Coords;
	}

	public void setCoords(double x) {
		Coords.add(x) ;
		Coords.add(Math.pow(x,2));//-5*x+1
	}
	
	@Override
	public String toString() {
		
		return "x : " + Coords.get(0)+ " , f(x) : "+ Coords.get(1);
		
	}


	

}
