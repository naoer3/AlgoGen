package algorithme;

import java.util.ArrayList;

/**
 * Définit un individu
 * @version 1.0
 * @since 1.0
 */
public class Individu<T> {
		
	protected ArrayList<Double> Coords = null;
			
	/**
	 * Score de l'individu
	 */
	private T fitness;

	public T getFitness() {
		return fitness;
	}
	
	/**
	 * Permet de modifier le score de l'individu
	 * @param fitness la nouvelle fitness de l'individu
	 */
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
	
	public String toString() {
		
		return "x : " + Coords.get(0)+"  , y : " + Coords.get(1)+ " , f(x,y) : "+ Coords.get(2);
	}
	
}
