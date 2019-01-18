package algorithme;

import java.util.Comparator;

/**
 * Classe permettant d'effectuer la comparaison entre des individus
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public class ComparatorIndividu<T extends Comparable<T>> implements Comparator<Individu<T>>{

	/***
	 * compare: Retourne un entier en fonction du résultat de la comparaison
	 * @param1: indiv1
	 * @param2: indiv2
	 */
	@Override
	public int compare(Individu<T> indiv1, Individu<T> indiv2) {
		T fit1 = indiv1.getFitness();
		T fit2 = indiv2.getFitness();
		return fit1.compareTo(fit2);
	}
}
