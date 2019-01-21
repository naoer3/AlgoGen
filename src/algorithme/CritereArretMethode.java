package algorithme;

/**
 * Classe qui définit les criteres d'arrets de notre algorithme
 * @param <T>
 */
public abstract class CritereArretMethode<T extends Comparable<T>> {
	
	/**
	 * Valeur du critere a respecter
	 */
	protected int critere; 
	
	/**
	 * Permet de vérifier si le critere est respecter
	 * @param population
	 * @return
	 */
	public abstract boolean getEtat(Population<T> population);
	
	/**
	 * Getter de l'attribut critere
	 * @return the critere
	 */
	public int getCritere() {
		return critere;
	}
	/**
	 * Setter de l'attibut critere
	 * @param critere the critere to set
	 */
	public void setCritere(int critere) {
		this.critere = critere;
	}

}
