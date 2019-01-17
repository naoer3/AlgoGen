package algorithme;

import java.util.List;

/**
 * Classe abstraite permettant de selectionner une methode de selection de population 
 * parmis elitiste, loterie ou tournoi
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
public abstract class SelectionMethode<T extends Comparable<T>> {
	
	/***
	 * Signature de la methode methodeSelection utilisee dans les classes derivees
	 * @param p
	 */
	public abstract List<Individu<T>> methodeSelection(Population<T> p);
}
