package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FitnessEval<T extends Comparable<T>>{

	/**
	 * Fonction a  evaluer dans l'algorithme
	 */
	private Function<Individu<T>,T> calc_finess = null;

	/**
	 * Constructeur de la classe
	 * @param fct_finess Fonction d'évaluation de l'algorithme
	 */
	public FitnessEval(Function<Individu<T>,T> fct_finess){
		this.calc_finess = fct_finess;
	}

	/**
	 * Retourne les individus evalues passes en parametres
	 * @param liste d'individus a evaluer
	 * @return liste d'individus evalues
	 */
	public List<Individu<T>> EvaluatePopulation(List<Individu<T>> individus) {

		int nb_threads = 4;

		List<Thread> threads = new ArrayList<>();

		for (int i = 1; i <= nb_threads; i++) {
			threads.add(new Thread (new RunFitness<T>(individus, calc_finess)));
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

		return individus;

	}
}
