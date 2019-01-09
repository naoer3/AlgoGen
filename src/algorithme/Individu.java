package algorithme;

import java.util.ArrayList;

public class Individu<T>{
	
	private ArrayList<Double> Coords = new ArrayList<>();
	private T fitness;

	public T getFitness() {
		return fitness;
	}

	public void setFitness(T fitness) {
		this.fitness = fitness;
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
