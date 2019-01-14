package algorithme;

import java.util.function.Function;

public class FitnessEval<T> {
	
	/**
	 * Fonction a  evaluer dans l'algorithme
	 */
	private Function<Individu<T>,T> calc_finess = null;
	
	
	public FitnessEval(Function<Individu<T>,T> fct_finess){
		this.calc_finess = fct_finess;
	}
	
	/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 */
	public void Evaluate(Individu<T> individu) {
		T fitness = calc_finess.apply(individu);
		individu.setFitness(fitness);
	}
}
