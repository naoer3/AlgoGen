package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public abstract class Individu<T, R> {
	
	/**
	 * Définit un individu
	 * @version 1.0
	 * @since 1.0
	 */
	
	/**
	 * Score de l'individu
	 */
	private T fitness;
	
	/**
	 * Retourne le score de l'individu
	 * @return fitness de l'individu
	 */
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

	
	
}
