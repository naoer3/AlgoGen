package algorithme;

import java.util.List;

/**
 * Classe abstraite de méthode de sélection des individus
 *
 * @param <T>
 */
public abstract class SelectionMethode<T extends Comparable<T>> {
	
	/**
	 * Nombre d'individu souhaitee
	 */
	protected int nb_individu = 0;
	
	
	
	public abstract List<Individu<T>> methodeSelection(Population<T> p);
	
	/**
	 * @return the nb_individu
	 */
	public int getNb_individu() {
		return nb_individu;
	}

	/**
	 * @param nb_individu the nb_individu to set
	 */
	public void setNb_individu(int nb_individu) {
		this.nb_individu = nb_individu;
	}
}
