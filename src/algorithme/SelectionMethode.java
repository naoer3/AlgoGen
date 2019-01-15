package algorithme;

import java.util.List;

/**
 * Classe abstraite de m�thode de s�lection des individus
 *
 * @param <T>
 */
public abstract class SelectionMethode<T extends Comparable<T>> {
	
	public abstract List<Individu<T>> methodeSelection(Population<T> p);
}
