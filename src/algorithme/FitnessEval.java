package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Classe permettant d'evaluer une population
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public class FitnessEval<T> {
	
	/***
	 * Declaration des variables globales
	 * calc_fitness:
	 */
	private Function<Individu<T>,T> calc_finess = null;
	
	/**
	 * Constructeur de la classe
	 * @param fct_finess Fonction d'évaluation de l'algorithme
	 */
	public FitnessEval(Function<Individu<T>,T> fct_finess){
		this.calc_finess = fct_finess;
	}
	
	/*/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 * @param individu Individu a evaluer
	 */	
	public void EvaluatePopulation(List<Individu<T>> individus) {

		int nb_threads = 4;
		
		List<Thread> threads = new ArrayList<>();
		
		for (int i = 1; i <= nb_threads; i++) {
			threads.add(new Thread (new RunFitness<T>(i, individus, calc_finess)));
		}

		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
