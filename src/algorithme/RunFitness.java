/**
 * 
 */
package algorithme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RunFitness<T> implements Runnable {

	List<Individu<T>> individus = new ArrayList<>();
	/**
	 * Fonction a  evaluer dans l'algorithme
	 */
	private Function<Individu<T>,T> calc_finess = null;
	
	private int i;
	
	public RunFitness(int i, List<Individu<T>> individus, Function<Individu<T>,T> fct_fitness) {
		this.individus = individus;
		this.calc_finess = fct_fitness;
		this.i = i;
	}
	
	@Override
	public void run() {
		synchronized (individus) {
			for(Individu<T> individu : individus) {
				this.Evaluate(individu);
			}
		}
	}
	
	/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 * @param individu Individu a évaluer
	 */
	public void Evaluate(Individu<T> individu) {
		T fitness = calc_finess.apply(individu);
		individu.setFitness(fitness);
		//Thread ne partagent pas l'info sur lequel ils ont traité
		System.out.println("Thread "+i+ " Individu " + individu);
	}

}
