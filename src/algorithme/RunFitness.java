/**
 * 
 */
package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RunFitness<T> implements Runnable {

	/**
	 * liste d'individus a evaluer
	 */
	private List<Individu<T>> individus = new ArrayList<>();
	
	/**
	 * Fonction d'evaluation
	 */
	private Function<Individu<T>,T> calc_finess = null;

	/**
	 * index partage entre les threads
	 */
	private static Integer index = 0;
	/**
	 * index servant a effectuer des modifications sur un individu specifique
	 */
	private int workedIndex;

	/**
	 * Constructeur de la classe
	 * Initialise un runnable
	 * @param individus liste d'individus a evaluer
	 * @param fct_fitness fonction utile pour l'evaluation
	 */
	public RunFitness(List<Individu<T>> individus, Function<Individu<T>,T> fct_fitness) {
		this.individus = individus;
		this.calc_finess = fct_fitness;
	}

	/**
	 * Permet l'evaluation de chaque individu par les differents threads tant que la liste n'est pas complétement bloquée
	 */
	@Override
	public void run() {
		while(true) {
			synchronized (index) {
				this.workedIndex = index.intValue();
				if(index<individus.size()) 
					index ++;
				else
					break;
			}
			Individu<T> individu = individus.get(workedIndex);
			this.Evaluate(individu);
		}

	}


	/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 * @param individu Individu a évaluer
	 */
	public void Evaluate(Individu<T> individu) {
		T fitness = calc_finess.apply(individu);
		individu.setFitness(fitness);
	}

}
