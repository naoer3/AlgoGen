package algorithme;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Classe permettant la mutation d'un individu
 * @param <T>
 */
public class Mutation<T> {
	
	/**
	 * Fonction decrivant la facon dont l'individu mute
	 */
	private Function<Individu<T>, Individu<T>> fct_mutation;
	
	/**
	 * Décrit la propabilite d'un individu en pourcentage qu'un individu mute
	 */
	private double prob_mutation;

	/**
	 * Constructeur de la classe Mutation
	 * @param fct_mutation
	 * @param prob_mutation
	 */
	public Mutation(Function<Individu<T>, Individu<T>> fct_mutation, double prob_mutation) {
		this.fct_mutation = fct_mutation;
		this.prob_mutation=prob_mutation;
	}

	/**
	 * Effectue la mutation pour une liste d'individus
	 * @param individus Liste d'individus de la population
	 * @return Liste des individus de la population comprenant ce qui ont mute
	 */
	public List<Individu<T>> doMutation(List<Individu<T>> individus)
	{
		Random rand = new Random();
		for (Individu<T> individu : individus) {
			if((rand.nextInt(100)+1) <= prob_mutation) {
				individu = doIndividuMutation(individu);
			}
		}
		return individus;
	}
	
	/**
	 * Effectue la mutation pour un individu en executant fct_mutation
	 * @param individu Individu a muter
	 * @return L'individu mute
	 */
	public Individu<T> doIndividuMutation(Individu<T> individu){
		return fct_mutation.apply(individu);
	}
	
}
