package algorithme;

import java.util.function.Function;

public class FitnessEval<R> {
	
	/**
	 * Fonction a  evaluer dans l'algorithme
	 */
	private Function<Individu,R> calc_finess = null;
	
	
	public FitnessEval(Function<Individu,R> fct_finess){
		this.calc_finess = fct_finess;
	}
	
	/**
	 * Evalue la fitness d'un individu avec la fonction d'evaluation
	 */
	public void Evaluate(Individu individu) {
		R fitness = calc_finess.apply(individu);
		individu.setFitness(fitness);
	}
}
