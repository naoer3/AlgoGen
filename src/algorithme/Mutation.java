package algorithme;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Classe permettant la mutation d'un individu
 * @param <T>
 */
public class Mutation<T> {
	
	/***
	 * Declaration des variables globales
	 * fct_mutation: Fonction permettant de muter un individu
	 * prob_mutation: Pourcentage de la population selectionne pour les mutations
	 */
	private Function<Individu<T>, Individu<T>> fct_mutation;
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

	/***
	 * doMutation: Methode qui permet de muter une liste d'individus
	 * @param individus
	 * @return une liste d'individus mutes
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
	
	/***
	 * doIndividuMutation: Methode permettant d'effecter une mutation sur un indidivu particulier
	 * @param individu
	 * @return un individu mute
	 */
	public Individu<T> doIndividuMutation(Individu<T> individu){
		return fct_mutation.apply(individu);
	}
	
}
