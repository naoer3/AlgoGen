package algorithme;

public abstract class CritereArretMethode<T extends Comparable<T>> {
	
	protected int critere; 
	
	public abstract boolean getEtat(Population<T> population);
	
	/**
	 * @return the critere
	 */
	public int getCritere() {
		return critere;
	}
	/**
	 * @param critere the critere to set
	 */
	public void setCritere(int critere) {
		this.critere = critere;
	}

}
