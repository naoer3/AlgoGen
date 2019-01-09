package algorithme;

import java.util.ArrayList;

public class MyIndividu extends Individu{

	public MyIndividu(double x, double y) {
		Coords = new ArrayList<Double>(); 
		setCoords(x,y);
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

	public void setCoords(double x, double y) {
		Coords.add(x) ;
		Coords.add(y) ;
		Coords.add(Math.pow(x,2)+Math.pow(y,2));//-5*x+1
	}
	
	@Override
	public String toString() {
		
		return "x : " + Coords.get(0)+"  , y : " + Coords.get(1)+ " , f(x,y) : "+ Coords.get(2);
		
	}


	


	

}
