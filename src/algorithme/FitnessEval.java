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
	
	/*/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 * @param individu Individu a évaluer
	 */
	/*public void Evaluate(Individu<T> individu) {
		T fitness = calc_finess.apply(individu);
		individu.setFitness(fitness);
	}*/
	
	public void EvaluatePopulation(List<Individu<T>> individus) {

		int nb_threads = 4;
		
		List<Thread> threads = new ArrayList<>();
		
		for (int i = 1; i <= nb_threads; i++) {
			//TODO i à enlever
			threads.add(new Thread (new RunFitness<T>(i, individus, calc_finess)));
		}

		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*for(Individu<T> individu : individus) {
			this.Evaluate(individu);
		}*/
	}
}
